package com.devonfw.application.mtsj.ordermanagement.common.api;

import com.devonfw.module.basic.common.api.entity.GenericEntity;

public interface Order extends GenericEntity<String> {

  public String getBookingId();

  public void setBookingId(String bookingId);

  public String getInvitedGuestId();

  public void setInvitedGuestId(String invitedGuestId);

  public String getHostId();

  public void setHostId(String hostId);

}
