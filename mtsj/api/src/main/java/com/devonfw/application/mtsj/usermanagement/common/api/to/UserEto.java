package com.devonfw.application.mtsj.usermanagement.common.api.to;

import com.devonfw.application.mtsj.general.common.api.to.AbstractEtoString;
import com.devonfw.application.mtsj.usermanagement.common.api.User;

/**
 * Entity transport object of User
 */
public class UserEto extends AbstractEtoString implements User {

  private static final long serialVersionUID = 1L;

  private String username;

  private String email;

  private boolean twoFactorStatus;

  private String userRoleId;

  @Override
  public String getUsername() {

    return this.username;
  }

  @Override
  public void setUsername(String username) {

    this.username = username;
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
  public boolean getTwoFactorStatus() {

    return this.twoFactorStatus;
  }

  @Override
  public void setTwoFactorStatus(boolean twoFactorStatus) {

    this.twoFactorStatus = twoFactorStatus;
  }

  @Override
  public String getUserRoleId() {

    return this.userRoleId;
  }

  @Override
  public void setUserRoleId(String userRoleId) {

    this.userRoleId = userRoleId;
  }

  @Override
  public int hashCode() {

    final int prime = 31;
    int result = super.hashCode();
    result = prime * result + ((this.username == null) ? 0 : this.username.hashCode());
    result = prime * result + ((this.email == null) ? 0 : this.email.hashCode());
    result = prime * result + ((this.userRoleId == null) ? 0 : this.userRoleId.hashCode());

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
    UserEto other = (UserEto) obj;
    if (this.username == null) {
      if (other.username != null) {
        return false;
      }
    } else if (!this.username.equals(other.username)) {
      return false;
    }
    if (this.email == null) {
      if (other.email != null) {
        return false;
      }
    } else if (!this.email.equals(other.email)) {
      return false;
    }

    if (this.userRoleId == null) {
      if (other.userRoleId != null) {
        return false;
      }
    } else if (!this.userRoleId.equals(other.userRoleId)) {
      return false;
    }

    return true;
  }
}
