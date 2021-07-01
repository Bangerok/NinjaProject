package ru.bangerok.ninja.util;

import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.util.SerializationUtils;

/**
 * <p> Class for working with cookies. Used before sending an authorization request to the provider
 * and after receiving a response from him. </p>
 *
 * @author v.kuznetsov
 * @since 0.3.0
 */
public class CookieUtils {

  private static final List<String> WHITE_LIST =
      Arrays.asList("oauth2_auth_request", "redirect_uri");

  private CookieUtils() {
    throw new IllegalStateException("Utility class");
  }

  /**
   * Static method for getting cookie from request.
   *
   * @param request request.
   * @param name    cookie name.
   * @return optional с cookie.
   */
  public static Optional<Cookie> getCookie(HttpServletRequest request, String name) {
    var cookies = request.getCookies();

    if (cookies != null && cookies.length > 0) {
      for (var cookie : cookies) {
        if (cookie.getName().equals(name)) {
          return Optional.of(cookie);
        }
      }
    }

    return Optional.empty();
  }

  /**
   * Static method for adding a new cookie to the request response.
   *
   * @param response response.
   * @param name     the name of the new cookie.
   * @param value    the value of the new cookie.
   */
  public static void addCookie(HttpServletResponse response, String name, String value,
                               int maxAge) {
    var cookie = new Cookie(name, value);
    cookie.setPath("/");
    cookie.setHttpOnly(true);
    cookie.setMaxAge(maxAge);
    response.addCookie(cookie);
  }

  /**
   * Static method to remove cookie from request response.
   *
   * @param request  request.
   * @param response response.
   * @param name     the name of the old cookie.
   */
  public static void deleteCookie(HttpServletRequest request, HttpServletResponse response,
                                  String name) {
    var cookies = request.getCookies();
    if (cookies != null && cookies.length > 0 && WHITE_LIST.contains(name)) {
      Arrays.stream(cookies).filter(cookie -> cookie.getName().equals(name)).forEach(cookie -> {
        cookie.setValue("");
        cookie.setPath("/");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
      });
    }
  }

  /**
   * Static method for serializing cookie.
   *
   * @param object cookie.
   * @return the string representation of the cookie.
   */
  public static String serialize(Object object) {
    return Base64.getUrlEncoder().encodeToString(SerializationUtils.serialize(object));
  }

  /**
   * Static method to deserialize the string representation of the cookie.
   *
   * @param cookie cookie.
   * @param cls    the class for the value in the cookie.
   * @return cookie object representation.
   */
  public static <T> T deserialize(Cookie cookie, Class<T> cls) {
    return cls
        .cast(SerializationUtils.deserialize(Base64.getUrlDecoder().decode(cookie.getValue())));
  }
}