package com.devonfw.application.mtsj.imagemanagement.dataaccess.api;

import java.sql.Blob;

import com.devonfw.application.mtsj.general.dataaccess.api.ApplicationPersistenceEntity;
import com.devonfw.application.mtsj.imagemanagement.common.api.ImageBlob;
import com.devonfw.application.mtsj.imagemanagement.common.api.datatype.ContentType;
import com.gigaspaces.annotation.pojo.SpaceClass;
import com.gigaspaces.annotation.pojo.SpaceStorageType;
import com.gigaspaces.metadata.StorageType;

/**
 * @author pparrado
 */
@SpaceClass
public class ImageEntity extends ApplicationPersistenceEntity implements ImageBlob {

  private static final long serialVersionUID = 1L;

  private String name;

  private Blob content;

  private ContentType contentType;

  private String mimeType;

  /**
   * @return name
   */
  @Override
  public String getName() {

    return this.name;
  }

  /**
   * @param name new value of {@link #getName}.
   */
  @Override
  public void setName(String name) {

    this.name = name;
  }

  /**
   * @return content
   */

  @Override
  @SpaceStorageType(storageType = StorageType.BINARY)
  public Blob getContent() {

    return this.content;
  }

  /**
   * @param content new value of {@link #getContent}.
   */
  @Override
  public void setContent(Blob content) {

    this.content = content;
  }

  /**
   * @return contentType
   */
  @Override
  public ContentType getContentType() {

    return this.contentType;
  }

  /**
   * @param contentType new value of {@link #getContentType}.
   */
  @Override
  public void setContentType(ContentType contentType) {

    this.contentType = contentType;
  }

  /**
   * @return mimeType
   */
  @Override
  public String getMimeType() {

    return this.mimeType;
  }

  /**
   * @param mimeType new value of {@link #getMimeType}.
   */
  @Override
  public void setMimeType(String mimeType) {

    this.mimeType = mimeType;
  }

}
