package com.devonfw.application.mtsj.bookingmanagement.dataaccess.api.repo;

import org.springframework.data.domain.Page;

import com.devonfw.application.mtsj.bookingmanagement.common.api.to.TableSearchCriteriaTo;
import com.devonfw.application.mtsj.bookingmanagement.dataaccess.api.QTableEntity;
import com.devonfw.application.mtsj.bookingmanagement.dataaccess.api.TableEntity;
import com.devonfw.application.mtsj.xap.DefaultXapRepository;
import com.devonfw.application.mtsj.xap.XapQueryUtil;
import com.devonfw.module.jpa.dataaccess.api.data.DefaultRepository;
import com.querydsl.core.types.dsl.BooleanExpression;

/**
 * {@link DefaultRepository} for {@link TableEntity}.
 */
public interface TableRepository extends DefaultXapRepository<TableEntity> {

  /**
   * @param criteria the {@link TableSearchCriteriaTo} with the criteria to search.
   * @return the {@link Page} of the {@link TableEntity} objects that matched the search.
   */
  default Page<TableEntity> findTables(TableSearchCriteriaTo criteria) {

    QTableEntity qtableEntity = QTableEntity.tableEntity;
    BooleanExpression query = null;

    Integer seatsNumber = criteria.getSeatsNumber();
    if (seatsNumber != null) {
      query = XapQueryUtil.get().where(query, qtableEntity.seatsNumber.eq(seatsNumber));
    }

    return findAll(query, criteria.getPageable());

  }
}
