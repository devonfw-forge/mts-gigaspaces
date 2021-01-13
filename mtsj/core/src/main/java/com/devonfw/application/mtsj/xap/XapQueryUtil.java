package com.devonfw.application.mtsj.xap;

import java.util.List;

import com.devonfw.application.mtsj.bookingmanagement.dataaccess.api.BookingEntity;
import com.devonfw.module.basic.common.api.query.StringSearchConfigTo;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.StringExpression;

/**
 * @author dpatesan
 *
 *         Class based on com.devonfw.module.jpa.dataaccess.api.QueryUtil for creating the WHERE-Clause with QueryDSL
 *         functionalities.
 */
public class XapQueryUtil extends XapQueryHelper {

  private static final XapQueryUtil INSTANCE = new XapQueryUtil();

  @Override
  public BooleanExpression whereString(BooleanExpression query, StringExpression expression, String value,
      StringSearchConfigTo config) {

    return super.whereString(query, expression, value, config);
  }

  @Override
  public BooleanExpression where(BooleanExpression query, BooleanExpression expression) {

    return super.where(query, expression);
  }

  @Override
  public List<BookingEntity> findMultipleByTemplate(BookingEntity template) {

    return super.findMultipleByTemplate(template);
  }

  /**
   * @return the {@link XapQueryUtil} instance.
   */
  public static XapQueryUtil get() {

    return INSTANCE;
  }

}
