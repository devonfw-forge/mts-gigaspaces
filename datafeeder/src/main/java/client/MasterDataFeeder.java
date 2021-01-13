package client;

import java.util.UUID;

import org.openspaces.core.GigaSpace;
import org.openspaces.core.GigaSpaceConfigurer;
import org.openspaces.core.space.SpaceProxyConfigurer;

import com.devonfw.application.mtsj.predictionmanagement.dataaccess.api.PredictionDayDataEntity;
import com.devonfw.application.mtsj.predictionmanagement.dataaccess.api.PredictionForecastDataEntity;
import com.gigaspaces.async.AsyncFuture;
import com.j_spaces.core.IJSpace;

import client.feeders.AddressFeeder;
import client.feeders.BookingFeeder;
import client.feeders.CategoryFeeder;
import client.feeders.DateInfoFeeder;
import client.feeders.DishFeeder;
import client.feeders.ImageFeeder;
import client.feeders.IngredientFeeder;
import client.feeders.OrderLineFeeder;
import client.feeders.OrdersFeeder;
import client.feeders.TableFeeder;
import client.feeders.UserFeeder;
import client.feeders.UserRoleFeeder;
import client.tasks.DailyOrderedDishesTask;
import client.tasks.GeobookingsTask;
import client.tasks.MonthlyOrderedDishesTask;
import client.tasks.OrderedDishesPerDayTask;
import client.tasks.OrderedDishesPerMonthTask;

/**
 * @author dpatesan
 *
 */

public class MasterDataFeeder {

  public static void main(String[] args) {

    // Get a proxy to the space using a configurer

    SpaceProxyConfigurer spaceConfigurer = new SpaceProxyConfigurer("CG-space");
    IJSpace space = spaceConfigurer.space();

    // Create a space proxy

    GigaSpace gigaSpace = new GigaSpaceConfigurer(space).gigaSpace();

    try {

      // Write data into the space
      CategoryFeeder categoryFeeder = new CategoryFeeder(gigaSpace);
      categoryFeeder.loadData();

      TableFeeder tableFeeder = new TableFeeder(gigaSpace);
      tableFeeder.loadData();

      IngredientFeeder ingredientFeeder = new IngredientFeeder(gigaSpace);
      ingredientFeeder.loadData();

      UserRoleFeeder userRoleFeeder = new UserRoleFeeder(gigaSpace);
      userRoleFeeder.loadData();

      ImageFeeder imageFeeder = new ImageFeeder(gigaSpace);
      imageFeeder.loadData();

      UserFeeder userFeeder = new UserFeeder(gigaSpace);
      userFeeder.loadData();

      BookingFeeder bookingFeeder = new BookingFeeder(gigaSpace);
      bookingFeeder.loadData();

      OrdersFeeder ordersFeeder = new OrdersFeeder(gigaSpace);
      ordersFeeder.loadData();

      DishFeeder dishFeeder = new DishFeeder(gigaSpace);
      dishFeeder.loadData();

      OrderLineFeeder orderLineFeeder = new OrderLineFeeder(gigaSpace);
      orderLineFeeder.loadData();

      AddressFeeder addressFeeder = new AddressFeeder(gigaSpace);
      addressFeeder.loadData();

      DateInfoFeeder dateInfoFeeder = new DateInfoFeeder(gigaSpace);
      dateInfoFeeder.loadData();

      // Executing the DistributedTasks

      AsyncFuture<Integer> geobookingsResult = gigaSpace.execute(new GeobookingsTask());
      geobookingsResult.get();

      AsyncFuture<Integer> dailyOrderedResult = gigaSpace.execute(new DailyOrderedDishesTask());
      dailyOrderedResult.get();

      AsyncFuture<Integer> orderedDishesPerDay = gigaSpace.execute(new OrderedDishesPerDayTask());
      orderedDishesPerDay.get();

      AsyncFuture<Integer> monthlyOrderedResult = gigaSpace.execute(new MonthlyOrderedDishesTask());
      monthlyOrderedResult.get();

      AsyncFuture<Integer> orderedDishesPerMonth = gigaSpace.execute(new OrderedDishesPerMonthTask());
      orderedDishesPerMonth.get();

      // Storing sample entries in the Space so that Apache Spark knows how the class looks like.

      PredictionForecastDataEntity forecastData = new PredictionForecastDataEntity();
      forecastData.setId(UUID.randomUUID().toString());
      forecastData.setTimestamp(0);
      forecastData.setTemperature(0.0);
      forecastData.setHoliday(0);
      gigaSpace.write(forecastData);

      PredictionDayDataEntity predictionData = new PredictionDayDataEntity();
      predictionData.setId(UUID.randomUUID().toString());
      predictionData.setDishId("0");
      predictionData.setForecast(0.0);
      predictionData.setTimestamp(0);
      gigaSpace.write(predictionData);

    } catch (Exception ex) {
      ex.printStackTrace();
    }

    spaceConfigurer.close();

  }

}
