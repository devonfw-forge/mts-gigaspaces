package com.devonfw.application.mtsj.dishmanagement.dataaccess.api;

import com.devonfw.application.mtsj.dishmanagement.common.api.Category;
import com.devonfw.application.mtsj.general.dataaccess.api.ApplicationPersistenceEntity;
import com.gigaspaces.annotation.pojo.SpaceClass;
import com.gigaspaces.annotation.pojo.SpaceProperty;

/**
 * The {@link com.devonfw.application.mtsj.general.dataaccess.api.ApplicationPersistenceEntity persistent entity} for
 * {@link CategoryEntity}.
 */

@SpaceClass
public class CategoryEntity extends ApplicationPersistenceEntity implements Category {

  private static final long serialVersionUID = 1L;

  private String name;

  private String description;

  private int showOrder;

  /**
   * @return name
   */
  @Override
  public String getName() {

    return this.name;
  }

  /**
   * @param name new value of {@link #getName}.
   */
  @Override
  public void setName(String name) {

    this.name = name;
  }

  /**
   * @return description
   */
  @Override
  public String getDescription() {

    return this.description;
  }

  /**
   * @param description new value of {@link #getDescription}.
   */
  @Override
  public void setDescription(String description) {

    this.description = description;
  }

  /**
   * @return showOrder
   */
  @Override
  @SpaceProperty(nullValue = "-1")
  public int getShowOrder() {

    return this.showOrder;
  }

  /**
   * @param showOrder new value of {@link #getShowOrder}.
   */
  @Override
  public void setShowOrder(int showOrder) {

    this.showOrder = showOrder;
  }

}
