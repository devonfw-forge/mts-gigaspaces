package com.devonfw.application.mtsj.bookingmanagement.dataaccess.api.repo;

import java.time.Instant;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.gigaspaces.repository.Query;

import com.devonfw.application.mtsj.bookingmanagement.common.api.to.InvitedGuestSearchCriteriaTo;
import com.devonfw.application.mtsj.bookingmanagement.dataaccess.api.InvitedGuestEntity;
import com.devonfw.application.mtsj.bookingmanagement.dataaccess.api.QInvitedGuestEntity;
import com.devonfw.application.mtsj.xap.DefaultXapRepository;
import com.devonfw.application.mtsj.xap.XapQueryUtil;
import com.devonfw.module.jpa.dataaccess.api.data.DefaultRepository;
import com.querydsl.core.types.dsl.BooleanExpression;

/**
 * {@link DefaultRepository} for {@link InvitedGuestEntity}.
 */
public interface InvitedGuestRepository extends DefaultXapRepository<InvitedGuestEntity> {

  /**
   * @param token
   * @return the {@link InvitedGuestEntity} objects that matched the search.
   */
  @Query("guestToken = ?")
  InvitedGuestEntity findInvitedGuestByToken(String token);

  /**
   * @param idBooking
   * @return the List {@link InvitedGuestEntity} objects that matched the search.
   */
  @Query("bookingId = ?")
  List<InvitedGuestEntity> findInvitedGuestByBooking(String idBooking);

  /**
   * @param criteria the {@link InvitedGuestSearchCriteriaTo} with the criteria to search.
   * @return the {@link Page} of the {@link InvitedGuestEntity} objects that matched the search.
   */
  default Page<InvitedGuestEntity> findInvitedGuests(InvitedGuestSearchCriteriaTo criteria) {

    QInvitedGuestEntity qinvitedGuestEntity = QInvitedGuestEntity.invitedGuestEntity;
    BooleanExpression query = null;

    String booking = criteria.getBookingId();
    if (booking != null && qinvitedGuestEntity.booking != null) {
      query = XapQueryUtil.get().where(query, qinvitedGuestEntity.bookingId.eq(booking));
    }
    String guestToken = criteria.getGuestToken();
    if ((guestToken != null) && !guestToken.isEmpty()) {
      query = XapQueryUtil.get().whereString(query, qinvitedGuestEntity.guestToken, guestToken,
          criteria.getGuestTokenOption());
    }
    String email = criteria.getEmail();
    if ((email != null) && !email.isEmpty()) {
      query = XapQueryUtil.get().whereString(query, qinvitedGuestEntity.email, email, criteria.getEmailOption());
    }
    Boolean accepted = criteria.getAccepted();
    if (accepted != null) {
      query = XapQueryUtil.get().where(query, qinvitedGuestEntity.accepted.eq(accepted));
    }
    Instant modificationDate = criteria.getModificationDate();
    if (modificationDate != null) {
      query = XapQueryUtil.get().where(query, qinvitedGuestEntity.modificationDate.eq(modificationDate));
    }

    return findAll(query, criteria.getPageable());
  }

}
