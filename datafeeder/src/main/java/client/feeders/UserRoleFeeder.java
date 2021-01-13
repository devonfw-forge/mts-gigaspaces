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

import com.devonfw.application.mtsj.usermanagement.dataaccess.api.UserRoleEntity;

/**
 * @author dpatesan
 *
 *         Class responsible for creating and storing sample Data for User Roles out of a csv file.
 *
 */
public class UserRoleFeeder {
  private GigaSpace gigaSpace;

  public UserRoleFeeder(GigaSpace gigaSpace) {

    this.gigaSpace = gigaSpace;

  }

  /**
   * Create and load sample UserRole data into the space.
   *
   * @throws Exception
   */
  public void loadData() throws Exception {

    List<UserRoleEntity> userRoles = readUserRolesFromCSV("src/main/resources/userrole.csv");
    this.gigaSpace.writeMultiple(userRoles.toArray());

  }

  private List<UserRoleEntity> readUserRolesFromCSV(String fileName) {

    List<UserRoleEntity> userRoles = new ArrayList<>();
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

        UserRoleEntity userRole = createUserRole(attributes);

        // adding userRole into ArrayList
        userRoles.add(userRole);

        // read next line before looping
        // if end of file reached, line would be null
        line = br.readLine();
      }

    } catch (IOException ioe) {
      ioe.printStackTrace();
    }

    return userRoles;
  }

  /**
   * @param attributes
   * @return
   */
  private UserRoleEntity createUserRole(String[] metadata) {

    // Extract and Convert the attributes from metadata.
    String id = metadata[0];
    String name = metadata[2];
    Integer activeInt = Integer.parseInt(metadata[3]);
    boolean active = false;
    if (activeInt == 1) {
      active = true;
    }
    // create and return userRole of this metadata
    UserRoleEntity userRole = new UserRoleEntity();

    userRole.setId(id);
    userRole.setName(name);
    userRole.setActive(active);

    return userRole;
  }
}
