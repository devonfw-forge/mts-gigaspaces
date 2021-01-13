package com.devonfw.application.mtsj.ordermanagement.dataaccess.api.repo;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.gigaspaces.repository.Query;

import com.devonfw.application.mtsj.ordermanagement.common.api.to.OrderLineSearchCriteriaTo;
import com.devonfw.application.mtsj.ordermanagement.dataaccess.api.OrderLineEntity;
import com.devonfw.application.mtsj.ordermanagement.dataaccess.api.QOrderLineEntity;
import com.devonfw.application.mtsj.xap.DefaultXapRepository;
import com.devonfw.application.mtsj.xap.XapQueryUtil;
import com.devonfw.module.jpa.dataaccess.api.data.DefaultRepository;
import com.querydsl.core.types.dsl.BooleanExpression;

/**
 * {@link DefaultRepository} for {@link OrderLineEntity}.
 */
public interface OrderLineRepository extends DefaultXapRepository<OrderLineEntity> {

  /**
   * @param id
   * @return the list {@link OrderLineEntity} objects that matched the search.
   */
  @Query("orderId = ?")
  List<OrderLineEntity> findOrderLines(String id);

  /**
   * @param criteria the {@link OrderLineSearchCriteriaTo} with the criteria to search.
   * @return the {@link Page} of the {@link OrderLineEntity} objects that matched the search.
   */
  default Page<OrderLineEntity> findOrderLines(OrderLineSearchCriteriaTo criteria) {

    QOrderLineEntity qorderlineEntity = QOrderLineEntity.orderLineEntity;
    BooleanExpression query = null;

    String order = criteria.getOrderId();
    if (order != null && qorderlineEntity.order != null) {
      query = XapQueryUtil.get().where(query, qorderlineEntity.orderId.eq(order));
    }
    String dish = criteria.getDishId();
    if (dish != null && qorderlineEntity.dish != null) {
      query = XapQueryUtil.get().where(query, qorderlineEntity.dishId.eq(dish));
    }
    Integer amount = criteria.getAmount();
    if (amount != null) {
      query = XapQueryUtil.get().where(query, qorderlineEntity.amount.eq(amount));
    }
    String comment = criteria.getComment();
    if ((comment != null) && !comment.isEmpty()) {
      query = XapQueryUtil.get().whereString(query, qorderlineEntity.comment, comment, criteria.getCommentOption());
    }

    return findAll(query, criteria.getPageable());
  }
}
