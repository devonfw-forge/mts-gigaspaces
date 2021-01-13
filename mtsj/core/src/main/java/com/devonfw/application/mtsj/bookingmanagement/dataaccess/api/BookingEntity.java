package com.devonfw.application.mtsj.bookingmanagement.dataaccess.api;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;

import org.openspaces.core.GigaSpace;

import com.devonfw.application.mtsj.bookingmanagement.common.api.Booking;
import com.devonfw.application.mtsj.bookingmanagement.common.api.datatype.BookingType;
import com.devonfw.application.mtsj.general.dataaccess.api.ApplicationPersistenceEntity;
import com.devonfw.application.mtsj.ordermanagement.dataaccess.api.OrderEntity;
import com.devonfw.application.mtsj.usermanagement.dataaccess.api.UserEntity;
import com.devonfw.application.mtsj.xap.BeanUtils;
import com.gigaspaces.annotation.pojo.SpaceClass;
import com.gigaspaces.annotation.pojo.SpaceExclude;
import com.gigaspaces.annotation.pojo.SpaceIndex;
import com.gigaspaces.metadata.index.SpaceIndexType;

@SpaceClass
public class BookingEntity extends ApplicationPersistenceEntity implements Booking {

  private String name;

  private String bookingToken;

  private String comment;

  private Instant bookingDate;

  private Instant expirationDate;

  private Instant creationDate;

  private String email;

  private Boolean canceled;

  private BookingType bookingType;

  private TableEntity table;

  private OrderEntity order;

  private UserEntity user;

  private List<InvitedGuestEntity> invitedGuests;

  private List<OrderEntity> orders;

  @Min(value = 1, message = "Assistants must be greater than 0")
  @Digits(integer = 2, fraction = 0)
  private Integer assistants;

  private static final long serialVersionUID = 1L;

  public BookingEntity() {

    super();
    this.canceled = false;
  }

  /**
   * @return name
   */
  @Override
  public String getName() {

    return this.name;
  }

  /**
   * @param name new value of {@link #getname}.
   */
  @Override
  public void setName(String name) {

    this.name = name;
  }

  /**
   * @return bookingToken
   */
  @Override
  @SpaceIndex(type = SpaceIndexType.EQUAL)
  public String getBookingToken() {

    return this.bookingToken;
  }

  /**
   * @param bookingToken new value of {@link #getbookingToken}.
   */
  @Override
  public void setBookingToken(String bookingToken) {

    this.bookingToken = bookingToken;
  }

  /**
   * @return comments
   */
  @Override
  public String getComment() {

    return this.comment;
  }

  /**
   * @param comments new value of {@link #getcomments}.
   */
  @Override
  public void setComment(String comments) {

    this.comment = comments;
  }

  /**
   * @return bookingDate
   */
  @Override
  @SpaceIndex(type = SpaceIndexType.ORDERED)
  public Instant getBookingDate() {

    return this.bookingDate;
  }

  /**
   * @param bookingDate new value of {@link #getbookingDate}.
   */
  @Override
  public void setBookingDate(Instant bookingDate) {

    this.bookingDate = bookingDate;
  }

  /**
   * @return expirationDate
   */
  @Override
  public Instant getExpirationDate() {

    return this.expirationDate;
  }

  /**
   * @param expirationDate new value of {@link #getexpirationDate}.
   */
  @Override
  public void setExpirationDate(Instant expirationDate) {

    this.expirationDate = expirationDate;
  }

  /**
   * @return creationDate
   */
  @Override
  public Instant getCreationDate() {

    return this.creationDate;
  }

  /**
   * @param creationDate new value of {@link #getcreationDate}.
   */
  @Override
  public void setCreationDate(Instant creationDate) {

    this.creationDate = creationDate;
  }

  /**
   * @return canceled
   */
  @Override
  public Boolean getCanceled() {

    return this.canceled;
  }

  /**
   * @param canceled new value of {@link #getcanceled}.
   */
  @Override
  public void setCanceled(Boolean canceled) {

    this.canceled = canceled;
  }

  /**
   * @return table
   */

  @SpaceExclude
  public TableEntity getTable() {

    GigaSpace gigaspace = BeanUtils.getBean(GigaSpace.class);
    TableEntity queriedTable = gigaspace.readById(TableEntity.class, getTableId());
    return queriedTable;
  }

