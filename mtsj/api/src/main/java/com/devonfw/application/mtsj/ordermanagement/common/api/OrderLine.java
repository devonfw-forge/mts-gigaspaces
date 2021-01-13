package com.devonfw.application.mtsj.ordermanagement.common.api;

import com.devonfw.module.basic.common.api.entity.GenericEntity;

public interface OrderLine extends GenericEntity<String> {

  public String getOrderId();

  public void setOrderId(String orderId);

  public String getDishId();

  public void setDishId(String dishId);

  public Integer getAmount();

  public void setAmount(Integer amount);

  public String getComment();

  public void setComment(String comment);

}
