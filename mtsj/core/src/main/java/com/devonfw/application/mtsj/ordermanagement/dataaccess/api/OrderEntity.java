package com.devonfw.application.mtsj.ordermanagement.dataaccess.api;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.openspaces.core.GigaSpace;

import com.devonfw.application.mtsj.bookingmanagement.dataaccess.api.BookingEntity;
import com.devonfw.application.mtsj.bookingmanagement.dataaccess.api.InvitedGuestEntity;
import com.devonfw.application.mtsj.general.dataaccess.api.ApplicationPersistenceEntity;
import com.devonfw.application.mtsj.ordermanagement.common.api.Order;
import com.devonfw.application.mtsj.xap.BeanUtils;
import com.gigaspaces.annotation.pojo.SpaceClass;
import com.gigaspaces.annotation.pojo.SpaceExclude;

/**
 * The {@link com.devonfw.application.mtsj.general.dataaccess.api.ApplicationPersistenceEntity persistent entity} for
 * {@link Order}.
 */

@SpaceClass
public class OrderEntity extends ApplicationPersistenceEntity implements Order {

  private static final long serialVersionUID = 1L;

  private BookingEntity booking;

  private InvitedGuestEntity invitedGuest;

  private BookingEntity host;

  private List<OrderLineEntity> orderLines;

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
   * @return invitedGuest
   */

  @SpaceExclude
  public InvitedGuestEntity getInvitedGuest() {

    GigaSpace gigaspace = BeanUtils.getBean(GigaSpace.class);
    InvitedGuestEntity queriedInvitedGuest = gigaspace.readById(InvitedGuestEntity.class, getInvitedGuestId());
    return queriedInvitedGuest;
  }

  /**
   * @param invitedGuest new value of {@link #getinvitedGuest}.
   */
  public void setInvitedGuest(InvitedGuestEntity invitedGuest) {

	  this.invitedGuest = invitedGuest;
  }

  /**
   * @return orderLines
   */
  @SpaceExclude
  public List<OrderLineEntity> getOrderLines() {

    GigaSpace gigaspace = BeanUtils.getBean(GigaSpace.class);
    OrderLineEntity template = new OrderLineEntity();
    template.setOrderId(getId());
    OrderLineEntity[] queriedOrderLines = gigaspace.readMultiple(template, Integer.MAX_VALUE);

    return Arrays.asList(queriedOrderLines);
  }

  /**
   * @param orderLines new value of {@link #getorderLines}.
   */
  public void setOrderLines(List<OrderLineEntity> orderLines) {

    GigaSpace gigaspace = BeanUtils.getBean(GigaSpace.class);
    for (OrderLineEntity orderLine : orderLines) {
      orderLine.setId(UUID.randomUUID().toString());
      gigaspace.write(orderLine);
    }
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

  @Override
  public String getInvitedGuestId() {

    if (this.invitedGuest == null) {
      return null;
    }
    return this.invitedGuest.getId();
  }

  @Override
  public void setInvitedGuestId(String invitedGuestId) {

    if (invitedGuestId == null) {
      this.invitedGuest = null;
    } else {
      InvitedGuestEntity invitedGuestEntity = new InvitedGuestEntity();
      invitedGuestEntity.setId(invitedGuestId);
      this.invitedGuest = invitedGuestEntity;
    }
  }

  /**
   * @return host
   */
  @SpaceExclude
  public BookingEntity getHost() {

    return this.host;
  }

  /**
   * @param host new value of {@link #gethost}.
   */
  public void setHost(BookingEntity host) {

    this.host = host;
  }

  @Override
  public String getHostId() {

    if (this.host == null) {
      return null;
    }
    return this.host.getId();
  }

  @Override
  public void setHostId(String hostId) {

    if (hostId == null) {
      this.host = null;
    } else {
      BookingEntity bookingEntity = new BookingEntity();
      bookingEntity.setId(hostId);
      this.host = bookingEntity;
    }
  }

}
