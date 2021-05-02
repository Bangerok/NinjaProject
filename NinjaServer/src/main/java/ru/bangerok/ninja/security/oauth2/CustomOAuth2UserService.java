package ru.bangerok.ninja.security.oauth2;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import ru.bangerok.ninja.config.SecurityConfig;
import ru.bangerok.ninja.enumeration.AuthProvider;
import ru.bangerok.ninja.persistence.dao.base.RepositoryLocator;
import ru.bangerok.ninja.persistence.model.user.Role;
import ru.bangerok.ninja.persistence.model.user.User;
import ru.bangerok.ninja.security.UserPrincipal;
import ru.bangerok.ninja.security.error.OAuth2AuthenticationProcessingException;
import ru.bangerok.ninja.security.oauth2.user.OAuth2UserInfo;
import ru.bangerok.ninja.security.oauth2.user.OAuth2UserInfoFactory;

/**
 * A service class that updates or registers a new user using data received from an external
 * authorization provider.
 * <p>
 * Connects here: {@link SecurityConfig}.
 *
 * @author v.kuznetsov
 * @since 0.3.0
 */
@RequiredArgsConstructor
@Service
@Transactional
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

		private final RepositoryLocator repositoryLocator;

		/**
		 * Method for getting user authentication after successful authorization from external
		 * provider.
		 *
		 * @param oAuth2UserRequest oauth2 authorization request.
		 * @return authenticated user after successful authorization.
		 */
		@Override
		public OAuth2User loadUser(OAuth2UserRequest oAuth2UserRequest)
				throws OAuth2AuthenticationException {
				OAuth2User oAuth2User = super.loadUser(oAuth2UserRequest);

				try {
						return processOAuth2User(oAuth2UserRequest, oAuth2User);
				} catch (AuthenticationException ex) {
						throw ex;
				} catch (Exception ex) {
						throw new InternalAuthenticationServiceException(ex.getMessage(), ex.getCause());
				}
		}

		/**
		 * Method for creating an authentication user after successful authorization from an external
		 * provider.
		 *
		 * @param oAuth2UserRequest oauth2 authorization request.
		 * @param oAuth2User        oauth2 user with data after authorization.
		 * @return authenticated user after creating it.
		 */
		private OAuth2User processOAuth2User(OAuth2UserRequest oAuth2UserRequest,
				OAuth2User oAuth2User) {
				OAuth2UserInfo oAuth2UserInfo = OAuth2UserInfoFactory
						.getOAuth2UserInfo(oAuth2UserRequest.getClientRegistration().getRegistrationId(),
								oAuth2User.getAttributes());
				if (!StringUtils.hasText(oAuth2UserInfo.getEmail())) {
						throw new OAuth2AuthenticationProcessingException(
								"Email not found from OAuth2 provider");
				}

				User user = repositoryLocator.getUserRepository().findByEmail(oAuth2UserInfo.getEmail())
						.map(
								value -> updateExistingUser(value, oAuth2UserInfo)
						).orElseGet(
								() -> registerNewUser(oAuth2UserRequest, oAuth2UserInfo)
						);

				return UserPrincipal.create(user, oAuth2User.getAttributes());
		}

		/**
		 * Method for saving a new user to the database.
		 *
		 * @param oAuth2UserRequest oauth2 authorization request.
		 * @param oAuth2UserInfo    oauth2 user data.
		 * @return user.
		 */
		private User registerNewUser(OAuth2UserRequest oAuth2UserRequest,
				OAuth2UserInfo oAuth2UserInfo) {
				User user = new User();

				user.setAuthProvider(
						AuthProvider.valueOf(oAuth2UserRequest.getClientRegistration().getRegistrationId()));
				user.setProviderId(oAuth2UserInfo.getId());
				user.setFullname(oAuth2UserInfo.getName());
				user.setEmail(oAuth2UserInfo.getEmail());
				user.setAvatar(oAuth2UserInfo.getImageUrl());
				user.setLastVisit(LocalDateTime.now());
				user.setEmailVerified(true);

				Optional<Role> optionalRole = repositoryLocator.getRoleRepository().findByName("ROLE_USER");

				if (optionalRole.isPresent()) {
						Set<Role> roles = Collections.singleton(optionalRole.get());
						user.setRoles(roles);
				}

				return repositoryLocator.getUserRepository().save(user);
		}

		/**
		 * Method for updating a user in a database.
		 *
		 * @param existingUser   existing user.
		 * @param oAuth2UserInfo oauth2 user data.
		 * @return user.
		 */
		private User updateExistingUser(User existingUser, OAuth2UserInfo oAuth2UserInfo) {
				existingUser.setFullname(oAuth2UserInfo.getName());
				existingUser.setAvatar(oAuth2UserInfo.getImageUrl());
				existingUser.setLastVisit(LocalDateTime.now());
				return repositoryLocator.getUserRepository().save(existingUser);
		}
}