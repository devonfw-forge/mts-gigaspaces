package com.devonfw.application.mtsj.usermanagement.common.api;

import com.devonfw.module.basic.common.api.entity.GenericEntity;

public interface User extends GenericEntity<String> {

  public String getUsername();

  public void setUsername(String username);

  public String getEmail();

  public void setEmail(String email);

  public String getUserRoleId();

  public void setUserRoleId(String userRoleId);

  public boolean getTwoFactorStatus();

  public void setTwoFactorStatus(boolean twoFactorStatus);

}
