package com.devonfw.application.mtsj.ordermanagement.dataaccess.api.repo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.gigaspaces.repository.Query;

import com.devonfw.application.mtsj.bookingmanagement.dataaccess.api.BookingEntity;
import com.devonfw.application.mtsj.ordermanagement.common.api.to.OrderSearchCriteriaTo;
import com.devonfw.application.mtsj.ordermanagement.dataaccess.api.OrderEntity;
import com.devonfw.application.mtsj.ordermanagement.dataaccess.api.QOrderEntity;
import com.devonfw.application.mtsj.xap.DefaultXapRepository;
import com.devonfw.application.mtsj.xap.XapQueryUtil;
import com.devonfw.module.jpa.dataaccess.api.data.DefaultRepository;
import com.querydsl.core.types.dsl.BooleanExpression;

/**
 * {@link DefaultRepository} for {@link OrderEntity}.
 */
public interface OrderRepository extends DefaultXapRepository<OrderEntity> {

  /**
   * @param idBooking
   * @return the list {@link OrderEntity} objects that matched the search.
   */
  @Query("bookingId = ?")
  List<OrderEntity> findOrders(String idBooking);

  /**
   * @param idInvitedGuest
   * @return the list {@link OrderEntity} objects that matched the search.
   */
  @Query("invitedGuestId = ?")
  List<OrderEntity> findOrdersByInvitedGuest(String idInvitedGuest);

  /**
   * @param bookingToken
   * @return the {@link OrderEntity} objects that matched the search.
   */
  default List<OrderEntity> findOrdersByBookingToken(String bookingToken) {

    BookingEntity template = new BookingEntity();
    template.setBookingToken(bookingToken);
    List<BookingEntity> bookings = XapQueryUtil.get().findMultipleByTemplate(template);
    List<String> bookingIds = new ArrayList<>();
    for (BookingEntity booking : bookings) {
      bookingIds.add(booking.getId());
    }
    return (List<OrderEntity>) findAll(bookingIds, null);

  }

  /**
   * @param criteria the {@link OrderSearchCriteriaTo} with the criteria to search.
   * @return the {@link Page} of the {@link OrderEntity} objects that matched the search.
   */
  default Page<OrderEntity> findOrders(OrderSearchCriteriaTo criteria) {

    QOrderEntity qorderEntity = QOrderEntity.orderEntity;
    BooleanExpression query = null;

    String booking = criteria.getBookingId();
    if (booking != null && qorderEntity.booking != null) {
      query = XapQueryUtil.get().where(query, qorderEntity.bookingId.eq(booking));
    }
    String invitedGuest = criteria.getInvitedGuestId();
    if (invitedGuest != null && qorderEntity.invitedGuest != null) {
      query = XapQueryUtil.get().where(query, qorderEntity.invitedGuestId.eq(invitedGuest));
    }
    String hostToken = criteria.getHostToken();
    if (hostToken != null && qorderEntity.host != null) {
      query = XapQueryUtil.get().where(query, qorderEntity.booking.bookingToken.eq(hostToken));
    }
    String email = criteria.getEmail();
    if ((email != null) && qorderEntity.booking != null) {
      query = XapQueryUtil.get().where(query, qorderEntity.booking.email.toLowerCase().like(email.toLowerCase()));
    }
    String bookingToken = criteria.getBookingToken();
    if ((bookingToken != null) && qorderEntity.booking != null) {
      query = XapQueryUtil.get().where(query,
          qorderEntity.booking.bookingToken.toLowerCase().eq(bookingToken.toLowerCase()));
    }

    return findAll(query, criteria.getPageable());
  }

}
