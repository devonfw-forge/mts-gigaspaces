package client.tasks;

import static org.openspaces.extensions.QueryExtension.average;
import static org.openspaces.extensions.QueryExtension.groupBy;
import static org.openspaces.extensions.QueryExtension.max;
import static org.openspaces.extensions.QueryExtension.min;
import static org.openspaces.extensions.QueryExtension.sum;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.openspaces.core.GigaSpace;
import org.openspaces.core.executor.DistributedTask;
import org.openspaces.core.executor.TaskGigaSpace;

import com.devonfw.application.mtsj.ordermanagement.dataaccess.api.OrderedDishesPerMonthEntity;
import com.devonfw.application.mtsj.predictionmanagement.dataaccess.api.MonthlyOrderedDishesEntity;
import com.gigaspaces.async.AsyncResult;
import com.gigaspaces.query.aggregators.GroupByAggregator;
import com.gigaspaces.query.aggregators.GroupByResult;
import com.gigaspaces.query.aggregators.GroupByValue;
import com.j_spaces.core.client.SQLQuery;

/**
 * @author dpatesan
 *
 *         Class responsible for Grouping the MonthlyOrderedDishes by Month and calculating the Information about the
 *         Month (Average Temperature).
 */
public class OrderedDishesPerMonthTask implements DistributedTask<Integer, Integer> {

  private static final long serialVersionUID = -2471928549211928236L;

  @TaskGigaSpace
  private transient GigaSpace gigaSpace;

  @Override
  public Integer execute() throws Exception {

    List<OrderedDishesPerMonthEntity> orderedDishesPerMonth = new ArrayList<>();
    GigaSpace clusteredProxy = this.gigaSpace.getClustered();

    // Execute Group By Clause over monthly ordered dishes
    SQLQuery<MonthlyOrderedDishesEntity> query = new SQLQuery<>(MonthlyOrderedDishesEntity.class, "");
    GroupByResult groupByResult = groupBy(clusteredProxy, query,
        new GroupByAggregator().select(min("bookingDate"), max("dishId"), average("temperature"), sum("amount"))
            .groupBy("yearmonth", "dishId"));

    // Iterate through group results
    for (GroupByValue group : groupByResult) {
      Instant bookingDate = (Instant) group.get(0);
      String dishId = group.getString(1);
      Double temperature = group.getDouble(2);
      Long amount = group.getLong(3);

      BigDecimal roundedTemperature = new BigDecimal(Double.toString(temperature));
      roundedTemperature = roundedTemperature.setScale(1, RoundingMode.HALF_UP);
      String id = Timestamp.from(bookingDate).toString().substring(0, 10).concat(dishId);

      // Create ordered dishes per month Object
      OrderedDishesPerMonthEntity orderedDishPerMonth = new OrderedDishesPerMonthEntity();
      orderedDishPerMonth.setId(id);
      orderedDishPerMonth.setAmount((int) (long) amount);
      orderedDishPerMonth.setTemperature(roundedTemperature.doubleValue());
      orderedDishPerMonth.setDishId(dishId);

      /*
       * Adjust the date to be always the first day of the month and time to be 00:00
       */
      Calendar calendar = Calendar.getInstance();
      calendar.setTime(Date.from(bookingDate));
      calendar.set(Calendar.DAY_OF_MONTH, 1);
      Date zeroedDate = calendar.getTime();
      Timestamp bookingDateTS = new Timestamp(zeroedDate.getTime());
      orderedDishPerMonth.setBookingdate(bookingDateTS);

      orderedDishesPerMonth.add(orderedDishPerMonth);

    }
    OrderedDishesPerMonthEntity template = new OrderedDishesPerMonthEntity();
    clusteredProxy.writeMultiple(orderedDishesPerMonth.toArray());
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
