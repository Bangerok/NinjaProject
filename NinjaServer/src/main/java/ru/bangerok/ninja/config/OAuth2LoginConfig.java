package ru.bangerok.ninja.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;
import org.springframework.security.oauth2.client.InMemoryOAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.AuthenticatedPrincipalOAuth2AuthorizedClientRepository;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository;

/**
 * Configuration java class for declaring and configuring a bean for oAuth2 login settings.
 *
 * @author v.kuznetsov
 * @since 0.5.5
 */
@Configuration
public class OAuth2LoginConfig {

		/**
		 * Constructs and return an InMemoryClientRegistrationRepository using the provided parameters.
		 */
		@Bean
		public ClientRegistrationRepository clientRegistrationRepository() {
				return new InMemoryClientRegistrationRepository(this.googleClientRegistration());
		}

		/**
		 * Constructs and return an InMemoryOAuth2AuthorizedClientService using the provided
		 * parameters.
		 *
		 * @param clientRegistrationRepository a repository for OAuth 2.0 / OpenID Connect 1.0.
		 */
		@Bean
		public OAuth2AuthorizedClientService authorizedClientService(
				ClientRegistrationRepository clientRegistrationRepository) {
				return new InMemoryOAuth2AuthorizedClientService(clientRegistrationRepository);
		}

		/**
		 * Constructs and return a AuthenticatedPrincipalOAuth2AuthorizedClientRepository using the
		 * provided parameters.
		 *
		 * @param authorizedClientService implementations of this interface are responsible for the
		 *                                management of Authorized Client(s).
		 */
		@Bean
		public OAuth2AuthorizedClientRepository authorizedClientRepository(
				OAuth2AuthorizedClientService authorizedClientService) {
				return new AuthenticatedPrincipalOAuth2AuthorizedClientRepository(authorizedClientService);
		}

		/**
		 * Google provider configuration for authorization.
		 *
		 * @return a new ClientRegistration.
		 */
		@SuppressWarnings("SpellCheckingInspection")
		private ClientRegistration googleClientRegistration() {
				return CommonOAuth2Provider.GOOGLE.getBuilder("google")
						.clientId("707167609466-40pekve7j2peuaf08b9e7jf8jdbp3hd7.apps.googleusercontent.com")
						.clientSecret("ILwB054_dK4tFTNknvuj0M0v")
						.redirectUri("{baseUrl}/login/oauth2/code/{registrationId}")
						.scope("profile", "email")
						.build();
		}
}