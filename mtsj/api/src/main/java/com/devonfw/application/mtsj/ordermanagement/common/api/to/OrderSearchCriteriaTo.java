package com.devonfw.application.mtsj.ordermanagement.common.api.to;

import com.devonfw.application.mtsj.general.common.api.to.AbstractSearchCriteriaTo;
import com.devonfw.module.basic.common.api.query.StringSearchConfigTo;

/**
 * used to find {@link com.devonfw.application.mtsj.ordermanagement.common.api.Order}s.
 */
public class OrderSearchCriteriaTo extends AbstractSearchCriteriaTo {

  private static final long serialVersionUID = 1L;

  private String bookingId;

  private String invitedGuestId;

  private String hostToken;

  private String hostId;

  private String email;

  private String bookingToken;

  private StringSearchConfigTo hostTokenOption;

  private StringSearchConfigTo emailOption;

  private StringSearchConfigTo bookingTokenOption;

  /**
   * The constructor.
   */
  public OrderSearchCriteriaTo() {

    super();
  }

  public String getBookingId() {

    return this.bookingId;
  }

  public void setBookingId(String bookingId) {

    this.bookingId = bookingId;
  }

  public String getInvitedGuestId() {

    return this.invitedGuestId;
  }

  public void setInvitedGuestId(String invitedGuestId) {

    this.invitedGuestId = invitedGuestId;
  }

  public String getHostToken() {

    return this.hostToken;
  }

  public void setHostToken(String hostToken) {

    this.hostToken = hostToken;
  }

  public String getHostId() {

    return this.hostId;
  }

  public void setHostId(String hostId) {

    this.hostId = hostId;
  }

  /**
   * @return email
   */
  public String getEmail() {

    return this.email;
  }

  /**
   * @param email new value of {@link #getEmail}.
   */
  public void setEmail(String email) {

    this.email = email;
  }

  /**
   * @return bookingToken
   */
  public String getBookingToken() {

    return this.bookingToken;
  }

  /**
   * @param bookingToken new value of {@link #getBookingToken}.
   */
  public void setBookingToken(String bookingToken) {

    this.bookingToken = bookingToken;
  }

  /**
   * @return hostTokenOption
   */
  public StringSearchConfigTo getHostTokenOption() {

    return this.hostTokenOption;
  }

  /**
   * @param hostTokenOption new value of {@link #gethostTokenOption}.
   */
  public void setHostTokenOption(StringSearchConfigTo hostTokenOption) {

    this.hostTokenOption = hostTokenOption;
  }

  /**
   * @return emailOption
   */
  public StringSearchConfigTo getEmailOption() {

    return this.emailOption;
  }

  /**
   * @param emailOption new value of {@link #getemailOption}.
   */
  public void setEmailOption(StringSearchConfigTo emailOption) {

    this.emailOption = emailOption;
  }

  /**
   * @return bookingTokenOption
   */
  public StringSearchConfigTo getBookingTokenOption() {

    return this.bookingTokenOption;
  }

  /**
   * @param bookingTokenOption new value of {@link #getbookingTokenOption}.
   */
  public void setBookingTokenOption(StringSearchConfigTo bookingTokenOption) {

    this.bookingTokenOption = bookingTokenOption;
  }

}
