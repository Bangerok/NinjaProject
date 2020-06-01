package ru.bangerok.ninja.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import ru.bangerok.ninja.config.AppPropertiesConfig;
import ru.bangerok.ninja.controller.AuthController;
import ru.bangerok.ninja.controller.payload.request.LoginRequest;

/**
 * Сервисный класс, который содержит код для генерации и проверки Json Web токенов.
 * <p>
 * Используется, например, здесь: {@link AuthController#authenticateUser(LoginRequest)}.
 *
 * @author v.kuznetsov
 * @version 1.0
 */
@Service
public class TokenProvider {

		private static final Logger logger = LoggerFactory.getLogger(TokenProvider.class);

		private final AppPropertiesConfig appProperties;

		public TokenProvider(AppPropertiesConfig appProperties) {
				this.appProperties = appProperties;
		}

		/**
		 * Метод для генерации токена аутентификации из имеющихся данных пользователя.
		 *
		 * @param authentication информация об аутентификации пользователя.
		 * @return строка с токеном аутентификации.
		 */
		public String createToken(Authentication authentication) {
				UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();

				return Jwts.builder()
						.setSubject(String.valueOf(userPrincipal.getId()))
						.setIssuedAt(new Date())
						.setExpiration(
								new Date(new Date().getTime() + appProperties.getAuth().getTokenExpirationMsec()))
						.signWith(SignatureAlgorithm.HS512, appProperties.getAuth().getTokenSecret())
						.compact();
		}

		/**
		 * Метод для расшифровки токена для получения его данных.
		 *
		 * @param token токен с зашифрованными данными пользователя.
		 * @return идентификатор пользователя или идентификатор пользователя во внешнем провайдере.
		 */
		public long getUserIdFromToken(String token) {
				Claims claims = Jwts.parser()
						.setSigningKey(appProperties.getAuth().getTokenSecret())
						.parseClaimsJws(token)
						.getBody();

				return Long.parseLong(claims.getSubject());
		}

		/**
		 * Метод для проверки токена аутентификации для проверки на валидность.
		 *
		 * @param authToken строка с токеном для проверки.
		 * @return true, если токен валиден, иначе false.
		 */
		public boolean validateToken(String authToken) {
				try {
						Jwts.parser().setSigningKey(appProperties.getAuth().getTokenSecret())
								.parseClaimsJws(authToken);
						return true;
				} catch (SignatureException ex) {
						logger.error("Invalid JWT signature");
				} catch (MalformedJwtException ex) {
						logger.error("Invalid JWT token");
				} catch (ExpiredJwtException ex) {
						logger.error("Expired JWT token");
				} catch (UnsupportedJwtException ex) {
						logger.error("Unsupported JWT token");
				} catch (IllegalArgumentException ex) {
						logger.error("JWT claims string is empty.");
				}
				return false;
		}

}
