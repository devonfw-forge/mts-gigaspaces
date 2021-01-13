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

import com.devonfw.application.mtsj.dishmanagement.dataaccess.api.CategoryEntity;

/**
 * @author dpatesan
 *
 *         Class responsible for creating and storing sample Data for Categories out of a csv file.
 *
 */
public class CategoryFeeder {
  private GigaSpace gigaSpace;

  public CategoryFeeder(GigaSpace gigaSpace) {

    this.gigaSpace = gigaSpace;
  }

  /**
   * Create and load sample Category data into the space.
   *
   * @throws Exception
   */
  public void loadData() throws Exception {

    List<CategoryEntity> categories = readCategoriesFromCSV("src/main/resources/category.csv");
    this.gigaSpace.writeMultiple(categories.toArray());

  }

  /**
   * @param string
   * @return
   */
  private List<CategoryEntity> readCategoriesFromCSV(String fileName) {

    List<CategoryEntity> categories = new ArrayList<>();
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

        CategoryEntity category = createCategory(attributes);

        // adding category into ArrayList
        categories.add(category);

        // read next line before looping
        // if end of file reached, line would be null
        line = br.readLine();
      }

    } catch (IOException ioe) {
      ioe.printStackTrace();
    }

    return categories;
  }

  /**
   * @param attributes
   * @return
   */
  private CategoryEntity createCategory(String[] metadata) {

    // Extract and Convert the attributes from metadata.
    String id = metadata[0];
    String name = metadata[2];
    String description = metadata[3];
    Integer showOrder = Integer.parseInt(metadata[4]);

    // create and return Category Object of this metadata
    CategoryEntity category = new CategoryEntity();

    category.setId(id);
    category.setName(name);
    category.setDescription(description);
    category.setShowOrder(showOrder.intValue());

    return category;
  }
}
