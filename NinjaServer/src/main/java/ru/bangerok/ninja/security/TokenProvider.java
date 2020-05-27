package ru.bangerok.ninja.security;

import ru.bangerok.ninja.config.AppPropertiesConfig;
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
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class TokenProvider {

		private static final Logger logger = LoggerFactory.getLogger(TokenProvider.class);

		private final AppPropertiesConfig appProperties;

		public TokenProvider(AppPropertiesConfig appProperties) {
				this.appProperties = appProperties;
		}

		public String createToken(Authentication authentication) {
				OAuth2User userPrincipal = (OAuth2User) authentication.getPrincipal();

				return Jwts.builder()
						.setSubject(userPrincipal.getAttribute("sub"))
						.setIssuedAt(new Date())
						.setExpiration(
								new Date(new Date().getTime() + appProperties.getAuth().getTokenExpirationMsec()))
						.signWith(SignatureAlgorithm.HS512, appProperties.getAuth().getTokenSecret())
						.compact();
		}

		public String getUserIdFromToken(String token) {
				Claims claims = Jwts.parser()
						.setSigningKey(appProperties.getAuth().getTokenSecret())
						.parseClaimsJws(token)
						.getBody();

				return claims.getSubject();
		}

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
