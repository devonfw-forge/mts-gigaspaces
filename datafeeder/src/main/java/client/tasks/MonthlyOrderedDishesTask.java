package client.tasks;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openspaces.core.GigaSpace;
import org.openspaces.core.executor.DistributedTask;
import org.openspaces.core.executor.TaskGigaSpace;

import com.devonfw.application.mtsj.bookingmanagement.dataaccess.api.BookingEntity;
import com.devonfw.application.mtsj.ordermanagement.dataaccess.api.OrderEntity;
import com.devonfw.application.mtsj.ordermanagement.dataaccess.api.OrderLineEntity;
import com.devonfw.application.mtsj.predictionmanagement.dataaccess.api.DateInfoEntity;
import com.devonfw.application.mtsj.predictionmanagement.dataaccess.api.MonthlyOrderedDishesEntity;
import com.gigaspaces.async.AsyncResult;

/**
 * @author dpatesan
 *
 *         Class responsible for Joining Information about monthly ordered dishes. This means the enrichment of Order
 *         Lines with a the Date of it's Booking and a Field containing only the combination of Month and Year.
 */
public class MonthlyOrderedDishesTask implements DistributedTask<Integer, Integer> {

  private static final long serialVersionUID = -2471928549211928236L;

  @TaskGigaSpace
  private transient GigaSpace gigaSpace;

  @Override
  public Integer execute() throws Exception {

    GigaSpace clusteredProxy = this.gigaSpace.getClustered();
    List<MonthlyOrderedDishesEntity> monthlyOrderedDishes = new ArrayList<>();
    OrderLineEntity orderLineTemplate = new OrderLineEntity();

    // Requesting and Iterating over all Order Lines
    List<OrderLineEntity> orderLines = Arrays.asList(this.gigaSpace.readMultiple(orderLineTemplate));
    for (OrderLineEntity orderLine : orderLines) {

      // Query Entities that contain Information belonging to a MonthlyOrderdishesEntity
      String orderId = orderLine.getOrderId();

      OrderEntity order = clusteredProxy.readById(OrderEntity.class, orderId);

      String bookingId = order.getBookingId();

      BookingEntity booking = clusteredProxy.readById(BookingEntity.class, bookingId);

      DateInfoEntity dateInfoTemplate = new DateInfoEntity();
      dateInfoTemplate.setDate(Timestamp.from(booking.getBookingDate()));
      DateInfoEntity dateInfo = clusteredProxy.read(dateInfoTemplate);

      // Create a monthly ordered dish Object
      MonthlyOrderedDishesEntity monthlyOrderedDish = new MonthlyOrderedDishesEntity();
      monthlyOrderedDish.setDishId(orderLine.getDishId());
      monthlyOrderedDish.setBookingDate(booking.getBookingDate());
      monthlyOrderedDish.setAmount(orderLine.getAmount());
      monthlyOrderedDish.setTemperature(dateInfo.getTemperature());

      Instant bookingdateInstant = booking.getBookingDate();
      Timestamp bookingdate = Timestamp.from(bookingdateInstant);

      // Extract only the Month and Year from the date.
      String yearmonth = bookingdate.toString().substring(0, 7);
      monthlyOrderedDish.setYearmonth(yearmonth);

      monthlyOrderedDishes.add(monthlyOrderedDish);
    }
    this.gigaSpace.writeMultiple(monthlyOrderedDishes.toArray());
    return monthlyOrderedDishes.size();
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
