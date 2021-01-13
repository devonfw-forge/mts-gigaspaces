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
public class DailyOrderedDishesEntity {

  private static final long serialVersionUID = 1L;

  private String id;

  private String dishId;

  private Instant bookingDate;

  private Integer amount;

  private int modificationCounter;

  @SpaceVersion
  public int getModificationCounter() {

    return this.modificationCounter;
  }

  public void setModificationCounter(int version) {

    this.modificationCounter = version;
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

}
