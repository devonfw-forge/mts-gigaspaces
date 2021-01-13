package com.devonfw.application.mtsj.imagemanagement.dataaccess.api.repo;

import java.sql.Blob;

import org.springframework.data.domain.Page;

import com.devonfw.application.mtsj.imagemanagement.common.api.datatype.ContentType;
import com.devonfw.application.mtsj.imagemanagement.common.api.to.ImageSearchCriteriaTo;
import com.devonfw.application.mtsj.imagemanagement.dataaccess.api.ImageEntity;
import com.devonfw.application.mtsj.imagemanagement.dataaccess.api.QImageEntity;
import com.devonfw.application.mtsj.xap.DefaultXapRepository;
import com.devonfw.application.mtsj.xap.XapQueryUtil;
import com.devonfw.module.jpa.dataaccess.api.data.DefaultRepository;
import com.querydsl.core.types.dsl.BooleanExpression;

/**
 * {@link DefaultRepository} for {@link ImageEntity}.
 */
public interface ImageRepository extends DefaultXapRepository<ImageEntity> {

  /**
   * @param criteria the {@link ImageSearchCriteriaTo} with the criteria to search.
   * @return the {@link Page} of the {@link ImageEntity} objects that matched the search.
   */
  default Page<ImageEntity> findImages(ImageSearchCriteriaTo criteria) {

    QImageEntity qimageEntity = QImageEntity.imageEntity;
    BooleanExpression query = null;

    String name = criteria.getName();
    if ((name != null) && !name.isEmpty()) {
      query = XapQueryUtil.get().whereString(query, qimageEntity.name, name, criteria.getNameOption());
    }
    Blob content = criteria.getContent();
    if (content != null) {
      query = XapQueryUtil.get().where(query, qimageEntity.content.eq(content));
    }
    ContentType contentType = criteria.getContentType();
    if (contentType != null) {
      query = XapQueryUtil.get().where(query, qimageEntity.contentType.eq(contentType));
    }
    String mimeType = criteria.getMimeType();
    if ((mimeType != null) && !mimeType.isEmpty()) {
      query = XapQueryUtil.get().whereString(query, qimageEntity.mimeType, mimeType, criteria.getMimeTypeOption());
    }

    return findAll(query, criteria.getPageable());
  }

}
