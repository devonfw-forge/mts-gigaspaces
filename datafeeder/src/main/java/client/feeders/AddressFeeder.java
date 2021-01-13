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

import com.devonfw.application.mtsj.clustermanagement.dataaccess.api.AddressEntity;

/**
 * @author dpatesan
 *
 *         Class responsible for creating and storing sample Data for Addresses out of a csv file.
 */
public class AddressFeeder {
  GigaSpace gigaSpace;

  public AddressFeeder(GigaSpace gigaSpace) {

    this.gigaSpace = gigaSpace;
  }

  /**
   * Create and load sample Address data into the space.
   *
   * @throws Exception
   */
  public void loadData() throws Exception {

    List<AddressEntity> addresses = readAddressesFromCSV("src/main/resources/addresses.csv");
    this.gigaSpace.writeMultiple(addresses.toArray());

  }

  private List<AddressEntity> readAddressesFromCSV(String fileName) {

    List<AddressEntity> addresses = new ArrayList<>();
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
        String[] attributes = line.split("(?<!\\\\),");

        AddressEntity address = createAddress(attributes);

        // adding address into ArrayList
        addresses.add(address);

        // read next line before looping
        // if end of file reached, line would be null
        line = br.readLine();
      }

    } catch (IOException ioe) {
      ioe.printStackTrace();
    }

    return addresses;

  }

  private AddressEntity createAddress(String[] metadata) {

    // Extract and Convert the attributes from metadata.
    String userId = metadata[0];
    String street = metadata[1];
    String housenumber = metadata[2];
    String plz = metadata[3];
    String city = metadata[4];
    String country = metadata[5];
    String point = metadata[6].replace("(", "").replace(")", "");
    String[] coordinates = point.split(" ");
    Double longitude = Double.parseDouble(coordinates[1]);
    Double latitude = Double.parseDouble(coordinates[2]);

    // Create and return on Address Object of this metadata
    AddressEntity address = new AddressEntity();

    address.setUserId(userId);
    address.setStreet(street);
    address.setHousenumber(housenumber);
    address.setPlz(plz);
    address.setCity(city);
    address.setCountry(country);
    address.setLatitude(latitude);
    address.setLongitude(longitude);

    return address;
  }

}
