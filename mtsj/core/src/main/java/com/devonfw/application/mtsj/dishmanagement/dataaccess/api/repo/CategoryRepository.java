package com.devonfw.application.mtsj.dishmanagement.dataaccess.api.repo;

import org.springframework.data.domain.Page;

import com.devonfw.application.mtsj.dishmanagement.common.api.to.CategorySearchCriteriaTo;
import com.devonfw.application.mtsj.dishmanagement.dataaccess.api.CategoryEntity;
import com.devonfw.application.mtsj.dishmanagement.dataaccess.api.QCategoryEntity;
import com.devonfw.application.mtsj.xap.DefaultXapRepository;
import com.devonfw.application.mtsj.xap.XapQueryUtil;
import com.devonfw.module.jpa.dataaccess.api.data.DefaultRepository;
import com.querydsl.core.types.dsl.BooleanExpression;

/**
 * {@link DefaultRepository} for {@link CategoryEntity}.
 */
public interface CategoryRepository extends DefaultXapRepository<CategoryEntity> {

  /**
   * @param criteria the {@link CategorySearchCriteriaTo} with the criteria to search.
   * @return the {@link Page} of the {@link CategoryEntity} objects that matched the search.
   */
  default Page<CategoryEntity> findCategorys(CategorySearchCriteriaTo criteria) {

    QCategoryEntity qcategoryEntity = QCategoryEntity.categoryEntity;
    BooleanExpression query = null;

    String name = criteria.getName();
    if ((name != null) && !name.isEmpty()) {
      query = XapQueryUtil.get().whereString(query, qcategoryEntity.name, name, criteria.getNameOption());
    }
    String description = criteria.getDescription();
    if ((description != null) && !description.isEmpty()) {
      query = XapQueryUtil.get().whereString(query, qcategoryEntity.description, description,
          criteria.getDescriptionOption());
    }
    int showOrder = criteria.getShowOrder();
    query = XapQueryUtil.get().where(query, qcategoryEntity.showOrder.eq(showOrder));

    return findAll(query, criteria.getPageable());
  }

}
