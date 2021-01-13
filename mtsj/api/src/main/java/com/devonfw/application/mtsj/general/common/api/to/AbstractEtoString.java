package com.devonfw.application.mtsj.general.common.api.to;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.devonfw.module.basic.common.api.to.AbstractEto;
import com.devonfw.module.basic.common.api.to.AbstractGenericEto;

/**
 * @author dpatesan
 *
 */
public abstract class AbstractEtoString extends AbstractGenericEto<String> {

  private static final long serialVersionUID = 1L;

  private static final Logger LOG = LoggerFactory.getLogger(AbstractEto.class);

  private String id;

  /**
   * The constructor.
   */
  public AbstractEtoString() {

    super();
  }

  @Override
  public String getId() {

    return this.id;
  }

  @Override
  public void setId(String id) {

    this.id = id;
  }

  /**
   * Method to extend {@link #toString()} logic.
   *
   * @param buffer is the {@link StringBuilder} where to {@link StringBuilder#append(Object) append} the string
   *        representation.
   */
  @Override
  protected void toString(StringBuilder buffer) {

    super.toString(buffer);
    if (this.id != null) {
      buffer.append("[id=");
      buffer.append(this.id);
      buffer.append("]");
    }
  }

}
