package ru.bangerok.ninja.config;

import java.util.ArrayList;
import java.util.List;
import org.springframework.boot.context.properties.ConfigurationProperties;
import ru.bangerok.ninja.NinjaApplication;
import ru.bangerok.ninja.security.TokenProvider;

/**
 * Конфигурационный java класс для загрузки настроек spring из файла application.yaml по префиксу -
 * app.
 * <p>
 * Подключается здесь: {@link NinjaApplication}.
 * <p>
 * Используется, например, здесь: {@link TokenProvider}.
 *
 * @author v.kuznetsov
 * @version 1.0
 */
@ConfigurationProperties(prefix = "app")
public class AppPropertiesConfig {

		/**
		 * Private поле, в котором хранится информация, необходимая для генерации Json Web токенов.
		 */
		private final Auth auth = new Auth();

		/**
		 * Private поле, в котором хранится информация о redirect ссылках после oauth2 авторизации.
		 */
		private final OAuth2 oauth2 = new OAuth2();

		public Auth getAuth() {
				return auth;
		}

		public OAuth2 getOauth2() {
				return oauth2;
		}

		public static class Auth {

				/**
				 * Private поле, в котором хранится информация о секрете токена для его генерации.
				 */
				private String tokenSecret;

				/**
				 * Private поле, в котором хранится длительность действия токена.
				 */
				private long tokenExpirationMsec;

				public String getTokenSecret() {
						return tokenSecret;
				}

				public void setTokenSecret(String tokenSecret) {
						this.tokenSecret = tokenSecret;
				}

				public long getTokenExpirationMsec() {
						return tokenExpirationMsec;
				}

				public void setTokenExpirationMsec(long tokenExpirationMsec) {
						this.tokenExpirationMsec = tokenExpirationMsec;
				}
		}

		public static final class OAuth2 {

				/**
				 * Private поле, в котором хранится список redirect ссылок, используемых после oauth2
				 * авторизации.
				 */
				private List<String> authorizedRedirectUris = new ArrayList<>();

				public List<String> getAuthorizedRedirectUris() {
						return authorizedRedirectUris;
				}

				public OAuth2 authorizedRedirectUris(List<String> authorizedRedirectUris) {
						this.authorizedRedirectUris = authorizedRedirectUris;
						return this;
				}
		}
}
