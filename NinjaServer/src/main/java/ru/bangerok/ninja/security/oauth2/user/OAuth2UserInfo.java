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

		/**
		 * Protected поле, в котором хранятся атрибуты пользователя с внешнего провайдера авторизации.
		 */
		protected final Map<String, Object> attributes;

		public OAuth2UserInfo(Map<String, Object> attributes) {
				this.attributes = attributes;
		}

		/**
		 * Абстрактный метод для получения идентификатора пользователя во внешнем провайдере
		 * авторизации.
		 *
		 * @return идентификатор пользователя во внешнем провайдере авторизации.
		 */
		public abstract String getId();

		/**
		 * Абстрактный метод для получения имени пользователя во внешнем провайдере авторизации.
		 *
		 * @return имя пользователя во внешнем провайдере авторизации.
		 */
		public abstract String getName();

		/**
		 * Абстрактный метод для получения электронной почты пользователя во внешнем провайдере
		 * авторизации.
		 *
		 * @return электронная почта пользователя во внешнем провайдере авторизации.
		 */
		public abstract String getEmail();

		/**
		 * Абстрактный метод для получения аватарки пользователя во внешнем провайдере авторизации.
		 *
		 * @return ссылка на аватарку пользователя во внешнем провайдере авторизации.
		 */
		public abstract String getImageUrl();
}
