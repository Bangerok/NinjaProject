package ru.bangerok.ninja.security.oauth2.user;

import java.util.Map;

/**
 * Абстрактный класс с общими методами и полями для всех объектов классов (если из будет несколько)
 * данных пользователей, полученных с внешних провайдеров авторизации.
 * <p>
 * Используется, например, здесь: {@link GoogleOAuth2UserInfo}.
 *
 * @author v.kuznetsov
 * @version 1.0
 */
public abstract class OAuth2UserInfo {

		protected final Map<String, Object> attributes;

		public OAuth2UserInfo(Map<String, Object> attributes) {
				this.attributes = attributes;
		}

		public Map<String, Object> getAttributes() {
				return attributes;
		}

		public abstract String getId();

		public abstract String getName();

		public abstract String getEmail();

		public abstract String getImageUrl();
}
