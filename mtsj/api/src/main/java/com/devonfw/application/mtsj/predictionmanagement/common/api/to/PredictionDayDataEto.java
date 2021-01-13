package com.devonfw.application.mtsj.predictionmanagement.common.api.to;

import com.devonfw.application.mtsj.general.common.api.to.AbstractEtoString;
import com.devonfw.application.mtsj.predictionmanagement.common.api.PredictionDayData;

/**
 * Entity transport object of PredictionDayData
 */
public class PredictionDayDataEto extends AbstractEtoString implements PredictionDayData {

  private static final long serialVersionUID = 1L;

  private String dishId;

  private String dishName;

  private Integer timestamp;

  private Double forecast;

  @Override
  public String getDishId() {

    return this.dishId;
  }

  @Override
  public void setDishId(String dishId) {

    this.dishId = dishId;
  }

  @Override
  public String getDishName() {

    return this.dishName;
  }

  @Override
  public void setDishName(String dishName) {

    this.dishName = dishName;
  }

  @Override
  public Integer getTimestamp() {

    return this.timestamp;
  }

  @Override
  public void setTimestamp(Integer timestamp) {

    this.timestamp = timestamp;
  }

  @Override
  public Double getForecast() {

    return this.forecast;
  }

  @Override
  public void setForecast(Double forecast) {

    this.forecast = forecast;
  }

}
