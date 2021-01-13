package com.devonfw.application.mtsj.bookingmanagement.common.api;

import java.time.Instant;

import com.devonfw.module.basic.common.api.entity.GenericEntity;

public interface InvitedGuest extends GenericEntity<String> {

  public String getBookingId();

  public void setBookingId(String bookingId);

  public String getGuestToken();

  public void setGuestToken(String guestToken);

  public String getEmail();

  public void setEmail(String email);

  public Boolean getAccepted();

  public void setAccepted(Boolean accepted);

  public Instant getModificationDate();

  public void setModificationDate(Instant modificationDate);

}
