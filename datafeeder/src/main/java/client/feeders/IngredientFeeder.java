package client.feeders;

import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.openspaces.core.GigaSpace;

import com.devonfw.application.mtsj.dishmanagement.dataaccess.api.IngredientEntity;

/**
 * @author dpatesan
 *
 *         Class responsible for creating and storing sample Data for Ingredients out of a csv file.
 */
public class IngredientFeeder {
  private GigaSpace gigaSpace;

  /**
   * The constructor.
   *
   * @param gigaSpace
   */
  public IngredientFeeder(GigaSpace gigaSpace) {

    this.gigaSpace = gigaSpace;
  }

  /**
   * Create and load sample Ingredient data into the space.
   *
   * @throws Exception
   */
  public void loadData() throws Exception {

    List<IngredientEntity> ingredients = readIngredientsFromCSV("src/main/resources/ingredient.csv");
    this.gigaSpace.writeMultiple(ingredients.toArray());

  }

  private List<IngredientEntity> readIngredientsFromCSV(String fileName) {

    List<IngredientEntity> ingredients = new ArrayList<>();
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

        IngredientEntity ingredient = createIngredient(attributes);

        // adding ingredient into ArrayList
        ingredients.add(ingredient);

        // read next line before looping
        // if end of file reached, line would be null
        line = br.readLine();
      }

    } catch (IOException ioe) {
      ioe.printStackTrace();
    }

    return ingredients;
  }

  /**
   * @param attributes
   * @return
   */
  private IngredientEntity createIngredient(String[] metadata) {

    // Extract and Convert the attributes from metadata.
    String id = metadata[0];
    String name = metadata[2];
    String description = metadata[3].replace("\\", "");
    BigDecimal price = new BigDecimal(metadata[4]);

    // create and return ingredient of this metadata
    IngredientEntity ingredient = new IngredientEntity();
    ingredient.setId(id);
    ingredient.setName(name);
    ingredient.setDescription(description);
    ingredient.setPrice(price);

    return ingredient;
  }

}
