package ru.bangerok.ninja.config;

import javax.sql.DataSource;
import liquibase.integration.spring.SpringLiquibase;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration java class for declaring and configuring a bean for database settings.
 *
 * @author v.kuznetsov
 * @since 0.5.5
 */
@Configuration
public class DatabaseConfig {

		/**
		 * Getting and configuring the settings object for connecting to the database.
		 *
		 * @return configuration object with database settings.
		 */
		@Bean
		public DataSource getDataSource() {
				DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
				dataSourceBuilder.driverClassName("org.postgresql.Driver");
				dataSourceBuilder
						.url("jdbc:postgresql://localhost:5024/ninja_database?serverTimezone=UTC+3");
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
				SpringLiquibase liquibase = new SpringLiquibase();
				liquibase.setChangeLog("classpath:liquibase/db.changelog-master.yml");
				liquibase.setDataSource(getDataSource());
				return liquibase;
		}
}