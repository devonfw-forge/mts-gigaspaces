package com.devonfw.application.mtsj.usermanagement.dataaccess.api.repo;

import org.springframework.data.domain.Page;

import com.devonfw.application.mtsj.usermanagement.common.api.to.UserRoleSearchCriteriaTo;
import com.devonfw.application.mtsj.usermanagement.dataaccess.api.QUserRoleEntity;
import com.devonfw.application.mtsj.usermanagement.dataaccess.api.UserRoleEntity;
import com.devonfw.application.mtsj.xap.DefaultXapRepository;
import com.devonfw.application.mtsj.xap.XapQueryUtil;
import com.devonfw.module.jpa.dataaccess.api.data.DefaultRepository;
import com.querydsl.core.types.dsl.BooleanExpression;

/**
 * {@link DefaultRepository} for {@link UserRoleEntity}.
 */
public interface UserRoleRepository extends DefaultXapRepository<UserRoleEntity> {

  /**
   * @param criteria the {@link UserRoleSearchCriteriaTo} with the criteria to search.
   * @return the {@link Page} of the {@link UserRoleEntity} objects that matched the search.
   */
  default Page<UserRoleEntity> findUserRoles(UserRoleSearchCriteriaTo criteria) {

    BooleanExpression query = null;
    QUserRoleEntity quserRoleEntity = QUserRoleEntity.userRoleEntity;
    String name = criteria.getName();
    if ((name != null) && !name.isEmpty()) {
      query = XapQueryUtil.get().whereString(query, quserRoleEntity.name, name, criteria.getNameOption());
    }

    Boolean active = criteria.getActive();
    if (active != null) {
      query = XapQueryUtil.get().where(query, quserRoleEntity.active.eq(active));
    }

    return findAll(query, criteria.getPageable());
  }
}
