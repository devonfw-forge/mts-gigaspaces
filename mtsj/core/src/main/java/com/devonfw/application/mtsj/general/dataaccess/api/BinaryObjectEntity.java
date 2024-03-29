package com.devonfw.application.mtsj.general.dataaccess.api;

import java.sql.Blob;

import javax.persistence.Column;
import javax.persistence.Lob;

import com.devonfw.application.mtsj.general.common.api.BinaryObject;

/**
 * {@link ApplicationPersistenceEntity Entity} for {@link BinaryObject}. Contains the actual {@link Blob}.
 *
 */

public class BinaryObjectEntity extends ApplicationPersistenceEntity implements BinaryObject {

  private static final long serialVersionUID = 1L;

  private Blob data;

  private String mimeType;

  private long size;

  /**
   * The constructor.
   */
  public BinaryObjectEntity() {

    super();
  }

  @Override
  public void setMimeType(String mimeType) {

    this.mimeType = mimeType;

  }

  @Override
  public String getMimeType() {

    return this.mimeType;
  }

  /**
   * @return data
   */

  /**
   * Remove the following line completely (Type Annotation) in case of database other than PostGres and Uncomment the
   * annotation for @Lob
   */
  // @Type(type = "org.hibernate.type.BinaryType")
  @Lob
  @Column(name = "content")
  public Blob getData() {

    return this.data;
  }

  /**
   * @param data the data to set
   */
  public void setData(Blob data) {

    this.data = data;
  }

  @Column(name = "filesize")
  @Override
  public long getSize() {

    return this.size;
  }

  @Override
  public void setSize(long size) {

    this.size = size;
  }
}
