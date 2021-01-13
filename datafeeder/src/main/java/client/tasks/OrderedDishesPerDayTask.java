package client.tasks;

import static org.openspaces.extensions.QueryExtension.groupBy;
import static org.openspaces.extensions.QueryExtension.max;
import static org.openspaces.extensions.QueryExtension.sum;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.openspaces.core.GigaSpace;
import org.openspaces.core.executor.DistributedTask;
import org.openspaces.core.executor.TaskGigaSpace;

import com.devonfw.application.mtsj.ordermanagement.dataaccess.api.OrderedDishesPerDayEntity;
import com.devonfw.application.mtsj.predictionmanagement.dataaccess.api.DailyOrderedDishesEntity;
import com.devonfw.application.mtsj.predictionmanagement.dataaccess.api.DateInfoEntity;
import com.gigaspaces.async.AsyncResult;
import com.gigaspaces.query.aggregators.GroupByAggregator;
import com.gigaspaces.query.aggregators.GroupByResult;
import com.gigaspaces.query.aggregators.GroupByValue;
import com.j_spaces.core.client.SQLQuery;

/**
 * @author dpatesan
 *
 *         Class responsible for Grouping the DailyOrderedDishes by Day and enriching the results with the Information
 *         about the Day (Temperature, Designation) from the DateInfo.
 *
 */
public class OrderedDishesPerDayTask implements DistributedTask<Integer, Integer> {

  private static final long serialVersionUID = 1L;

  @TaskGigaSpace
  private transient GigaSpace gigaSpace;

  @Override
  public Integer execute() throws Exception {

    List<OrderedDishesPerDayEntity> orderedDishesPerDay = new ArrayList<>();
    GigaSpace clusteredProxy = this.gigaSpace.getClustered();

    // Execute Group By Clause over daily ordered dishes
    SQLQuery<DailyOrderedDishesEntity> query = new SQLQuery<>(DailyOrderedDishesEntity.class, "");
    GroupByResult groupByResult = groupBy(clusteredProxy, query, new GroupByAggregator()
        .select(max("bookingDate"), max("dishId"), sum("amount")).groupBy("bookingDate", "dishId"));

    // Iterate through group results
    for (GroupByValue group : groupByResult) {

      Instant bookingDate = (Instant) group.get(0);
      String dishId = group.getString(1);
      Long amount = group.getLong(2);

      String id = Timestamp.from(bookingDate).toString().substring(0, 10).concat(dishId);

      DateInfoEntity dateInfoTemplate = new DateInfoEntity();
      dateInfoTemplate.setDate(Timestamp.from(bookingDate));
      DateInfoEntity dateInfo = clusteredProxy.read(dateInfoTemplate);

      // Create ordered dishes per day Object
      OrderedDishesPerDayEntity orderedDishPerDay = new OrderedDishesPerDayEntity();
      orderedDishPerDay.setId(id);
      orderedDishPerDay.setAmount((int) (long) amount);
      orderedDishPerDay.setTemperature(dateInfo.getTemperature());
      orderedDishPerDay.setDesignation(dateInfo.getDesignation());
      orderedDishPerDay.setDishId(dishId);
      orderedDishPerDay.setBookingdate(Timestamp.from(bookingDate));
      orderedDishesPerDay.add(orderedDishPerDay);

    }

    OrderedDishesPerDayEntity template = new OrderedDishesPerDayEntity();
    clusteredProxy.writeMultiple(orderedDishesPerDay.toArray());
    return this.gigaSpace.count(template);
  }

  @Override
  public Integer reduce(List<AsyncResult<Integer>> results) throws Exception {

    int totalSize = 0;
    for (AsyncResult<Integer> result : results) {
      if (result.getException() != null) {
        throw result.getException();
      }
      totalSize += result.getResult();
    }
    return totalSize;
  }

}
