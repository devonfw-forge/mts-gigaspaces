package com.devonfw.application.mtsj.predictionmanagement.dataaccess.api;

import com.devonfw.application.mtsj.general.dataaccess.api.ApplicationPersistenceEntity;
import com.devonfw.application.mtsj.predictionmanagement.common.api.PredictionForecastData;
import com.gigaspaces.annotation.pojo.SpaceClass;

@SpaceClass
public class PredictionForecastDataEntity extends ApplicationPersistenceEntity implements PredictionForecastData {

  private int timestamp;

  private double temperature;

  private int holiday;

  @Override
  public int getTimestamp() {

    return this.timestamp;
  }

  @Override
  public void setTimestamp(int timestamp) {

    this.timestamp = timestamp;
  }

  @Override
  public double getTemperature() {

    return this.temperature;
  }

  @Override
  public void setTemperature(double temperature) {

    this.temperature = temperature;
  }

  @Override
  public int getHoliday() {

    return this.holiday;
  }

  @Override
  public void setHoliday(int holiday) {

    this.holiday = holiday;
  }
}
