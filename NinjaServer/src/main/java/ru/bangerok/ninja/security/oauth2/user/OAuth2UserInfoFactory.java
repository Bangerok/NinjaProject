package ru.bangerok.ninja.security.oauth2.user;

import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.bangerok.ninja.enumeration.AuthProvider;
import ru.bangerok.ninja.exception.auth.OAuth2AuthenticationProcessingException;
import ru.bangerok.ninja.security.oauth2.CustomOAuth2UserService;
import ru.bangerok.ninja.service.MessageService;

/**
 * A factory class that returns an oAuth2 authorization object with attributes obtained from an
 * external provider. Extensible to work with multiple external providers, not just Google.
 * <p>
 * Used for example here: {@link CustomOAuth2UserService}.
 *
 * @author v.kuznetsov
 * @since 0.3.0
 */
@RequiredArgsConstructor
@Component
public class OAuth2UserInfoFactory {

		private final MessageService messageService;

		/**
		 * Static object method with user data for oAuth2 authorization.
		 *
		 * @param registrationId authorization provider type.
		 * @param attributes     user attributes obtained from an external authorization provider
		 * @return {@link OAuth2UserInfo} after oAuth2 authorization.
		 * @throws OAuth2AuthenticationProcessingException if a non-Google provider is used for
		 *                                                 authorization.
		 */
		public OAuth2UserInfo getOAuth2UserInfo(String registrationId, Map<String, Object> attributes)
				throws OAuth2AuthenticationProcessingException {
				if (registrationId.equalsIgnoreCase(AuthProvider.google.toString())) {
						return new GoogleOAuth2UserInfo(attributes);
				} else {
						throw new OAuth2AuthenticationProcessingException(messageService.getMessageWithArgs(
								"auth.error.provider.not.supported", new Object[]{registrationId})
						);
				}
		}
}