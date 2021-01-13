package com.devonfw.application.mtsj.ordermanagement.dataaccess.api.repo;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

import org.springframework.data.domain.Page;

import com.devonfw.application.mtsj.ordermanagement.common.api.to.OrderedDishesSearchCriteriaTo;
import com.devonfw.application.mtsj.ordermanagement.dataaccess.api.OrderedDishesPerMonthEntity;
import com.devonfw.application.mtsj.ordermanagement.dataaccess.api.QOrderedDishesPerMonthEntity;
import com.devonfw.application.mtsj.xap.DefaultXapRepository;
import com.devonfw.application.mtsj.xap.XapQueryUtil;
import com.querydsl.core.types.dsl.BooleanExpression;

/**
 * Data access interface for OrderedDishesPerMonth entities
 */
public interface OrderedDishesPerMonthRepository extends DefaultXapRepository<OrderedDishesPerMonthEntity> {

  /**
   * Finds the {@link OrderedDishesPerMonthEntity orderedDishesPerMonth} matching the last 12 Months.
   *
   * @return the {@link PaginatedListTo} with the matching {@link OrderedDishesPerMonthEntity} objects.
   */
  default Page<OrderedDishesPerMonthEntity> findOrderedDishesPerMonth(OrderedDishesSearchCriteriaTo criteria) {

    QOrderedDishesPerMonthEntity qorderedDishesPerMonthEntity = QOrderedDishesPerMonthEntity.orderedDishesPerMonthEntity;
    BooleanExpression query = null;

    // le,ge,lt,gt,eq,ne
    Timestamp startBookingdate = new Timestamp(criteria.getStartBookingdate().getTime() - 1);

    if (startBookingdate != null) {
      query = XapQueryUtil.get().where(query, qorderedDishesPerMonthEntity.bookingdate.after(startBookingdate));
    }
    Date date = new Date(criteria.getEndBookingdate().getTime());
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    calendar.set(Calendar.HOUR_OF_DAY, 0); // ! clear would not reset the hour of day !
    calendar.clear(Calendar.MINUTE);
    calendar.clear(Calendar.SECOND);
    calendar.clear(Calendar.MILLISECOND);
    Date zeroedDate = calendar.getTime();
    Timestamp endBookingdate = new Timestamp(zeroedDate.getTime() + 86399999);

    if (endBookingdate != null) {
      query = XapQueryUtil.get().where(query, qorderedDishesPerMonthEntity.bookingdate.before(endBookingdate));
    }

    return findAll(query, criteria.getPageable());
  }
}
