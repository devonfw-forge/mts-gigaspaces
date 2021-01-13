package com.devonfw.application.mtsj.dishmanagement.dataaccess.api.repo;

import java.math.BigDecimal;

import org.springframework.data.domain.Page;

import com.devonfw.application.mtsj.dishmanagement.common.api.to.IngredientSearchCriteriaTo;
import com.devonfw.application.mtsj.dishmanagement.dataaccess.api.IngredientEntity;
import com.devonfw.application.mtsj.dishmanagement.dataaccess.api.QIngredientEntity;
import com.devonfw.application.mtsj.xap.DefaultXapRepository;
import com.devonfw.application.mtsj.xap.XapQueryUtil;
import com.devonfw.module.jpa.dataaccess.api.data.DefaultRepository;
import com.querydsl.core.types.dsl.BooleanExpression;

/**
 * {@link DefaultRepository} for {@link IngredientEntity}.
 */
public interface IngredientRepository extends DefaultXapRepository<IngredientEntity> {

  /**
   * @param criteria the {@link IngredientSearchCriteriaTo} with the criteria to search.
   * @return the {@link Page} of the {@link IngredientEntity} objects that matched the search.
   */
  default Page<IngredientEntity> findIngredients(IngredientSearchCriteriaTo criteria) {

    QIngredientEntity qingredientEntity = QIngredientEntity.ingredientEntity;
    BooleanExpression query = null;

    String name = criteria.getName();
    if ((name != null) && !name.isEmpty()) {
      query = XapQueryUtil.get().whereString(query, qingredientEntity.name, name, criteria.getNameOption());
    }
    String description = criteria.getDescription();
    if ((description != null) && !description.isEmpty()) {
      query = XapQueryUtil.get().whereString(query, qingredientEntity.description, description,
          criteria.getDescriptionOption());
    }

    BigDecimal price = criteria.getPrice();
    if (price != null) {
      query = XapQueryUtil.get().where(query, qingredientEntity.price.eq(price));
    }

    return findAll(query, criteria.getPageable());
  }
}
