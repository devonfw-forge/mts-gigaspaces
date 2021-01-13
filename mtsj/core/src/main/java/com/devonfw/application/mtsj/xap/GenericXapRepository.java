package com.devonfw.application.mtsj.xap;

import java.io.Serializable;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.gigaspaces.querydsl.GigaspacesQueryDslPredicateExecutor;
import org.springframework.data.gigaspaces.repository.GigaspacesRepository;
import org.springframework.data.repository.NoRepositoryBean;


/**
 * @author dpatesan
 *
 */
@NoRepositoryBean
public interface GenericXapRepository<E, ID extends Serializable> extends GigaspacesRepository<E, ID>, GigaspacesQueryDslPredicateExecutor<E>  {

  /**
   * @param id the {@link com.devonfw.module.basic.common.api.entity.PersistenceEntity#getId() primary key}. May not be
   *        {@code null}.
   * @return the requested entity. Never {@code null}.
   * @see #findById(Object)
   */
  default E find(ID id) {

    return findById(id)
        .orElseThrow(() -> new EmptyResultDataAccessException("No Entity with ID '" + id + "' was not found!", 1));
  }

}
