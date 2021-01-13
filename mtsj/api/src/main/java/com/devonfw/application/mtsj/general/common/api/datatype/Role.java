package com.devonfw.application.mtsj.general.common.api.datatype;

import java.security.Principal;

public enum Role implements Principal {

  MANAGER("2", "Manager"), WAITER("1", "Waiter"), CUSTOMER("0", "Customer");

  private final String id;

  private final String name;

  private Role(String id, String name) {

    this.id = id;
    this.name = name;
  }

  @Override
  public String getName() {

    return "ROLE_" + this.name;
  }

  public String getRole() {

    return this.name;
  }

  public static Role getRoleById(String id) {

    for (Role role : values()) {
      if (role.id.equals(id)) {
        return role;
      }
    }
    return Role.CUSTOMER;
  }
}
