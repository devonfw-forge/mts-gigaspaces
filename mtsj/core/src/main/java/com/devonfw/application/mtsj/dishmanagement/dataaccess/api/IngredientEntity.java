package com.devonfw.application.mtsj.dishmanagement.dataaccess.api;

import java.math.BigDecimal;

import com.devonfw.application.mtsj.dishmanagement.common.api.Ingredient;
import com.devonfw.application.mtsj.general.dataaccess.api.ApplicationPersistenceEntity;
import com.gigaspaces.annotation.pojo.SpaceClass;

/**
 * The {@link com.devonfw.application.mtsj.general.dataaccess.api.ApplicationPersistenceEntity persistent entity} for
 * {@link IngredientEntity}.
 */

@SpaceClass
public class IngredientEntity extends ApplicationPersistenceEntity implements Ingredient {

  private static final long serialVersionUID = 1L;

  private String name;

  private String description;

  private BigDecimal price;

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
   * @return price
   */
  @Override
  public BigDecimal getPrice() {

    return this.price;
  }

  /**
   * @param price new value of {@link #getPrice}.
   */
  @Override
  public void setPrice(BigDecimal price) {

    this.price = price;
  }

}
