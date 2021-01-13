package com.devonfw.application.mtsj.bookingmanagement.common.api;

import com.devonfw.module.basic.common.api.entity.GenericEntity;

public interface Table extends GenericEntity<String> {

  public Integer getSeatsNumber();

  public void setSeatsNumber(Integer seatsNumber);

}
