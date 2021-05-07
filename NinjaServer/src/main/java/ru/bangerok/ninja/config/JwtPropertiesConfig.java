package ru.bangerok.ninja.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.bangerok.ninja.NinjaApplication;
import ru.bangerok.ninja.config.properties.JwtProperties;
import ru.bangerok.ninja.security.TokenProvider;

/**
 * Configuration java class for declaring and configuring a bean for generation jwt tokens.
 * <p>
 * Connects here: {@link NinjaApplication}.
 * <p>
 * Used for example here: {@link TokenProvider}.
 *
 * @author v.kuznetsov
 * @since 0.5.5
 */
@Configuration
public class JwtPropertiesConfig {

		/**
		 * Getting and configuring the settings object for generation jwt tokens.
		 *
		 * @return configuration object with generation jwt tokens settings.
		 */
		@Bean
		public JwtProperties getAppProperties() {
				return new JwtProperties();
		}
}