package com.devonfw.application.mtsj.xap;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

/**
 * @author dpatesan
 *
 *         Helper Class that makes the usage of beans, inside Entities, possible.
 */

@Service
public class BeanUtils implements ApplicationContextAware {

  private static ApplicationContext applicationContext;

  @Override
  public void setApplicationContext(ApplicationContext context) throws BeansException {

    setContext(context);
  }

  /**
   * Gets a bean
   *
   * @param c class of the bean
   * @return the requested bean
   */
  public static <T> T getBean(Class<T> c) {

    return applicationContext.getBean(c);
  }

  private static void setContext(ApplicationContext context) {

    applicationContext = context;
  }

}