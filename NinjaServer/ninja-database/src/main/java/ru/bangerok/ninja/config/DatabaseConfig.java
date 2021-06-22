package ru.bangerok.ninja.config;

import javax.sql.DataSource;
import liquibase.integration.spring.SpringLiquibase;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

/**
 * Configuration java class for declaring and configuring a bean for database settings.
 *
 * @author v.kuznetsov
 * @since 0.5.5
 */
@Configuration
public class DatabaseConfig {

  /**
   * Entity manager configuration. Setting the property so that tables are not generated.
   *
   * @return configured entity manager.
   */
  @Bean
  public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
    var vendorAdapter = new HibernateJpaVendorAdapter();
    vendorAdapter.setDatabase(Database.POSTGRESQL);
    vendorAdapter.setGenerateDdl(false);

    var em = new LocalContainerEntityManagerFactoryBean();
    em.setDataSource(getDataSource());
    em.setPackagesToScan("ru.bangerok.ninja.persistence.model");
    em.setJpaVendorAdapter(vendorAdapter);
    return em;
  }

  /**
   * Getting and configuring the settings object for connecting to the database.
   *
   * @return configuration object with database settings.
   */
  @Bean
  public DataSource getDataSource() {
    var dataSourceBuilder = DataSourceBuilder.create();
    dataSourceBuilder.driverClassName("org.postgresql.Driver");
    dataSourceBuilder
        .url("jdbc:postgresql://localhost:5024/ninja_database");
    dataSourceBuilder.username("ninja");
    dataSourceBuilder.password("ninja");
    return dataSourceBuilder.build();
  }

  /**
   * Getting and configuring the settings object for liquibase (database versioning).
   *
   * @return configuration object with liquibase settings.
   */
  @Bean
  public SpringLiquibase liquibase() {
    var liquibase = new SpringLiquibase();
    liquibase.setChangeLog("classpath:liquibase/db.changelog-master.yml");
    liquibase.setDataSource(getDataSource());
    return liquibase;
  }
}