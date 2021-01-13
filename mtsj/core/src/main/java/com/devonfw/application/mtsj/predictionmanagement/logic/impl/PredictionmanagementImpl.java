package com.devonfw.application.mtsj.predictionmanagement.logic.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import com.devonfw.application.mtsj.general.common.impl.security.ApplicationAccessControlConfig;
import com.devonfw.application.mtsj.general.logic.base.AbstractComponentFacade;
import com.devonfw.application.mtsj.predictionmanagement.common.api.PredictionDayData;
import com.devonfw.application.mtsj.predictionmanagement.common.api.to.PredictionDataTo;
import com.devonfw.application.mtsj.predictionmanagement.common.api.to.PredictionDayDataEto;
import com.devonfw.application.mtsj.predictionmanagement.common.api.to.PredictionSearchCriteriaTo;
import com.devonfw.application.mtsj.predictionmanagement.dataaccess.api.PredictionDayDataEntity;
import com.devonfw.application.mtsj.predictionmanagement.dataaccess.api.PredictionForecastDataEntity;
import com.devonfw.application.mtsj.predictionmanagement.dataaccess.api.repo.PredictionDayDataRepository;
import com.devonfw.application.mtsj.predictionmanagement.dataaccess.api.repo.PredictionForecastDataRepository;
import com.devonfw.application.mtsj.predictionmanagement.logic.api.Predictionmanagement;

/**
 * @author dpatesan
 *
 *         Implementation of component interface of predictionmanagement
 */
@Named
@Transactional
public class PredictionmanagementImpl extends AbstractComponentFacade implements Predictionmanagement {

  /**
   * Logger instance.
   */
  private static final Logger LOG = LoggerFactory.getLogger(PredictionmanagementImpl.class);

  @Inject
  private PredictionForecastDataRepository predictionForecastDataRepository;

  @Inject
  private PredictionDayDataRepository predictionDayDataRepository;

  /**
   * The constructor.
   */
  public PredictionmanagementImpl() {

    super();
  }

  @Override
  @RolesAllowed(ApplicationAccessControlConfig.PERMISSION_FIND_NEXT_WEEK_PREDICTION)
  public PredictionDataTo getNextWeekPrediction(PredictionSearchCriteriaTo criteria) {

    LOG.debug("Generate predictions for next week.");

    // delete old forecast data
    this.predictionForecastDataRepository.deleteAll();

    // add new forecast data
    List<PredictionForecastDataEntity> predictionForecastData = new ArrayList<>();

    for (int i = 0; i < criteria.getTemperatures().size(); i++) {
      PredictionForecastDataEntity forecastData = new PredictionForecastDataEntity();
      forecastData.setId(UUID.randomUUID().toString());
      forecastData.setTimestamp(i + 1);
      forecastData.setTemperature(criteria.getTemperatures().get(i) == null ? 0 : criteria.getTemperatures().get(i));
      forecastData.setHoliday(criteria.getHolidays().get(i) == null ? 0 : 1);
      predictionForecastData.add(forecastData);

    }
    this.predictionForecastDataRepository.saveAll(predictionForecastData);

    // Start the Apache Zeppelin Notebook for Running the ARIMA Models
    ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c",
        "curl -X POST http://localhost:9090/api/notebook/job/2FU9WRG39");

    try {
      Process p = builder.start();
      p.waitFor();
    } catch (IOException | InterruptedException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    PredictionDataTo predictionDataTo = new PredictionDataTo();
    List<PredictionDayData> predictionDayDataEtos = new ArrayList<>();
    List<PredictionDayDataEntity> predictions = new ArrayList<>();

    // Receive the next week predictions from the space and order them by timestamp
    this.predictionDayDataRepository.findAll().forEach(predictions::add);
    predictions.sort(Comparator.comparing(PredictionDayDataEntity::getTimestamp));

    for (PredictionDayDataEntity entity : predictions) {

      PredictionDayDataEto predictionDayDataEto = new PredictionDayDataEto();
      predictionDayDataEto.setId(entity.getId());
      predictionDayDataEto.setDishId(entity.getDishId());
      predictionDayDataEto.setDishName(entity.getDish().getName());
      predictionDayDataEto.setTimestamp(entity.getTimestamp());
      predictionDayDataEto.setForecast(entity.getForecast());
      predictionDayDataEtos.add(predictionDayDataEto);

    }

    predictionDataTo.setData(predictionDayDataEtos);

    return predictionDataTo;
  }

}
