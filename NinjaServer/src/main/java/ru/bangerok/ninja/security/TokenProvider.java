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
 * Service class that contains code for generating and validating Json Web tokens.
 * <p>
 * Used for example here: {@link AuthController#authenticateUser(LoginRequest)}.
 *
 * @author v.kuznetsov
 * @since 0.3.0
 */
@Service
public class TokenProvider {

		private static final Logger logger = LoggerFactory.getLogger(TokenProvider.class);

		private final AppPropertiesConfig appProperties;

		public TokenProvider(AppPropertiesConfig appProperties) {
				this.appProperties = appProperties;
		}

		/**
		 * Method for generating an authentication token from existing user data.
		 *
		 * @param authentication user authentication information.
		 * @return authentication token string.
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
		 * Method for decrypting a token to get its data.
		 *
		 * @param token token with encrypted user data.
		 * @return user id or user id in external provider.
		 */
		public long getUserIdFromToken(String token) {
				Claims claims = Jwts.parser()
						.setSigningKey(appProperties.getAuth().getTokenSecret())
						.parseClaimsJws(token)
						.getBody();

				return Long.parseLong(claims.getSubject());
		}

		/**
		 * Method for checking the authentication token for validation.
		 *
		 * @param authToken token string for verification.
		 * @return true if the token is valid, otherwise false.
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