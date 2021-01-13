package com.devonfw.application.mtsj.usermanagement.dataaccess.api;

import java.util.List;

import com.devonfw.application.mtsj.general.dataaccess.api.ApplicationPersistenceEntity;
import com.devonfw.application.mtsj.usermanagement.common.api.UserRole;
import com.gigaspaces.annotation.pojo.SpaceClass;

@SpaceClass
public class UserRoleEntity extends ApplicationPersistenceEntity implements UserRole {

  private String name;

  private Boolean active;

  private List<UserEntity> users;

  private static final long serialVersionUID = 1L;

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
   * @return active
   */
  @Override
  public Boolean getActive() {

    return this.active;
  }

  /**
   * @param active new value of {@link #getactive}.
   */
  @Override
  public void setActive(Boolean active) {

    this.active = active;
  }

  public List<UserEntity> getUsers() {

    return this.users;
  }

  /**
   * @param users new value of {@link #getusers}.
   */
  public void setUsers(List<UserEntity> users) {

    this.users = users;
  }

  @Override
  public String toString() {

    return "UserRole [id=" + getId() + ", name=" + this.name + ", active=" + this.active + "]";
  }

}
