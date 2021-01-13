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

import com.devonfw.application.mtsj.usermanagement.dataaccess.api.UserEntity;

/**
 * @author dpatesan
 *
 *         Class responsible for creating and storing sample Data for Users out of a csv file.
 */
public class UserFeeder {

  GigaSpace gigaSpace;

  /**
   * The constructor.
   */
  public UserFeeder(GigaSpace gigaSpace) {

    this.gigaSpace = gigaSpace;
  }

  /**
   * Create and load sample Users data into the space.
   *
   * @throws Exception
   */
  public void loadData() throws Exception {

    List<UserEntity> users = readUsersFromCSV("src/main/resources/users.csv");
    this.gigaSpace.writeMultiple(users.toArray());

  }

  private List<UserEntity> readUsersFromCSV(String fileName) {

    List<UserEntity> users = new ArrayList<>();
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

        UserEntity user = createUser(attributes);

        // adding user into ArrayList
        users.add(user);

        // read next line before looping
        // if end of file reached, line would be null
        line = br.readLine();
      }

    } catch (IOException ioe) {
      ioe.printStackTrace();
    }

    return users;
  }

  private UserEntity createUser(String[] metadata) {

    // Extract and Convert the attributes from metadata.
    String id = metadata[0];
    String username = metadata[2];
    String password = metadata[3];
    String email = metadata[6];
    String roleId = metadata[7];

    // create and return user of this metadata
    UserEntity user = new UserEntity();

    user.setId(id);
    user.setUsername(username);
    user.setPassword(password);
    user.setTwoFactorStatus(false);
    user.setEmail(email);
    user.setUserRoleId(roleId);

    return user;
  }

}
