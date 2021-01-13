package com.devonfw.application.mtsj.predictionmanagement.dataaccess.api.repo;

import java.util.List;

import org.springframework.data.gigaspaces.repository.Query;

import com.devonfw.application.mtsj.predictionmanagement.dataaccess.api.PredictionDayDataEntity;
import com.devonfw.application.mtsj.xap.DefaultXapRepository;
import com.devonfw.module.jpa.dataaccess.api.data.DefaultRepository;

/***
 * {@link DefaultRepository} for {@link PredictionDayDataEntity}.
 */
public interface PredictionDayDataRepository extends DefaultXapRepository<PredictionDayDataEntity> {
  @Query("order by timestamp")
  List<PredictionDayDataEntity> findAllOrderedByTimestamp();

}
