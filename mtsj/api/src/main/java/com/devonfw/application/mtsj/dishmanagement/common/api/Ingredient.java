package com.devonfw.application.mtsj.dishmanagement.common.api;

import java.math.BigDecimal;

import com.devonfw.module.basic.common.api.entity.GenericEntity;

public interface Ingredient extends GenericEntity<String> {

  public String getName();

  public void setName(String name);

  public String getDescription();

  public void setDescription(String description);

  public BigDecimal getPrice();

  public void setPrice(BigDecimal price);

}
