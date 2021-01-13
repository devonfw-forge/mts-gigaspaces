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

import com.devonfw.application.mtsj.bookingmanagement.dataaccess.api.TableEntity;

/**
 * @author dpatesan
 *
 *         Class responsible for creating and storing sample Data for Tables out of a csv file.
 */
public class TableFeeder {

  private GigaSpace gigaSpace;

  public TableFeeder(GigaSpace gigaSpace) {

    this.gigaSpace = gigaSpace;
  }

  /**
   * Create and load sample Table data into the space.
   *
   * @throws Exception
   */
  public void loadData() throws Exception {

    List<TableEntity> tables = readTablesFromCSV("src/main/resources/table.csv");
    this.gigaSpace.writeMultiple(tables.toArray());

  }

  private List<TableEntity> readTablesFromCSV(String fileName) {

    List<TableEntity> tables = new ArrayList<>();
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

        TableEntity orderLine = createTable(attributes);

        // adding table into ArrayList
        tables.add(orderLine);

        // read next line before looping
        // if end of file reached, line would be null
        line = br.readLine();
      }

    } catch (IOException ioe) {
      ioe.printStackTrace();
    }

    return tables;
  }

  private TableEntity createTable(String[] metadata) {

    // Extract and Convert the attributes from metadata.
    String id = metadata[0];
    Integer seatsNumber = Integer.parseInt(metadata[2]);

    // create and return table of this metadata
    TableEntity table = new TableEntity();
    table.setId(id);
    table.setSeatsNumber(seatsNumber);

    return table;
  }
}