package com.devonfw.application.mtsj.usermanagement.dataaccess.api;

import java.util.Arrays;
import java.util.List;

import org.openspaces.core.GigaSpace;

import com.devonfw.application.mtsj.bookingmanagement.dataaccess.api.BookingEntity;
import com.devonfw.application.mtsj.dishmanagement.dataaccess.api.DishEntity;
import com.devonfw.application.mtsj.general.dataaccess.api.ApplicationPersistenceEntity;
import com.devonfw.application.mtsj.usermanagement.common.api.User;
import com.devonfw.application.mtsj.xap.BeanUtils;
import com.gigaspaces.annotation.pojo.SpaceClass;
import com.gigaspaces.annotation.pojo.SpaceExclude;
import com.gigaspaces.annotation.pojo.SpaceIndex;
import com.gigaspaces.metadata.index.SpaceIndexType;

@SpaceClass
public class UserEntity extends ApplicationPersistenceEntity implements User {

  private String username;

  private String password;

  private String email;

  private String secret;

  private boolean twoFactorStatus;

  private UserRoleEntity userRole;

  private List<BookingEntity> bookings;

  private List<DishEntity> favourites;

  private static final long serialVersionUID = 1L;

  /**
   * @return username
   */
  @Override
  @SpaceIndex(type = SpaceIndexType.EQUAL)
  public String getUsername() {

    return this.username;
  }

  /**
   * @param username new value of {@link #getUsername()}.
   */
  @Override
  public void setUsername(String username) {

    this.username = username;
  }

  /**
   * @return password
   */
  public String getPassword() {

    return this.password;
  }

  /**
   * @param password new value of {@link #getPassword()}.
   */
  public void setPassword(String password) {

    this.password = password;
  }

  /**
   * @return email
   */
  @Override
  public String getEmail() {

    return this.email;
  }

  /**
   * @param email new value of {@link #getEmail()}.
   */
  @Override
  public void setEmail(String email) {

    this.email = email;
  }

  /**
   * @return secret
   */
  public String getSecret() {

    return this.secret;
  }

  /**
   * @param secret new value of {@link #getSecret()}.
   */
  public void setSecret(String secret) {

    this.secret = secret;
  }

  /**
   * @return twoFactorStatus
   */
  @Override
  public boolean getTwoFactorStatus() {

    return this.twoFactorStatus;
  }

  /**
   *
   * @param twoFactorStatus new value of {@link #getTwoFactorStatus()}
   */
  @Override
  public void setTwoFactorStatus(boolean twoFactorStatus) {

    this.twoFactorStatus = twoFactorStatus;
  }

  @SpaceExclude
  public UserRoleEntity getUserRole() {

    GigaSpace gigaspace = BeanUtils.getBean(GigaSpace.class);
    UserRoleEntity queriedUserRole = gigaspace.readById(UserRoleEntity.class, getUserRoleId());
    return queriedUserRole;
  }

  /**
   * @param userRole new value of {@link #getUserRole()}.
   */
  public void setUserRole(UserRoleEntity userRole) {

    this.userRole = userRole;
  }

  /**
   * @return favourites
   */
  public List<DishEntity> getFavourites() {

    return this.favourites;
  }

  /**
   * @param favourites new value of {@link #getFavourites()}.
   */
  public void setFavourites(List<DishEntity> favourites) {

    this.favourites = favourites;
  }

  /**
   * @return bookings
   */
  @SpaceExclude
  public List<BookingEntity> getBookings() {

    GigaSpace gigaspace = BeanUtils.getBean(GigaSpace.class);
    BookingEntity template = new BookingEntity();
    template.setUserId(getId());
    BookingEntity[] queriedBookings = gigaspace.readMultiple(template, Integer.MAX_VALUE);

    return Arrays.asList(queriedBookings);
  }

  /**
   * @param bookings new value of {@link #getBookings()}.
   */
  public void setBookings(List<BookingEntity> bookings) {

    this.bookings = bookings;
  }

  @Override
  public String getUserRoleId() {

    if (this.userRole == null) {
      return null;
    }
    return this.userRole.getId();
  }

  @Override
  public void setUserRoleId(String userRoleId) {

    if (userRoleId == null) {
      this.userRole = null;
    } else {
      UserRoleEntity userRoleEntity = new UserRoleEntity();
      userRoleEntity.setId(userRoleId);
      this.userRole = userRoleEntity;
    }
  }

  @Override
  public String toString() {

    return "User [id=" + getId() + ", username=" + this.username + ", email=" + this.email + " userRole: "
        + this.userRole.toString() + "]";
  }

}
