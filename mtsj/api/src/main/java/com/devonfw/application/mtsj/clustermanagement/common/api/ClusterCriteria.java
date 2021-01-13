package com.devonfw.application.mtsj.clustermanagement.common.api;

import java.sql.Timestamp;

import com.devonfw.application.mtsj.general.common.api.ApplicationEntity;

public interface ClusterCriteria extends ApplicationEntity {

  public Timestamp getStartBookingdate();

  public void setStartBookingdate(Timestamp startBookingdate);

  public Timestamp getEndBookingdate();

  public void setEndBookingdate(Timestamp endBookingdate);

  public String getDishId();

  public void setDishId(String dishId);

  public Integer getClusters();

  public void setClusters(Integer clusters);

}
