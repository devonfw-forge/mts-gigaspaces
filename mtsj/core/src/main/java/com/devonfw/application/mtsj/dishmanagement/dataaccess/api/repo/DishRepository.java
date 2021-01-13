package com.devonfw.application.mtsj.dishmanagement.dataaccess.api.repo;

import java.math.BigDecimal;

import org.springframework.data.domain.Page;

import com.devonfw.application.mtsj.dishmanagement.common.api.to.DishSearchCriteriaTo;
import com.devonfw.application.mtsj.dishmanagement.dataaccess.api.DishEntity;
import com.devonfw.application.mtsj.dishmanagement.dataaccess.api.QDishEntity;
import com.devonfw.application.mtsj.xap.DefaultXapRepository;
import com.devonfw.application.mtsj.xap.XapQueryUtil;
import com.devonfw.module.jpa.dataaccess.api.data.DefaultRepository;
import com.querydsl.core.types.dsl.BooleanExpression;

/**
 * {@link DefaultRepository} for {@link DishEntity}.
 */
public interface DishRepository extends DefaultXapRepository<DishEntity> {

  /**
   * @param criteria the {@link DishSearchCriteriaTo} with the criteria to search.
   * @return the {@link Page} of the {@link DishEntity} objects that matched the search.
   */
  default Page<DishEntity> findDishs(DishSearchCriteriaTo criteria) {

    QDishEntity qdishEntity = QDishEntity.dishEntity;
    BooleanExpression query = null;

    String searchBy = criteria.getSearchBy();
    if (searchBy != null && !searchBy.isEmpty()) {
      query = XapQueryUtil.get().where(query,
          qdishEntity.name.containsIgnoreCase(searchBy).or(qdishEntity.description.containsIgnoreCase(searchBy)));
    }

    BigDecimal price = criteria.getMaxPrice();
    if (price != null) {
      query = XapQueryUtil.get().where(query, qdishEntity.price.lt(price));
    }
    return findAll(query, criteria.getPageable());
  }

}
