package ru.bangerok.enterprise.ninja;

import static org.springframework.boot.SpringApplication.run;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

/**
 * <p> A class to run the entire spring application. To run, just use the command - mvn spring-boot:
 * run. </p>
 *
 * @author Vladislav [Bangerok] Kuznetsov.
 * @since 0.0.0
 */
@SpringBootApplication
@ConfigurationPropertiesScan("ru.bangerok.enterprise.ninja.config.properties")
public class NinjaWebApplication {

  public static void main(String[] args) {
    run(NinjaWebApplication.class, args);
  }
}