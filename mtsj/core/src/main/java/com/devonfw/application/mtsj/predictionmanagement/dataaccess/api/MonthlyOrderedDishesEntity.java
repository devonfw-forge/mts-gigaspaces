package com.devonfw.application.mtsj.predictionmanagement.dataaccess.api;

import java.time.Instant;

import com.gigaspaces.annotation.pojo.SpaceClass;
import com.gigaspaces.annotation.pojo.SpaceId;
import com.gigaspaces.annotation.pojo.SpaceRouting;
import com.gigaspaces.annotation.pojo.SpaceVersion;

/**
 * @author dpatesan
 *
 */
@SpaceClass
public class MonthlyOrderedDishesEntity {
  /**
   *
   */
  private static final long serialVersionUID = 1L;

  private String id;

  private String dishId;

  private Instant bookingDate;

  private Integer amount;

  private Double temperature;

  private String yearmonth;

  private Integer versionId;

  @SpaceVersion
  public Integer getVersionId() {

    return this.versionId;
  }

  public void setVersionId(Integer versionId) {

    this.versionId = versionId;
  }

  /**
   * @return id
   */
  @SpaceId(autoGenerate = true)
  @SpaceRouting
  public String getId() {

    return this.id;
  }

  /**
   * @param id new value of {@link #getid}.
   */
  public void setId(String id) {

    this.id = id;
  }

  /**
   * @return dishId
   */
  public String getDishId() {

    return this.dishId;
  }

  /**
   * @param dishId new value of {@link #getdishId}.
   */
  public void setDishId(String dishId) {

    this.dishId = dishId;
  }

  /**
   * @return bookingDate
   */
  public Instant getBookingDate() {

    return this.bookingDate;
  }

  /**
   * @param bookingDate new value of {@link #getbookingDate}.
   */
  public void setBookingDate(Instant bookingDate) {

    this.bookingDate = bookingDate;
  }

  /**
   * @return amount
   */
  public Integer getAmount() {

    return this.amount;
  }

  /**
   * @param amount new value of {@link #getamount}.
   */
  public void setAmount(Integer amount) {

    this.amount = amount;
  }

  /**
   * @return temperature
   */
  public Double getTemperature() {

    return this.temperature;
  }

  /**
   * @param temperature new value of {@link #gettemperature}.
   */
  public void setTemperature(Double temperature) {

    this.temperature = temperature;
  }

  public String getYearmonth() {

    return this.yearmonth;
  }

  public void setYearmonth(String yearmonth) {

    this.yearmonth = yearmonth;
  }

}
