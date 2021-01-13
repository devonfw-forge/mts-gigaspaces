package com.devonfw.application.mtsj.ordermanagement.common.api.to;

import com.devonfw.application.mtsj.general.common.api.to.AbstractEtoString;
import com.devonfw.application.mtsj.ordermanagement.common.api.Order;

/**
 * Entity transport object of Order
 */
public class OrderEto extends AbstractEtoString implements Order {

  private static final long serialVersionUID = 1L;

  private String bookingId;

  private String invitedGuestId;

  private String bookingToken;

  /**
   * @return bookingToken
   */
  public String getBookingToken() {

    return this.bookingToken;
  }

  /**
   * @param bookingToken new value of {@link #getbookingToken}.
   */
  public void setBookingToken(String bookingToken) {

    this.bookingToken = bookingToken;
  }

  private String hostId;

  @Override
  public String getBookingId() {

    return this.bookingId;
  }

  @Override
  public void setBookingId(String bookingId) {

    this.bookingId = bookingId;
  }

  @Override
  public String getInvitedGuestId() {

    return this.invitedGuestId;
  }

  @Override
  public void setInvitedGuestId(String invitedGuestId) {

    this.invitedGuestId = invitedGuestId;
  }

  @Override
  public int hashCode() {

    final int prime = 31;
    int result = super.hashCode();

    result = prime * result + ((this.bookingId == null) ? 0 : this.bookingId.hashCode());

    result = prime * result + ((this.invitedGuestId == null) ? 0 : this.invitedGuestId.hashCode());

    return result;
  }

  @Override
  public boolean equals(Object obj) {

    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    // class check will be done by super type EntityTo!
    if (!super.equals(obj)) {
      return false;
    }
    OrderEto other = (OrderEto) obj;

    if (this.bookingId == null) {
      if (other.bookingId != null) {
        return false;
      }
    } else if (!this.bookingId.equals(other.bookingId)) {
      return false;
    }

    if (this.invitedGuestId == null) {
      if (other.invitedGuestId != null) {
        return false;
      }
    } else if (!this.invitedGuestId.equals(other.invitedGuestId)) {
      return false;
    }

    return true;
  }

  @Override
  public String getHostId() {

    return this.hostId;
  }

  @Override
  public void setHostId(String hostId) {

    this.hostId = hostId;
  }

}
