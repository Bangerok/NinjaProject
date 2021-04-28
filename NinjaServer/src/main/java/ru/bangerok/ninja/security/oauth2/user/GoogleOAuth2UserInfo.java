package ru.bangerok.ninja.security.oauth2.user;

import java.util.Map;

/**
 * Класс для данных пользователя, полученные через Google провайдер авторизации.
 * <p>
 * Используется, например, здесь: {@link OAuth2UserInfoFactory}.
 *
 * @author v.kuznetsov
 * @version 1.0
 */
public class GoogleOAuth2UserInfo extends OAuth2UserInfo {

		public GoogleOAuth2UserInfo(Map<String, Object> attributes) {
				super(attributes);
		}

		@Override
		public String getId() {
				return (String) attributes.get("sub");
		}

		@Override
		public String getName() {
				return (String) attributes.get("name");
		}

		@Override
		public String getEmail() {
				return (String) attributes.get("email");
		}

		@Override
		public String getImageUrl() {
				return (String) attributes.get("picture");
		}
}