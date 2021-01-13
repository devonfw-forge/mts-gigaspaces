package com.devonfw.application.mtsj.bookingmanagement.common.api;

import java.time.Instant;

import com.devonfw.application.mtsj.bookingmanagement.common.api.datatype.BookingType;
import com.devonfw.module.basic.common.api.entity.GenericEntity;

public interface Booking extends GenericEntity<String> {

  public String getName();

  public void setName(String name);

  public String getBookingToken();

  public void setBookingToken(String bookingToken);

  public String getComment();

  public void setComment(String comment);

  public Instant getBookingDate();

  public void setBookingDate(Instant bookingDate);

  public Instant getExpirationDate();

  public void setExpirationDate(Instant expirationDate);

  public Instant getCreationDate();

  public void setCreationDate(Instant creationDate);

  public String getEmail();

  public void setEmail(String email);

  public Boolean getCanceled();

  public void setCanceled(Boolean canceled);

  public BookingType getBookingType();

  public void setBookingType(BookingType bookingType);

  public String getTableId();

  public void setTableId(String tableId);

  public String getOrderId();

  public void setOrderId(String orderId);

  public Integer getAssistants();

  public void setAssistants(Integer assistants);

  public String getUserId();

  public void setUserId(String userId);

}
