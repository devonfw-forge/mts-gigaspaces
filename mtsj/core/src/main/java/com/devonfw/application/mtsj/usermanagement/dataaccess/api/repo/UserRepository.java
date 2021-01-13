package com.devonfw.application.mtsj.usermanagement.dataaccess.api.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.gigaspaces.repository.Query;

import com.devonfw.application.mtsj.usermanagement.common.api.to.UserSearchCriteriaTo;
import com.devonfw.application.mtsj.usermanagement.dataaccess.api.QUserEntity;
import com.devonfw.application.mtsj.usermanagement.dataaccess.api.UserEntity;
import com.devonfw.application.mtsj.xap.DefaultXapRepository;
import com.devonfw.application.mtsj.xap.XapQueryUtil;
import com.querydsl.core.types.dsl.BooleanExpression;

/**
 * @author dpatesan
 *
 */
public interface UserRepository extends DefaultXapRepository<UserEntity> {

  /**
   * @param criteria the {@link UserSearchCriteriaTo} with the criteria to search.
   * @return the {@link Page} of the {@link UserEntity} objects that matched the search.
   */
  default Page<UserEntity> findUsers(UserSearchCriteriaTo criteria) {

    QUserEntity quserEntity = QUserEntity.userEntity;
    BooleanExpression query = null;

    String username = criteria.getUsername();
    if ((username != null) && !username.isEmpty()) {
      query = XapQueryUtil.get().whereString(query, quserEntity.username, username, criteria.getUsernameOption());
    }
    String email = criteria.getEmail();
    if ((email != null) && !email.isEmpty()) {
      query = XapQueryUtil.get().whereString(query, quserEntity.email, email, criteria.getEmailOption());
    }
    String userRole = criteria.getUserRoleId();
    if (userRole != null && quserEntity.userRole != null) {
      query = XapQueryUtil.get().where(query, quserEntity.userRoleId.eq(userRole));
    }

    return findAll(query, criteria.getPageable());
  }

  /**
   * @param username
   * @return An {@link UserEntity} objects that matched the search.
   */
  @Query("username = ?")
  UserEntity findByUsername(String username);
}
