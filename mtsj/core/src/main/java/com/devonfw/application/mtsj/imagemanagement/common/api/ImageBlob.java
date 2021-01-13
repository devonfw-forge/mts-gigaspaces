package com.devonfw.application.mtsj.imagemanagement.common.api;

import java.sql.Blob;

import com.devonfw.application.mtsj.imagemanagement.common.api.datatype.ContentType;
import com.devonfw.module.basic.common.api.entity.GenericEntity;

/**
 * Interface for {@link ImageBlob}
 *
 */
public interface ImageBlob extends GenericEntity<String> {

  /**
   * @return name of the {@link ImageBlob}
   */
  public String getName();

  /**
   * @param name of the {@link ImageBlob}
   */
  public void setName(String name);

  /**
   * @return content of the {@link ImageBlob}
   */
  public Blob getContent();

  /**
   * @param content of the {@link ImageBlob}
   */
  public void setContent(Blob content);

  /**
   * @return contentType of the {@link ImageBlob}
   */
  public ContentType getContentType();

  /**
   * @param contentType of the {@link ImageBlob}
   */
  public void setContentType(ContentType contentType);

  /**
   * @return mimeType of the {@link ImageBlob}
   */
  public String getMimeType();

  /**
   * @param mimeType of the {@link ImageBlob}
   */
  public void setMimeType(String mimeType);

}
