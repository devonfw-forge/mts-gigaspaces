package client.feeders;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.ArrayList;
import java.util.List;

import org.openspaces.core.GigaSpace;

import com.devonfw.application.mtsj.bookingmanagement.common.api.datatype.BookingType;
import com.devonfw.application.mtsj.bookingmanagement.dataaccess.api.BookingEntity;

/**
 * @author dpatesan
 *
 *         Class responsible for creating and storing sample Data for Bookings out of a csv file.
 */
public class BookingFeeder {
  GigaSpace gigaSpace;

  public BookingFeeder(GigaSpace gigaSpace) {

    this.gigaSpace = gigaSpace;
  }

  /**
   * Create and load sample Booking data into the Space.
   *
   * @throws Exception
   */
  public void loadData() throws Exception {

    List<BookingEntity> bookings = readBookingsFromCSV("src/main/resources/bookings.csv");
    this.gigaSpace.writeMultiple(bookings.toArray());

  }

  private List<BookingEntity> readBookingsFromCSV(String fileName) {

    List<BookingEntity> bookings = new ArrayList<>();
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

        BookingEntity booking = createBooking(attributes);

        // adding booking into ArrayList
        bookings.add(booking);

        // read next line before looping
        // if end of file reached, line would be null
        line = br.readLine();
      }

    } catch (IOException ioe) {
      ioe.printStackTrace();
    }

    return bookings;

  }

  private BookingEntity createBooking(String[] metadata) {

    // Extract and Convert the attributes from metadata.
    String id = metadata[0];
    String userId = metadata[2];
    String name = metadata[3];
    String email = metadata[6];
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSSS");
    TemporalAccessor temporalAccessor = formatter.parse(metadata[7]);
    LocalDateTime localDateTime = LocalDateTime.from(temporalAccessor);
    ZonedDateTime zonedDateTime = ZonedDateTime.of(localDateTime, ZoneId.systemDefault());
    Instant bookingDate = Instant.from(zonedDateTime);
    String tableId = metadata[12];
    String orderId = metadata[13];
    Integer assistants = Integer.valueOf(metadata[14]);

    // Create and return on Booking Object of this metadata
    BookingEntity booking = new BookingEntity();

    booking.setId(id);
    booking.setUserId(userId);
    booking.setName(name);
    booking.setEmail(email);
    booking.setBookingDate(bookingDate);
    booking.setExpirationDate(bookingDate);
    booking.setCreationDate(bookingDate);
    booking.setCanceled(false);
    booking.setBookingType(BookingType.COMMON);
    booking.setTableId(tableId);
    booking.setOrderId(orderId);
    booking.setAssistants(assistants);

    return booking;
  }
}
