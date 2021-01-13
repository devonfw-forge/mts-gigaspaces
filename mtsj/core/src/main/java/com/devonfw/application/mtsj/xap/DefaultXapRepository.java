package com.devonfw.application.mtsj.xap;

import com.devonfw.module.basic.common.api.entity.PersistenceEntity;

/**
 * @author dpatesan
 *
 */
public interface DefaultXapRepository<E extends PersistenceEntity<String>> extends GenericXapRepository<E, String> {

}
