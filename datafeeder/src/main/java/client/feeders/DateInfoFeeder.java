package client.feeders;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.openspaces.core.GigaSpace;

import com.devonfw.application.mtsj.predictionmanagement.dataaccess.api.DateInfoEntity;

/**
 * @author dpatesan
 *
 *         Class responsible for creating and storing sample Data for DateInfo out of a csv file.
 */
public class DateInfoFeeder {
  GigaSpace gigaSpace;

  public DateInfoFeeder(GigaSpace gigaSpace) {

    this.gigaSpace = gigaSpace;
  }

  /**
   * Create and load sample DateInfo data into the space.
   *
   * @throws Exception
   */
  public void loadData() throws Exception {

    List<DateInfoEntity> datesInfo = readDatesInfoFromCSV("src/main/resources/dateinfo.csv");
    this.gigaSpace.writeMultiple(datesInfo.toArray());

  }

  private List<DateInfoEntity> readDatesInfoFromCSV(String fileName) {

    List<DateInfoEntity> datesInfo = new ArrayList<>();
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

        DateInfoEntity dateInfo = createDateInfo(attributes);

        // adding dateInfo into ArrayList
        datesInfo.add(dateInfo);

        // read next line before looping
        // if end of file reached, line would be null
        line = br.readLine();
      }

    } catch (IOException ioe) {
      ioe.printStackTrace();
    }

    return datesInfo;

  }

  private DateInfoEntity createDateInfo(String[] metadata) {

    // Extract and Convert the attributes from metadata.
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    LocalDateTime localDateTime = LocalDate.parse(metadata[0], formatter).atStartOfDay();
    ZonedDateTime zonedDateTime = ZonedDateTime.of(localDateTime, ZoneId.systemDefault());
    Instant date = Instant.from(zonedDateTime);
    double temperature = Double.parseDouble(metadata[1]);

    // Create and return on DateInfo Object of this metadata
    DateInfoEntity dateInfo = new DateInfoEntity();

    // Non-Holiday Days have null value for the designation field.
    try {
      String designation = metadata[2];
      dateInfo.setDesignation(designation);

    } catch (Exception e) {
    }

    dateInfo.setDate(Timestamp.from(date));
    dateInfo.setTemperature(temperature);

    return dateInfo;
  }
}
