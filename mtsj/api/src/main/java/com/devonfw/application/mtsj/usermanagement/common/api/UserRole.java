package com.devonfw.application.mtsj.usermanagement.common.api;

import com.devonfw.module.basic.common.api.entity.GenericEntity;

public interface UserRole extends GenericEntity<String> {

  public String getName();

  public void setName(String name);

  public Boolean getActive();

  public void setActive(Boolean active);

}
