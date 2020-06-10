package ru.bangerok.ninja.security.oauth2.user;

import java.util.Map;
import ru.bangerok.ninja.enumeration.AuthProvider;
import ru.bangerok.ninja.security.error.OAuth2AuthenticationProcessingException;
import ru.bangerok.ninja.security.oauth2.CustomOAuth2UserService;

/**
 * Фабричный класс, который возвращает объект oauth2 авторизации с атрибутами, полученными от
 * внешнего провайдера. Можно расширить для работы с несколькими внешними провайдерами, а не только
 * с Google.
 * <p>
 * Используется, например, здесь: {@link CustomOAuth2UserService}.
 *
 * @author v.kuznetsov
 * @version 1.0
 */
public class OAuth2UserInfoFactory {

		/**
		 * Static метод объекта с данными пользователя при oauth2 авторизации.
		 *
		 * @param registrationId тип провайдера авторизации.
		 * @param attributes     атрибуты пользователя, полученные с внешнего провайдера авторизации
		 * @return данные пользователя, полученные после oauth2 авторизации.
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
