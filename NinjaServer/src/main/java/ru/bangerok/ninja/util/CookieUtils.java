package ru.bangerok.ninja.util;

import java.util.Base64;
import java.util.Optional;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.util.SerializationUtils;
import ru.bangerok.ninja.security.oauth2.HttpCookieOAuth2AuthorizationRequestRepository;

/**
 * Класс для работы с cookie. Используется перед отправкой запроса авторизации провайдеру и после
 * получения ответа от него.
 * <p>
 * Используется, например, здесь: {@link HttpCookieOAuth2AuthorizationRequestRepository#loadAuthorizationRequest(HttpServletRequest)}.
 *
 * @author v.kuznetsov
 * @version 1.0
 */
public class CookieUtils {

		/**
		 * Static метод для получения cookie из запроса.
		 *
		 * @param request запрос.
		 * @param name    наименование cookie.
		 * @return Optional с cookie.
		 */
		public static Optional<Cookie> getCookie(HttpServletRequest request, String name) {
				Cookie[] cookies = request.getCookies();

				if (cookies != null && cookies.length > 0) {
						for (Cookie cookie : cookies) {
								if (cookie.getName().equals(name)) {
										return Optional.of(cookie);
								}
						}
				}

				return Optional.empty();
		}

		/**
		 * Static метод для добавления нового cookie в ответ запроса.
		 *
		 * @param response ответ запроса.
		 * @param name     наименование нового cookie.
		 * @param value    значение нового cookie.
		 */
		public static void addCookie(HttpServletResponse response, String name, String value,
				int maxAge) {
				Cookie cookie = new Cookie(name, value);
				cookie.setPath("/");
				cookie.setHttpOnly(true);
				cookie.setMaxAge(maxAge);
				response.addCookie(cookie);
		}

		/**
		 * Static метод для удаления cookie из ответа запроса.
		 *
		 * @param request  запрос.
		 * @param response ответ запроса.
		 * @param name     наименование нового cookie.
		 */
		public static void deleteCookie(HttpServletRequest request, HttpServletResponse response,
				String name) {
				Cookie[] cookies = request.getCookies();
				if (cookies != null && cookies.length > 0) {
						for (Cookie cookie : cookies) {
								if (cookie.getName().equals(name)) {
										cookie.setValue("");
										cookie.setPath("/");
										cookie.setMaxAge(0);
										response.addCookie(cookie);
								}
						}
				}
		}

		/**
		 * Static метод для сериализации cookie.
		 *
		 * @param object cookie.
		 * @return строковое представление cookie.
		 */
		public static String serialize(Object object) {
				return Base64.getUrlEncoder()
						.encodeToString(SerializationUtils.serialize(object));
		}

		/**
		 * Static метод для десериализации строкового представления cookie.
		 *
		 * @param cookie cookie.
		 * @param cls    класс для значения в cookie.
		 * @return объектное представление cookie.
		 */
		public static <T> T deserialize(Cookie cookie, Class<T> cls) {
				return cls.cast(SerializationUtils.deserialize(
						Base64.getUrlDecoder().decode(cookie.getValue())));
		}
}