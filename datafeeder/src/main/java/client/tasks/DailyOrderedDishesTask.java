package client.tasks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openspaces.core.GigaSpace;
import org.openspaces.core.executor.DistributedTask;
import org.openspaces.core.executor.TaskGigaSpace;

import com.devonfw.application.mtsj.bookingmanagement.dataaccess.api.BookingEntity;
import com.devonfw.application.mtsj.ordermanagement.dataaccess.api.OrderEntity;
import com.devonfw.application.mtsj.ordermanagement.dataaccess.api.OrderLineEntity;
import com.devonfw.application.mtsj.predictionmanagement.dataaccess.api.DailyOrderedDishesEntity;
import com.gigaspaces.async.AsyncResult;

/**
 * @author dpatesan
 *
 *         Class responsible for Joining Information about daily ordered dishes. This means the enrichment of Order
 *         Lines with a the Date of it's Booking.
 */
public class DailyOrderedDishesTask implements DistributedTask<Integer, Integer> {

  private static final long serialVersionUID = 1L;

  @TaskGigaSpace
  private transient GigaSpace gigaSpace;

  @Override
  public Integer execute() throws Exception {

    GigaSpace clusteredProxy = this.gigaSpace.getClustered();
    List<DailyOrderedDishesEntity> dailyOrderedDishes = new ArrayList<>();
    OrderLineEntity orderLineTemplate = new OrderLineEntity();

    // Requesting and Iterating over all Order Lines
    List<OrderLineEntity> orderLines = Arrays.asList(this.gigaSpace.readMultiple(orderLineTemplate));

    for (OrderLineEntity orderLine : orderLines) {

      // Query Entities that contain Information belonging to a DailyOrderdishesEntity
      String orderId = orderLine.getOrderId();

      OrderEntity order = clusteredProxy.readById(OrderEntity.class, orderId);

      String bookingId = order.getBookingId();

      BookingEntity booking = clusteredProxy.readById(BookingEntity.class, bookingId);

      // Create a daily ordered dish Object
      DailyOrderedDishesEntity dailyOrderedDish = new DailyOrderedDishesEntity();
      dailyOrderedDish.setDishId(orderLine.getDishId());
      dailyOrderedDish.setBookingDate(booking.getBookingDate());
      dailyOrderedDish.setAmount(orderLine.getAmount());

      dailyOrderedDishes.add(dailyOrderedDish);
    }
    this.gigaSpace.writeMultiple(dailyOrderedDishes.toArray());
    return dailyOrderedDishes.size();
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
