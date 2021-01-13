package com.devonfw.application.mtsj;

import java.util.Arrays;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.openspaces.core.GigaSpace;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

import com.devonfw.application.mtsj.dishmanagement.dataaccess.api.IngredientEntity;

@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class,
DataSourceTransactionManagerAutoConfiguration.class, HibernateJpaAutoConfiguration.class })
@EnableGlobalMethodSecurity(jsr250Enabled = true)
public class SpringBootApp {


  /**
   * Entry point for spring-boot based app
   *
   * @param args - arguments
   */
  public static void main(String[] args) {

    if (Arrays.stream(args).anyMatch((String e) -> e.contains("--spring.batch.job.names"))) {
      // if executing batch job, explicitly exit jvm to report error code from batch
      System.exit(SpringApplication.exit(SpringApplication.run(SpringBootApp.class, args)));
    } else {
      // normal web application start
      SpringApplication.run(SpringBootApp.class, args);
    }
  }


}
