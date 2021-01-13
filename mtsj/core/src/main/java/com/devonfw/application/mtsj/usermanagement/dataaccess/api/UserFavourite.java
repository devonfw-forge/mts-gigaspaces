package com.devonfw.application.mtsj.usermanagement.dataaccess.api;

import com.devonfw.application.mtsj.general.dataaccess.api.ApplicationPersistenceEntity;

public class UserFavourite extends ApplicationPersistenceEntity {

  private static final long serialVersionUID = 1L;

  private String idUser;

  private String idDish;

  /**
   * @return idUser
   */
  public String getIdUser() {

    return this.idUser;
  }

  /**
   * @param idUser new value of {@link #getidUser}.
   */
  public void setIdUser(String idUser) {

    this.idUser = idUser;
  }

  /**
   * @return idDish
   */
  public String getIdDish() {

    return this.idDish;
  }

  /**
   * @param idDish new value of {@link #getidDish}.
   */
  public void setIdDish(String idDish) {

    this.idDish = idDish;
  }

}
