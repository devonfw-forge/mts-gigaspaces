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

import com.devonfw.application.mtsj.dishmanagement.dataaccess.api.CategoryEntity;
import com.devonfw.application.mtsj.dishmanagement.dataaccess.api.DishEntity;
import com.devonfw.application.mtsj.dishmanagement.dataaccess.api.IngredientEntity;

/**
 * @author dpatesan
 *
 *         Class responsible for creating and storing sample Data for Dishes out of a csv file.
 *
 */
public class DishFeeder {
  private GigaSpace gigaSpace;

  /**
   * The constructor.
   *
   * @param gigaSpace
   */
  public DishFeeder(GigaSpace gigaSpace) {

    this.gigaSpace = gigaSpace;
  }

  /**
   * @throws Exception
   *
   *         Create and load sample Dish data into the space.
   */
  public void loadData() throws Exception {

    List<DishEntity> dishes = readDishesFromCSV("src/main/resources/dish.csv");
    this.gigaSpace.writeMultiple(dishes.toArray());

  }

  private List<DishEntity> readDishesFromCSV(String fileName) {

    List<DishEntity> dishes = new ArrayList<>();
    Path pathToFile = Paths.get(fileName);
    // create an instance of BufferedReader
    // using try with resource, Java 7 feature to close resources
    try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.UTF_8)) {

      // read the first line from the text file
      String line = br.readLine();

      // loop until all lines are read
      while (line != null) {

        // use string.split to load a string array with the values from
        // each line of the file, using a comma as the delimiter
        line = line.replace("\"", "");
        String[] attributes = line.split("(?<!\\\\),");

        DishEntity dish = createDish(attributes);

        // adding dish into ArrayList
        dishes.add(dish);

        // read next line before looping
        // if end of file reached, line would be null
        line = br.readLine();
      }

    } catch (IOException ioe) {
      ioe.printStackTrace();
    }

    return dishes;

  }

  private DishEntity createDish(String[] metadata) {

    // Extract and Convert the attributes from metadata.
    String id = metadata[0];
    String description = metadata[3].replace("\\", "");
    String name = metadata[2];
    BigDecimal price = new BigDecimal(metadata[4]);
    String imageId = metadata[5];
    List<CategoryEntity> categories = getCategories(id);
    List<IngredientEntity> extras = getExtras(id);

    // create and return dish of this metadata
    DishEntity dish = new DishEntity();
    dish.setId(id);
    dish.setName(name);
    dish.setDescription(description);
    dish.setPrice(price);
    dish.setImageId(imageId);
    dish.setCategories(categories);
    dish.setExtras(extras);

    return dish;
  }

  /**
   * Query Categories from the space that belong to a specific Dish based on the csv file that stores the relationship
   * between dishes and categories.
   */
  private List<CategoryEntity> getCategories(String dishId) {

    List<CategoryEntity> categories = new ArrayList<>();
    Path pathToFile = Paths.get("src/main/resources/dishcategory.csv");
    try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.UTF_8)) {
      String line = br.readLine();
      while (line != null) {
        line = line.replace("\"", "");
        String[] attributes = line.split(",");

        String dishIdFromCsv = attributes[2];
        String categoryId = attributes[3];
        if (dishIdFromCsv.equals(dishId)) {
          CategoryEntity category = new CategoryEntity();
          category = this.gigaSpace.readById(CategoryEntity.class, categoryId);
          categories.add(category);
        }

        line = br.readLine();
      }

    } catch (IOException ioe) {
      ioe.printStackTrace();
    }

    return categories;

  }

  /**
   * Query the additional Ingrendients from the space that can be added to a specific Dish based on the csv file that
   * stores the relationship between dishes and ingredients.
   */
  private List<IngredientEntity> getExtras(String dishId) {

    List<IngredientEntity> extras = new ArrayList<>();
    Path pathToFile = Paths.get("src/main/resources/dishingredient.csv");
    try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.UTF_8)) {
      String line = br.readLine();
      while (line != null) {
        line = line.replace("\"", "");
        String[] attributes = line.split(",");

        String dishIdFromCsv = attributes[2];
        String ingredientId = attributes[3];
        if (dishIdFromCsv.equals(dishId)) {
          IngredientEntity ingredient = new IngredientEntity();
          ingredient = this.gigaSpace.readById(IngredientEntity.class, ingredientId);
          extras.add(ingredient);
        }

        line = br.readLine();
      }

    } catch (IOException ioe) {
      ioe.printStackTrace();
    }
    return extras;

  }
}
