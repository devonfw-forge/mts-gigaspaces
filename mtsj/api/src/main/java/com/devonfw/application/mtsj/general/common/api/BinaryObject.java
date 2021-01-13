package com.devonfw.application.mtsj.general.common.api;

import com.devonfw.module.basic.common.api.entity.GenericEntity;

/**
 * This is the interface for a {@link BinaryObject} of the issue_6.
 */
public interface BinaryObject extends GenericEntity<String> {

  /**
   * @param mimeType is the MIME-Type of this {@link BinaryObject}
   */
  void setMimeType(String mimeType);

  /**
   * Returns MIME-Type of thie {@link BinaryObject}
   *
   * @return the MIME-Type, e.g image/jpeg
   */
  String getMimeType();

  /**
   * @return Returns the size in bytes
   */
  long getSize();

  /**
   * Sets the size of bytes
   *
   * @param size the size in bytes
   */
  void setSize(long size);

}
