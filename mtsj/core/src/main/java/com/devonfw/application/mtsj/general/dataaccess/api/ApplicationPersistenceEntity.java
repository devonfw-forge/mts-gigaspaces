package com.devonfw.application.mtsj.general.dataaccess.api;

import com.devonfw.module.basic.common.api.entity.PersistenceEntity;
import com.gigaspaces.annotation.pojo.SpaceId;
import com.gigaspaces.annotation.pojo.SpaceRouting;
import com.gigaspaces.annotation.pojo.SpaceVersion;

/**
 * Abstract Entity for all Entities with an id and a version field.
 *
 */
public abstract class ApplicationPersistenceEntity implements PersistenceEntity<String> {

  private static final long serialVersionUID = 1L;

  /** @see #getId() */
  private String id;

  /** @see #getModificationCounter() */
  private int modificationCounter;

  /**
   * The constructor.
   */
  public ApplicationPersistenceEntity() {

    super();
  }

  @Override
  @SpaceId(autoGenerate = false)
  @SpaceRouting
  public String getId() {

    return this.id;
  }

  /**
   * {@inheritDoc}
   */

  @Override
  public void setId(String id) {

    this.id = id;
  }

  @Override
  @SpaceVersion
  public int getModificationCounter() {

    return this.modificationCounter;
  }

  @Override
  public void setModificationCounter(int version) {

    this.modificationCounter = version;
  }

  @Override
  public String toString() {

    StringBuilder buffer = new StringBuilder();
    toString(buffer);
    return buffer.toString();
  }

  /**
   * Method to extend {@link #toString()} logic.
   *
   * @param buffer is the {@link StringBuilder} where to {@link StringBuilder#append(Object) append} the string
   *        representation.
   */
  protected void toString(StringBuilder buffer) {

    buffer.append(getClass().getSimpleName());
    if (this.id != null) {
      buffer.append("[id=");
      buffer.append(this.id);
      buffer.append("]");
    }
  }
}