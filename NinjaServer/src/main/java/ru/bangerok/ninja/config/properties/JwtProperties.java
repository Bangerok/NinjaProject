package ru.bangerok.ninja.config.properties;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import org.springframework.stereotype.Component;
import ru.bangerok.ninja.config.JwtPropertiesConfig;
import ru.bangerok.ninja.security.TokenProvider;

/**
 * Java class containing settings for generating JWT tokens.
 * <p>
 * Connects here: {@link JwtPropertiesConfig}.
 * <p>
 * Used for example here: {@link TokenProvider}.
 *
 * @author v.kuznetsov
 * @since 0.5.5
 */
@Getter
@Component
public class JwtProperties {

		/**
		 * Private field that stores information required to generate Json Web tokens.
		 */
		private final Auth auth = new Auth();

		/**
		 * Private field that stores information about redirect links after oauth2 authorization.
		 */
		private final OAuth2 oauth2 = new OAuth2();

		@Getter
		public static class Auth {

				/**
				 * Private field, which stores information about the secret of the token for its
				 * generation.
				 */
				private final String tokenSecret = "926D96C90030DD58429D2751AC1BDBBC";

				/**
				 * Private field that stores the duration of the token.
				 */
				private final long tokenExpirationMsec = 864000000;
		}

		@Getter
		public static final class OAuth2 {

				/**
				 * Private field that stores a list of redirect links used after oauth2 authorization.
				 */
				private final List<String> authorizedRedirectUris;

				public OAuth2() {
						authorizedRedirectUris = new ArrayList<>();
						authorizedRedirectUris.add("http://localhost:3000");
				}
		}
}
