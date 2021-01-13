package com.devonfw.application.mtsj.ordermanagement.dataaccess.api.repo;

import java.sql.Timestamp;

import org.springframework.data.domain.Page;

import com.devonfw.application.mtsj.ordermanagement.common.api.to.OrderedDishesSearchCriteriaTo;
import com.devonfw.application.mtsj.ordermanagement.dataaccess.api.OrderedDishesPerDayEntity;
import com.devonfw.application.mtsj.ordermanagement.dataaccess.api.QOrderedDishesPerDayEntity;
import com.devonfw.application.mtsj.xap.DefaultXapRepository;
import com.devonfw.application.mtsj.xap.XapQueryUtil;
import com.querydsl.core.types.dsl.BooleanExpression;

/**
 * Data access interface for OrderedDishesPerDay entities
 */
public interface OrderedDishesPerDayRepository extends DefaultXapRepository<OrderedDishesPerDayEntity> {

  /**
   * Finds the {@link OrderedDishesPerDayEntity orderedDishesPerDay} matching the last seven days.
   *
   * @return the {@link PaginatedListTo} with the matching {@link OrderedDishesPerDayEntity} objects.
   */
  default Page<OrderedDishesPerDayEntity> findOrderedDishesPerDay(OrderedDishesSearchCriteriaTo criteria) {

    QOrderedDishesPerDayEntity qorderedDishesPerDayEntity = QOrderedDishesPerDayEntity.orderedDishesPerDayEntity;
    BooleanExpression query = null;

    // le,ge,lt,gt,eq,ne
    Timestamp startBookingdate = new Timestamp(criteria.getStartBookingdate().getTime() - 1);

    if (startBookingdate != null) {
      query = XapQueryUtil.get().where(query, qorderedDishesPerDayEntity.bookingdate.after(startBookingdate));
    }
    Timestamp endBookingdate = new Timestamp(criteria.getEndBookingdate().getTime() + 86399999);
    if (endBookingdate != null) {
      query = XapQueryUtil.get().where(query, qorderedDishesPerDayEntity.bookingdate.before(endBookingdate));
    }

    return findAll(query, criteria.getPageable());
  }
}
