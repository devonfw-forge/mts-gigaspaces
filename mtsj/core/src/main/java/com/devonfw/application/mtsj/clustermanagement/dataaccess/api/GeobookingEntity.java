package com.devonfw.application.mtsj.clustermanagement.dataaccess.api;

import com.gigaspaces.annotation.pojo.SpaceClass;
import com.gigaspaces.annotation.pojo.SpaceId;
import com.gigaspaces.annotation.pojo.SpaceIndex;
import com.gigaspaces.annotation.pojo.SpaceRouting;
import com.gigaspaces.annotation.pojo.SpaceVersion;
import com.gigaspaces.metadata.index.SpaceIndexType;

/**
 * @author dpatesan
 *
 */
@SpaceClass
public class GeobookingEntity {

  private String id;

  private String dishId;

  private String dishName;

  private Integer amount;

  private Long bookingDate;

  private Double latitude;

  private Double longitude;

  private Integer prediction;

  private int modificationCounter;

  @SpaceId(autoGenerate = true)
  @SpaceRouting
  public String getId() {

    return this.id;
  }

  public void setId(String id) {

    this.id = id;
  }

  @SpaceIndex(type = SpaceIndexType.EQUAL)
  public String getDishId() {

    return this.dishId;
  }

  public void setDishId(String dishId) {

    this.dishId = dishId;
  }

  public String getDishName() {

    return this.dishName;
  }

  public void setDishName(String dishName) {

    this.dishName = dishName;
  }

  public Integer getAmount() {

    return this.amount;
  }

  public void setAmount(Integer amount) {

    this.amount = amount;
  }

  @SpaceIndex(type = SpaceIndexType.ORDERED)
  public Long getBookingDate() {

    return this.bookingDate;
  }

  public void setBookingDate(Long bookingDate) {

    this.bookingDate = bookingDate;
  }

  public Double getLatitude() {

    return this.latitude;
  }

  public void setLatitude(Double latitude) {

    this.latitude = latitude;
  }

  public Double getLongitude() {

    return this.longitude;
  }

  public void setLongitude(Double longitude) {

    this.longitude = longitude;
  }

  @SpaceIndex(type = SpaceIndexType.EQUAL)
  public Integer getPrediction() {

    return this.prediction;
  }

  public void setPrediction(Integer prediction) {

    this.prediction = prediction;
  }

  @SpaceVersion
  public int getModificationCounter() {

    return this.modificationCounter;
  }

  public void setModificationCounter(int version) {

    this.modificationCounter = version;
  }

}
