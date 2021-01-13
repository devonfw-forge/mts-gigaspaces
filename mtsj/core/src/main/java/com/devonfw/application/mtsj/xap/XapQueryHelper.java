package com.devonfw.application.mtsj.xap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import org.openspaces.core.GigaSpace;

import com.devonfw.application.mtsj.bookingmanagement.dataaccess.api.BookingEntity;
import com.devonfw.module.basic.common.api.query.LikePatternSyntax;
import com.devonfw.module.basic.common.api.query.StringSearchConfigTo;
import com.devonfw.module.basic.common.api.query.StringSearchOperator;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.StringExpression;

/**
 * @author dpatesan
 *
 */
public class XapQueryHelper {

  /**
   * @param query the {@link BooleanExpression} to modify.
   * @param expression the {@link StringExpression} to search on.
   * @param value the string value or pattern to search for.
   * @param config the {@link StringSearchConfigTo} to configure the search. May be {@code null} for regular equals
   *        search.
   * @return the new query as BooleanExpression
   */
  protected BooleanExpression whereString(BooleanExpression query, StringExpression expression, String value,
      StringSearchConfigTo config) {

    BooleanExpression clause = newStringClause(expression, value, config);

    return where(query, clause);
  }

  /**
   * @param expression the {@link StringExpression} to search on.
   * @param value the string value or pattern to search for.
   * @param config the {@link StringSearchConfigTo} to configure the search. May be {@code null} for regular equals
   *        search as default fallback.
   * @return the new {@link BooleanExpression} for the specified string comparison clause.
   */
  protected BooleanExpression newStringClause(StringExpression expression, String value, StringSearchConfigTo config) {

    StringSearchOperator operator = StringSearchOperator.EQ;
    LikePatternSyntax syntax = null;
    boolean ignoreCase = false;
    boolean matchSubstring = false;
    if (config != null) {
      operator = config.getOperator();
      syntax = config.getLikeSyntax();
      ignoreCase = config.isIgnoreCase();
      matchSubstring = config.isMatchSubstring();
    }
    return newStringClause(expression, value, operator, syntax, ignoreCase, matchSubstring);
  }

  /**
   * @param expression the new {@link BooleanExpression} for the specified string comparison clause.
   * @param value the string value or pattern to search for.
   * @param operator the {@link StringSearchOperator} used to compare the search string {@code value}.
   * @param syntax the {@link LikePatternSyntax} of the given {@code pattern}.
   * @param ignoreCase {@code true} to ignore the case, {@code false} otherwise (to search case-sensitive).
   * @param matchSubstring {@code true} to match also if the given {@code pattern} shall also match substrings on the
   *        given {@link StringExpression}.
   * @return the new {@link BooleanExpression} for the specified string comparison clause.
   */
  protected BooleanExpression newStringClause(StringExpression expression, String value, StringSearchOperator operator,
      LikePatternSyntax syntax, boolean ignoreCase, boolean matchSubstring) {

    if (operator == null) {
      if (syntax == null) {
        syntax = LikePatternSyntax.autoDetect(value);
        if (syntax == null) {
          operator = StringSearchOperator.EQ;
        } else {
          operator = StringSearchOperator.LIKE;
        }
      } else {
        operator = StringSearchOperator.LIKE;
      }
    }
    if (matchSubstring && ((operator == StringSearchOperator.EQ) || (operator == StringSearchOperator.NE))) {
      if (syntax == null) {
        syntax = LikePatternSyntax.SQL;
      }
      if (operator == StringSearchOperator.EQ) {
        operator = StringSearchOperator.LIKE;
      } else {
        operator = StringSearchOperator.NOT_LIKE;
      }
    }
    String v = value;
    if (v == null) {
      switch (operator) {
        case LIKE:
        case EQ:
          return expression.isNull();
        case NE:
          return expression.isNotNull();
        default:
          throw new IllegalArgumentException("Operator " + operator + " does not accept null!");
      }
    } else if (v.isEmpty()) {
      switch (operator) {
        case LIKE:
        case EQ:
          return expression.isEmpty();
        case NOT_LIKE:
        case NE:
          return expression.isNotEmpty();
        default:
          // continue;
      }
    }
    StringExpression exp = expression;
    if (ignoreCase) {
      v = v.toUpperCase(Locale.US);
      exp = exp.upper();
    }
    switch (operator) {
      case LIKE:
        return newLikeClause(exp, v, syntax, false, matchSubstring, false);
      case NOT_LIKE:
        return newLikeClause(exp, v, syntax, false, matchSubstring, true);
      case EQ:
        return exp.eq(v);
      case NE:
        return exp.ne(v);
      case LT:
        return exp.lt(v);
      case LE:
        return exp.loe(v);
      case GT:
        return exp.gt(v);
      case GE:
        return exp.goe(v);
      default:
        throw new IllegalStateException("" + operator);
    }
  }

  /**
   * @param expression the {@link StringExpression} to {@link StringExpression#like(String) create the LIKE-clause}
   *        from.
   * @param pattern the pattern for the LIKE-clause to create.
   * @param syntax the {@link LikePatternSyntax} of the given {@code pattern}.
   * @param ignoreCase {@code true} to ignore the case, {@code false} otherwise (to search case-sensitive).
   * @param matchSubstring {@code true} to match also if the given {@code pattern} shall also match substrings on the
   *        given {@link StringExpression}.
   * @param negate - {@code true} for {@link StringExpression#notLike(String) NOT LIKE}, {@code false} for
   *        {@link StringExpression#like(String) LIKE}.
   * @return the LIKE-clause as {@link BooleanExpression}.
   */
  protected BooleanExpression newLikeClause(StringExpression expression, String pattern, LikePatternSyntax syntax,
      boolean ignoreCase, boolean matchSubstring, boolean negate) {

    if (syntax == null) {
      syntax = LikePatternSyntax.autoDetect(pattern);
      if (syntax == null) {
        syntax = LikePatternSyntax.SQL;
      }
    }
    String likePattern = LikePatternSyntax.SQL.convert(pattern, syntax, matchSubstring);
    StringExpression exp = expression;
    if (ignoreCase) {
      likePattern = likePattern.toUpperCase(Locale.US);
      exp = exp.upper();
    }
    BooleanExpression clause;
    if (syntax != LikePatternSyntax.SQL) {
      clause = exp.like(likePattern, LikePatternSyntax.ESCAPE);
    } else {
      clause = exp.like(likePattern);
    }
    if (negate) {
      clause = clause.not();
    }
    return clause;
  }

  /**
   * @param query the {@link BooleanExpression} to modify.
   * @param expression to be added to the query
   * @return the new query as BooleanExpression
   */
  public BooleanExpression where(BooleanExpression query, BooleanExpression expression) {

    BooleanExpression condition = null;
    if (expression != null) {

      condition = query == null ? expression : query.and(expression);
    }

    return condition;
  }

  /**
   * @param template
   * @return
   */
  public List<BookingEntity> findMultipleByTemplate(BookingEntity template) {

    GigaSpace gigaspace = BeanUtils.getBean(GigaSpace.class);
    BookingEntity[] found = gigaspace.readMultiple(template);
    return new ArrayList<>(Arrays.asList(found));
  }

}
