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

import com.devonfw.application.mtsj.ordermanagement.dataaccess.api.OrderLineEntity;

/**
 * @author dpatesan
 *
 *         Class responsible for creating and storing sample Data for OrderLines out of a csv file.
 */
public class OrderLineFeeder {
  GigaSpace gigaSpace;

  public OrderLineFeeder(GigaSpace gigaSpace) {

    this.gigaSpace = gigaSpace;

  }

  /**
   * Create and load sample OrderLine data into the space.
   *
   * @throws Exception
   */
  public void loadData() throws Exception {

    List<OrderLineEntity> orderLines = readOrderLinesFromCSV("src/main/resources/orderline.csv");
    this.gigaSpace.writeMultiple(orderLines.toArray());

  }

  private List<OrderLineEntity> readOrderLinesFromCSV(String fileName) {

    List<OrderLineEntity> orderLines = new ArrayList<>();
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

        OrderLineEntity orderLine = createOrderLine(attributes);

        // adding orderLine into ArrayList
        orderLines.add(orderLine);

        // read next line before looping
        // if end of file reached, line would be null
        line = br.readLine();
      }

    } catch (IOException ioe) {
      ioe.printStackTrace();
    }

    return orderLines;

  }

  private OrderLineEntity createOrderLine(String[] metadata) {

    // Extract and Convert the attributes from metadata.
    String id = metadata[0];
    String dishId = metadata[2];
    Integer amount = Integer.parseInt(metadata[3]);
    String orderId = metadata[5];

    // create and return order line of this metadata
    OrderLineEntity orderLine = new OrderLineEntity();
    orderLine.setId(id);
    orderLine.setDishId(dishId);
    orderLine.setAmount(amount);
    orderLine.setOrderId(orderId);

    return orderLine;
  }
}
