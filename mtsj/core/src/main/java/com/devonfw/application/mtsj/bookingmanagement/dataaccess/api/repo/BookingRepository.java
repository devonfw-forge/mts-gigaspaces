package com.devonfw.application.mtsj.bookingmanagement.dataaccess.api.repo;

import java.time.Instant;

import org.springframework.data.domain.Page;
import org.springframework.data.gigaspaces.repository.Query;

import com.devonfw.application.mtsj.bookingmanagement.common.api.datatype.BookingType;
import com.devonfw.application.mtsj.bookingmanagement.common.api.to.BookingSearchCriteriaTo;
import com.devonfw.application.mtsj.bookingmanagement.dataaccess.api.BookingEntity;
import com.devonfw.application.mtsj.bookingmanagement.dataaccess.api.QBookingEntity;
import com.devonfw.application.mtsj.xap.DefaultXapRepository;
import com.devonfw.application.mtsj.xap.XapQueryUtil;
import com.devonfw.module.jpa.dataaccess.api.data.DefaultRepository;
import com.querydsl.core.types.dsl.BooleanExpression;

/**
 * {@link DefaultRepository} for {@link BookingEntity}.
 */
public interface BookingRepository extends DefaultXapRepository<BookingEntity> {

  /**
   * @param token
   * @return the {@link BookingEntity} objects that matched the search.
   */
  @Query("bookingToken = ?")
  BookingEntity findBookingByToken(String token);

  /**
   * @param criteria the {@link BookingSearchCriteriaTo} with the criteria to search.
   * @return the {@link Page} of the {@link BookingEntity} objects that matched the search.
   */
  default Page<BookingEntity> findBookings(BookingSearchCriteriaTo criteria) {

    QBookingEntity qbookingEntity = QBookingEntity.bookingEntity;
    BooleanExpression query = null;

    String name = criteria.getName();
    if ((name != null) && !name.isEmpty()) {
      query = XapQueryUtil.get().whereString(query, qbookingEntity.name, name, criteria.getNameOption());
    }
    String bookingToken = criteria.getBookingToken();
    if (bookingToken != null && !bookingToken.isEmpty()) {
      query = XapQueryUtil.get().whereString(query, qbookingEntity.bookingToken, bookingToken,
          criteria.getBookingTokenOption());
    }
    String comment = criteria.getComment();
    if (comment != null && !comment.isEmpty()) {
      query = XapQueryUtil.get().whereString(query, qbookingEntity.comment, comment, criteria.getCommentOption());
    }
    Instant bookingDate = criteria.getBookingDate();
    if (bookingDate != null) {
      query = XapQueryUtil.get().where(query, qbookingEntity.bookingDate.eq(bookingDate));
    }
    Instant expirationDate = criteria.getExpirationDate();
    if (expirationDate != null) {
      query = XapQueryUtil.get().where(query, qbookingEntity.expirationDate.eq(expirationDate));
    }
    Instant creationDate = criteria.getCreationDate();
    if (creationDate != null) {
      query = XapQueryUtil.get().where(query, qbookingEntity.creationDate.eq(creationDate));
    }
    String email = criteria.getEmail();
    if (email != null && !email.isEmpty()) {
      query = XapQueryUtil.get().whereString(query, qbookingEntity.email, email, criteria.getEmailOption());

    }
    Boolean canceled = criteria.getCanceled();
    if (canceled != null) {
      query = XapQueryUtil.get().where(query, qbookingEntity.canceled.eq(canceled));
    }
    BookingType bookingType = criteria.getBookingType();
    if (bookingType != null) {
      query = XapQueryUtil.get().where(query, qbookingEntity.bookingType.eq(bookingType));
    }
    String table = criteria.getTableId();
    if (table != null && qbookingEntity.table != null) {
      query = XapQueryUtil.get().where(query, qbookingEntity.tableId.eq(table));
    }

    return findAll(query, criteria.getPageable());
  }
}
