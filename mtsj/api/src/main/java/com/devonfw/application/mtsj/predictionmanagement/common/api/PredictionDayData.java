package com.devonfw.application.mtsj.predictionmanagement.common.api;

import com.devonfw.application.mtsj.general.common.api.ApplicationEntity;
import com.devonfw.module.basic.common.api.entity.GenericEntity;

/**
 * Predicted order data for a given dish and a given day
 */
public interface PredictionDayData extends GenericEntity<String>  {

  /**
   * @return the ID of the dish
   */
  public String getDishId();

  /**
   * Set the ID of the dish
   * @param dishId the ID of the dish
   */
  public void setDishId(String dishId);

  /**
   * @return the name of the dish
   */
  public String getDishName();

  /**
   * Set the name of the dish
   * @param dishName the name of the dish
   */
  public void setDishName(String dishName);

  /**
   * @return the point in time of the prediction
   */
  public Integer getTimestamp();

  /**
   * Set the point in time for the prediction
   * @param timestamp the point in time for the prediction
   */
  public void setTimestamp(Integer timestamp);

  /**
   * @return the predicted number of orders
   */
  public Double getForecast();

  /**
   * Set the predicted number of orders
   * @param forecast the predicted number of orders
   */
  public void setForecast(Double forecast);

}