  /**
   * @param table new value of {@link #gettable}.
   */
  public void setTable(TableEntity table) {

    this.table = table;
  }

  /**
   * @return invitedGuests
   */
  @SpaceExclude
  public List<InvitedGuestEntity> getInvitedGuests() {

    GigaSpace gigaspace = BeanUtils.getBean(GigaSpace.class);
    InvitedGuestEntity template = new InvitedGuestEntity();
    template.setBookingId(getId());
    InvitedGuestEntity[] queriedInvitedGuests = gigaspace.readMultiple(template, Integer.MAX_VALUE);

    return Arrays.asList(queriedInvitedGuests);
  }

  /**
   * @param invitedGuests new value of {@link #getinvitedGuests}.
   */
  public void setInvitedGuests(List<InvitedGuestEntity> invitedGuests) {

    GigaSpace gigaspace = BeanUtils.getBean(GigaSpace.class);
    for (InvitedGuestEntity invitedGuest : invitedGuests) {
      invitedGuest.setId(UUID.randomUUID().toString());
      gigaspace.write(invitedGuest);
    }
  }

  /**
   * @return type
   */
  @Override
  public BookingType getBookingType() {

    return this.bookingType;
  }

  /**
   * @param type new value of {@link #gettype}.
   */
  @Override
  public void setBookingType(BookingType bookingType) {

    this.bookingType = bookingType;
  }

  @Override
  public String getEmail() {

    return this.email;
  }

  @Override
  public void setEmail(String email) {

    this.email = email;

  }

  @Override
  public String getTableId() {

    if (this.table == null) {
      return null;
    }
    return this.table.getId();
  }

  @Override
  public void setTableId(String tableId) {

    if (tableId == null) {
      this.table = null;
    } else {
      TableEntity tableEntity = new TableEntity();
      tableEntity.setId(tableId);
      this.table = tableEntity;
    }
  }

  /**
   * @return order
   */
  @SpaceExclude
  public OrderEntity getOrder() {

    GigaSpace gigaspace = BeanUtils.getBean(GigaSpace.class);
    OrderEntity queriedOrder = gigaspace.readById(OrderEntity.class, getOrderId());
    return queriedOrder;
  }

  /**
   * @param order new value of {@link #getorder}.
   */
  public void setOrder(OrderEntity order) {

    this.order = order;
  }

  @Override
  @SpaceIndex(type = SpaceIndexType.EQUAL)
  public String getOrderId() {

    if (this.order == null) {
      return null;
    }
    return this.order.getId();
  }

  @Override
  public void setOrderId(String orderId) {

    if (orderId == null) {
      this.order = null;
    } else {
      OrderEntity orderEntity = new OrderEntity();
      orderEntity.setId(orderId);
      this.order = orderEntity;
    }
  }

  /**
   * @return orders
   */
  @SpaceExclude
  public List<OrderEntity> getOrders() {

    GigaSpace gigaspace = BeanUtils.getBean(GigaSpace.class);
    OrderEntity template = new OrderEntity();
    template.setBookingId(getId());
    OrderEntity[] queriedInvitedGuests = gigaspace.readMultiple(template, Integer.MAX_VALUE);

    return Arrays.asList(queriedInvitedGuests);
  }

  /**
   * @param orders new value of {@link #getorders}.
   */
  public void setOrders(List<OrderEntity> orders) {

    this.orders = orders;
  }

  /**
   * @return assistants
   */
  @Override
  public Integer getAssistants() {

    return this.assistants;
  }

  /**
   * @param assistants new value of {@link #getassistants}.
   */
  @Override
  public void setAssistants(Integer assistants) {

    this.assistants = assistants;
  }

  /**
   * @return user
   */
  @SpaceExclude
  public UserEntity getUser() {

    GigaSpace gigaspace = BeanUtils.getBean(GigaSpace.class);
    UserEntity queriedUser = gigaspace.readById(UserEntity.class, getUserId());
    return queriedUser;
  }

  /**
   * @param user new value of {@link #getuser}.
   */
  public void setUser(UserEntity user) {

    this.user = user;
  }

  @Override
  public String getUserId() {

    if (this.user == null) {
      return null;
    }
    return this.user.getId();
  }

  @Override
  public void setUserId(String userId) {

    if (userId == null) {
      this.user = null;
    } else {
      UserEntity userEntity = new UserEntity();
      userEntity.setId(userId);
      this.user = userEntity;
    }
  }

}
