package ru.bangerok.enterprise.ninja.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Configuration java class for configuring allowed hosts for requests, allowed rest methods,
 * allowed headers in server requests, etc.
 *
 * @author Vladislav [Bangerok] Kuznetsov.
 * @since 0.3.0
 */
@Configuration
@EnableWebMvc
public class MvcConfig implements WebMvcConfigurer {

  /**
   * Method for adding CORS mapping.
   *
   * @param registry config file for CORS.
   */
  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/**")
        .allowedOriginPatterns("*")
        .allowedMethods("GET", "POST", "PUT", "PATCH")
        .allowedHeaders("*")
        .allowCredentials(true)
        .maxAge(3600);
  }
}