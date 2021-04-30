package ru.bangerok.ninja.config;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import ru.bangerok.ninja.NinjaApplication;
import ru.bangerok.ninja.security.TokenProvider;

/**
 * Config java class to load spring settings from application.yaml file by prefix - app.
 * <p>
 * Connects here: {@link NinjaApplication}.
 * <p>
 * Used for example here: {@link TokenProvider}.
 *
 * @author v.kuznetsov
 * @since 0.3.0
 */
@Data
@ConfigurationProperties("app")
public class AppPropertiesConfig {

		/**
		 * Private field that stores information required to generate Json Web tokens.
		 */
		private final Auth auth = new Auth();

		/**
		 * Private field that stores information about redirect links after oauth2 authorization.
		 */
		private final OAuth2 oauth2 = new OAuth2();

		@Data
		public static class Auth {

				/**
				 * Private field, which stores information about the secret of the token for its
				 * generation.
				 */
				private String tokenSecret;

				/**
				 * Private field that stores the duration of the token.
				 */
				private long tokenExpirationMsec;
		}

		@Data
		public static final class OAuth2 {

				/**
				 * Private field that stores a list of redirect links used after oauth2 authorization.
				 */
				private List<String> authorizedRedirectUris = new ArrayList<>();

				public OAuth2 authorizedRedirectUris(List<String> authorizedRedirectUris) {
						this.authorizedRedirectUris = authorizedRedirectUris;
						return this;
				}
		}
}