package client.feeders;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.openspaces.core.GigaSpace;

import com.devonfw.application.mtsj.bookingmanagement.dataaccess.api.BookingEntity;
import com.devonfw.application.mtsj.ordermanagement.dataaccess.api.OrderEntity;
import com.gigaspaces.client.ChangeSet;
import com.gigaspaces.query.IdQuery;

/**
 * @author dpatesan
 *
 *         Class responsible for creating and storing sample Data for Orders out of a csv file.
 *
 */
public class OrdersFeeder {
  GigaSpace gigaSpace;

  public OrdersFeeder(GigaSpace gigaSpace) {

    this.gigaSpace = gigaSpace;
  }

  /**
   * Create and load sample Order data into the space.
   *
   * @throws Exception
   */
  public void loadData() throws Exception {

    List<OrderEntity> orders = readOrdersFromCSV("src/main/resources/orders.csv");
    this.gigaSpace.writeMultiple(orders.toArray());

  }

  private List<OrderEntity> readOrdersFromCSV(String fileName) {

    List<OrderEntity> orders = new ArrayList<>();
    Path pathToFile = Paths.get(fileName);
    // create an instance of BufferedReader
    // using try with resource, Java 7 feature to close resources
    try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.UTF_8)) {

      // read the first line from the text file
      String line = br.readLine();

      // loop until all lines are read
      while (line != null) {

        // use string.split to load a string array with the values from
        // each line of
        // the file, using a comma as the delimiter
        line = line.replace("\"", "");
        String[] attributes = line.split(",");

        OrderEntity order = createOrder(attributes);

        // adding order into ArrayList
        orders.add(order);

        // read next line before looping
        // if end of file reached, line would be null
        line = br.readLine();
      }

    } catch (IOException ioe) {
      ioe.printStackTrace();
    }

    return orders;

  }

  private OrderEntity createOrder(String[] metadata) {

    // Extract and Convert the attributes from metadata.
    String orderId = metadata[0];
    String bookingId = metadata[2];

    // create and return order of this metadata
    OrderEntity order = new OrderEntity();

    order.setId(orderId);
    order.setBookingId(bookingId);

    // update the orderId of the booking that belongs to this order
    IdQuery<BookingEntity> bookingIdQuery = new IdQuery<BookingEntity>(BookingEntity.class, bookingId);
    this.gigaSpace.change(bookingIdQuery, new ChangeSet().set("orderId", orderId));

    return order;
  }
}
