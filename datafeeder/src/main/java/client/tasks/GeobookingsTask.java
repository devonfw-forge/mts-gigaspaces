package client.tasks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openspaces.core.GigaSpace;
import org.openspaces.core.executor.DistributedTask;
import org.openspaces.core.executor.TaskGigaSpace;

import com.devonfw.application.mtsj.bookingmanagement.dataaccess.api.BookingEntity;
import com.devonfw.application.mtsj.clustermanagement.dataaccess.api.AddressEntity;
import com.devonfw.application.mtsj.clustermanagement.dataaccess.api.GeobookingEntity;
import com.devonfw.application.mtsj.dishmanagement.dataaccess.api.DishEntity;
import com.devonfw.application.mtsj.ordermanagement.dataaccess.api.OrderLineEntity;
import com.gigaspaces.async.AsyncResult;

/**
 * @author dpatesan
 *
 *         Class responsible for Joining information spread across Bookings, Addresses, Order Lines and Dishes in order
 *         to create GeoBookings.
 *
 */
public class GeobookingsTask implements DistributedTask<Integer, Integer> {

  private static final long serialVersionUID = 1L;

  @TaskGigaSpace
  private transient GigaSpace gigaSpace;

  @Override
  public Integer execute() throws Exception {

    GigaSpace clusteredProxy = this.gigaSpace.getClustered();
    List<GeobookingEntity> geobookings = new ArrayList<>();
    BookingEntity bookingTemplate = new BookingEntity();

    // Requesting and Iterating over all Bookings
    List<BookingEntity> bookings = Arrays.asList(this.gigaSpace.readMultiple(bookingTemplate));

    for (BookingEntity booking : bookings) {

      String userId = booking.getUserId();
      String orderId = booking.getOrderId();

      // Query Entities that contain Information belonging to a GeoBooking
      AddressEntity addressTemplate = new AddressEntity();
      addressTemplate.setUserId(userId);
      AddressEntity address = clusteredProxy.read(addressTemplate);

      OrderLineEntity orderLineTemplate = new OrderLineEntity();
      orderLineTemplate.setOrderId(orderId);
      OrderLineEntity orderLine = clusteredProxy.read(orderLineTemplate);

      DishEntity dish = clusteredProxy.readById(DishEntity.class, orderLine.getDishId());

      // Create a GeoBooking Object
      GeobookingEntity geobooking = new GeobookingEntity();
      geobooking.setBookingDate(booking.getBookingDate().getEpochSecond());
      geobooking.setLatitude(address.getLatitude());
      geobooking.setLongitude(address.getLongitude());
      geobooking.setDishId(orderLine.getDishId());
      geobooking.setAmount(orderLine.getAmount());
      geobooking.setDishName(dish.getName());

      geobookings.add(geobooking);
    }
    this.gigaSpace.writeMultiple(geobookings.toArray());
    return geobookings.size();
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
