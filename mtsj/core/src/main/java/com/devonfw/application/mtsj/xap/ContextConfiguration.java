package com.devonfw.application.mtsj.xap;

import org.openspaces.core.GigaSpace;
import org.openspaces.core.GigaSpaceConfigurer;
import org.openspaces.core.space.UrlSpaceConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.gigaspaces.repository.config.EnableGigaspacesRepositories;

/**
 * @author dpatesan
 *
 */
@Configuration
@Profile("xap")
@EnableGigaspacesRepositories("com.devonfw.application.mtsj")
public class ContextConfiguration {
  /**
   * Creates a bean representing a space. The space is identified by it's name, which is contained in the URL. A Space
   * with this name must previously be created and deployed to the Service Grid.
   *
   * @return A bean representing a space.
   */
  @Bean
  public GigaSpace space() {

    UrlSpaceConfigurer urlSpaceConfigurer = new UrlSpaceConfigurer("jini://*/*/CG-space");
    return new GigaSpaceConfigurer(urlSpaceConfigurer).gigaSpace();
  }
}
