package com.devonfw.application.mtsj.bookingmanagement.dataaccess.api;

import java.time.Instant;

import org.openspaces.core.GigaSpace;

import com.devonfw.application.mtsj.bookingmanagement.common.api.InvitedGuest;
import com.devonfw.application.mtsj.general.dataaccess.api.ApplicationPersistenceEntity;
import com.devonfw.application.mtsj.ordermanagement.dataaccess.api.OrderEntity;
import com.devonfw.application.mtsj.xap.BeanUtils;
import com.gigaspaces.annotation.pojo.SpaceClass;
import com.gigaspaces.annotation.pojo.SpaceExclude;
import com.gigaspaces.annotation.pojo.SpaceIndex;
import com.gigaspaces.metadata.index.SpaceIndexType;

@SpaceClass
public class InvitedGuestEntity extends ApplicationPersistenceEntity implements InvitedGuest {

  private BookingEntity booking;

  private String guestToken;

  private String email;

  private Boolean accepted;

  private Instant modificationDate;

  private OrderEntity order;

  private static final long serialVersionUID = 1L;

  public InvitedGuestEntity() {

    super();
  }

  /**
   * @return booking
   */
  @SpaceExclude
  public BookingEntity getBooking() {

    GigaSpace gigaspace = BeanUtils.getBean(GigaSpace.class);
    BookingEntity queriedBooking = gigaspace.readById(BookingEntity.class, getBookingId());
    return queriedBooking;
  }

  /**
   * @param booking new value of {@link #getbooking}.
   */
  public void setBooking(BookingEntity booking) {

    this.booking = booking;
  }

  /**
   * @return guestToken
   */
  @Override
  @SpaceIndex(type = SpaceIndexType.EQUAL)
  public String getGuestToken() {

    return this.guestToken;
  }

  /**
   * @param guestToken new value of {@link #getguestToken}.
   */
  @Override
  public void setGuestToken(String guestToken) {

    this.guestToken = guestToken;
  }

  /**
   * @return email
   */
  @Override
  public String getEmail() {

    return this.email;
  }

  /**
   * @param email new value of {@link #getemail}.
   */
  @Override
  public void setEmail(String email) {

    this.email = email;
  }

  /**
   * @return accepted
   */
  @Override
  public Boolean getAccepted() {

    return this.accepted;
  }

  /**
   * @param accepted new value of {@link #getaccepted}.
   */
  @Override
  public void setAccepted(Boolean accepted) {

    this.accepted = accepted;
  }

  /**
   * @return modificationDate
   */
  @Override
  public Instant getModificationDate() {

    return this.modificationDate;
  }

  /**
   * @param modificationDate new value of {@link #getmodificationDate}.
   */
  @Override
  public void setModificationDate(Instant modificationDate) {

    this.modificationDate = modificationDate;
  }

  @Override
  public String getBookingId() {

    if (this.booking == null) {
      return null;
    }
    return this.booking.getId();
  }

  @Override
  public void setBookingId(String bookingId) {

    if (bookingId == null) {
      this.booking = null;
    } else {
      BookingEntity bookingEntity = new BookingEntity();
      bookingEntity.setId(bookingId);
      this.booking = bookingEntity;
    }
  }

  /**
   * @return order
   */
  @SpaceExclude
  public OrderEntity getOrder() {

    GigaSpace gigaspace = BeanUtils.getBean(GigaSpace.class);
    OrderEntity queriedBooking = gigaspace.readById(OrderEntity.class, getOrderId());
    return queriedBooking;
  }

  /**
   * @param order new value of {@link #getorder}.
   */
  public void setOrder(OrderEntity order) {

    this.order = order;
  }

  public String getOrderId() {

    if (this.order == null) {
      return null;
    }
    return this.order.getId();
  }

  public void setOrderId(String orderId) {

    if (orderId == null) {
      this.order = null;
    } else {
      OrderEntity orderEntity = new OrderEntity();
      orderEntity.setId(orderId);
      this.order = orderEntity;
    }
  }

}
