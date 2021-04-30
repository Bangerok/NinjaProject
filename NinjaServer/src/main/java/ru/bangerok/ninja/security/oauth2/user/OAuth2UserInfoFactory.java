package ru.bangerok.ninja.security.oauth2.user;

import java.util.Map;
import ru.bangerok.ninja.enumeration.AuthProvider;
import ru.bangerok.ninja.security.error.OAuth2AuthenticationProcessingException;
import ru.bangerok.ninja.security.oauth2.CustomOAuth2UserService;

/**
 * A factory class that returns an oauth2 authorization object with attributes obtained from an
 * external provider. Extensible to work with multiple external providers, not just Google.
 * <p>
 * Used for example here: {@link CustomOAuth2UserService}.
 *
 * @author v.kuznetsov
 * @since 0.3.0
 */
public class OAuth2UserInfoFactory {

		/**
		 * Static object method with user data for oauth2 authorization.
		 *
		 * @param registrationId authorization provider type.
		 * @param attributes     user attributes obtained from an external authorization provider
		 * @return user data obtained after oauth2 authorization.
		 */
		public static OAuth2UserInfo getOAuth2UserInfo(String registrationId,
				Map<String, Object> attributes) {
				if (registrationId.equalsIgnoreCase(AuthProvider.google.toString())) {
						return new GoogleOAuth2UserInfo(attributes);
				} else {
						throw new OAuth2AuthenticationProcessingException(
								"Sorry! Login with " + registrationId + " is not supported yet.");
				}
		}
}