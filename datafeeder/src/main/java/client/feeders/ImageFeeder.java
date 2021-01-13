package client.feeders;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;

import org.openspaces.core.GigaSpace;

import com.devonfw.application.mtsj.imagemanagement.common.api.datatype.ContentType;
import com.devonfw.application.mtsj.imagemanagement.dataaccess.api.ImageEntity;

/**
 * @author dpatesan
 *
 *         Class responsible for creating and storing sample Data for Images out of a csv file.
 */
public class ImageFeeder {
  GigaSpace gigaSpace;

  public ImageFeeder(GigaSpace gigaSpace) {

    this.gigaSpace = gigaSpace;
  }

  /**
   * @throws Exception
   *
   *         Create and load sample Image data into the space.
   */
  public void loadData() throws Exception {

    List<ImageEntity> images = readImagesFromCSV("src/main/resources/image.csv");
    this.gigaSpace.writeMultiple(images.toArray());

  }

  private List<ImageEntity> readImagesFromCSV(String fileName) {

    List<ImageEntity> images = new ArrayList<>();
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

        ImageEntity image = createImage(attributes);

        // adding image into ArrayList
        images.add(image);

        // read next line before looping
        // if end of file reached, line would be null
        line = br.readLine();
      }

    } catch (IOException ioe) {
      ioe.printStackTrace();
    }

    return images;

  }

  private ImageEntity createImage(String[] metadata) {

    // Extract and Convert the attributes from metadata.
    String id = metadata[0];
    String contentAsString = metadata[3];
    String name = metadata[2];
    String mimeType = metadata[5];
    Blob content = null;
    byte[] decodedByte = Base64.getDecoder().decode(contentAsString);
    try {
      content = new SerialBlob(decodedByte);
    } catch (SerialException e) {
      e.printStackTrace();
    } catch (SQLException e) {
      e.printStackTrace();
    }

    // Create and return on Image Object of this metadata
    ImageEntity image = new ImageEntity();

    image.setId(id);
    image.setName(name);
    image.setContent(content);
    image.setContentType(ContentType.Binary);
    image.setMimeType(mimeType);

    return image;
  }
}
