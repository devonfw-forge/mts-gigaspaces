package com.devonfw.application.mtsj.ordermanagement.dataaccess.api;

import java.util.List;

import org.openspaces.core.GigaSpace;

import com.devonfw.application.mtsj.dishmanagement.dataaccess.api.DishEntity;
import com.devonfw.application.mtsj.dishmanagement.dataaccess.api.IngredientEntity;
import com.devonfw.application.mtsj.general.dataaccess.api.ApplicationPersistenceEntity;
import com.devonfw.application.mtsj.ordermanagement.common.api.OrderLine;
import com.devonfw.application.mtsj.xap.BeanUtils;
import com.gigaspaces.annotation.pojo.SpaceClass;
import com.gigaspaces.annotation.pojo.SpaceExclude;
import com.gigaspaces.annotation.pojo.SpaceIndex;
import com.gigaspaces.metadata.index.SpaceIndexType;

/**
 * {@link ApplicationPersistenceEntity Entity} that represents a single {@link OrderLine} of an {@link OrderEntity}.
 */

@SpaceClass
public class OrderLineEntity extends ApplicationPersistenceEntity implements OrderLine {

  private OrderEntity order;

  private DishEntity dish;

  private List<IngredientEntity> extras;

  private Integer amount;

  private String comment;

  private static final long serialVersionUID = 1L;

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
   * @param order new value of {@link #getOrder}.
   */
  public void setOrder(OrderEntity order) {

	  this.order = order;
  }

  /**
   * @return extras
   */
  public List<IngredientEntity> getExtras() {

    return this.extras;
  }

  /**
   * @param extras new value of {@link #getExtras}.
   */
  public void setExtras(List<IngredientEntity> extras) {

    this.extras = extras;
  }

  /**
   * @return amount
   */
  @Override
  public Integer getAmount() {

    return this.amount;
  }

  /**
   * @param amount new value of {@link #getAmount}.
   */
  @Override
  public void setAmount(Integer amount) {

    this.amount = amount;
  }

  /**
   * @return comment
   */
  @Override
  public String getComment() {

    return this.comment;
  }

  /**
   * @param comment new value of {@link #getComment}.
   */
  @Override
  public void setComment(String comment) {

    this.comment = comment;
  }

  @Override
  @SpaceIndex(type=SpaceIndexType.EQUAL)
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

  @Override
  public String getDishId() {

    if (this.dish == null) {
      return null;
    }
    return this.dish.getId();
  }

  @Override
  public void setDishId(String dishId) {

    if (dishId == null) {
      this.dish = null;
    } else {
      DishEntity dishEntity = new DishEntity();
      dishEntity.setId(dishId);
      this.dish = dishEntity;
    }
  }

  /**
   * @return dish
   */
  @SpaceExclude
  public DishEntity getDish() {

	  GigaSpace gigaspace = BeanUtils.getBean(GigaSpace.class);
	    DishEntity queriedDish = gigaspace.readById(DishEntity.class, getDishId());
	    return queriedDish;
  }

  /**
   * @param dish new value of {@link #getDish}.
   */
  public void setDish(DishEntity dish) {

    this.dish = dish;
  }

}
