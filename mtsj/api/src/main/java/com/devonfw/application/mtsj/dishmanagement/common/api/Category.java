package com.devonfw.application.mtsj.dishmanagement.common.api;

import com.devonfw.module.basic.common.api.entity.GenericEntity;

public interface Category extends GenericEntity<String> {

  public String getName();

  public void setName(String name);

  public String getDescription();

  public void setDescription(String description);

  public int getShowOrder();

  public void setShowOrder(int showOrder);

}
