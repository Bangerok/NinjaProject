package ru.bangerok.ninja.security.oauth2;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;
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
import ru.bangerok.ninja.domain.Role;
import ru.bangerok.ninja.domain.User;
import ru.bangerok.ninja.enumeration.AuthProvider;
import ru.bangerok.ninja.exception.OAuth2AuthenticationProcessingException;
import ru.bangerok.ninja.repo.RoleRepository;
import ru.bangerok.ninja.repo.UserRepository;
import ru.bangerok.ninja.security.UserPrincipal;
import ru.bangerok.ninja.security.oauth2.user.OAuth2UserInfo;
import ru.bangerok.ninja.security.oauth2.user.OAuth2UserInfoFactory;

/**
 * Сервисный класс, который обновляет или регистрирует нового пользователя, используя полученные
 * данные с внешнего провайдера авторизации.
 * <p>
 * Подключается здесь: {@link SecurityConfig}.
 *
 * @author v.kuznetsov
 * @version 1.0
 */
@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

		private final UserRepository userRepository;
		private final RoleRepository roleRepository;

		public CustomOAuth2UserService(
				UserRepository userRepository, RoleRepository roleRepository) {
				this.userRepository = userRepository;
				this.roleRepository = roleRepository;
		}

		/**
		 * Метод для получения пользователя аутентификации после успешной авторизации с внешнего
		 * провайдера.
		 *
		 * @param oAuth2UserRequest запрос авторизации oauth2.
		 * @return аутентифицированный пользователь после успешной авторизации.
		 */
		@Override
		@Transactional
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
		 * Метод для создания пользователя аутентификации после успешной авторизации с внешнего
		 * провайдера.
		 *
		 * @param oAuth2UserRequest запрос авторизации oauth2.
		 * @param oAuth2User        пользователь oauth2 с данными после авторизации.
		 * @return аутентифицированный пользователь после его создания.
		 */
		private OAuth2User processOAuth2User(OAuth2UserRequest oAuth2UserRequest,
				OAuth2User oAuth2User) {
				OAuth2UserInfo oAuth2UserInfo = OAuth2UserInfoFactory
						.getOAuth2UserInfo(oAuth2UserRequest.getClientRegistration().getRegistrationId(),
								oAuth2User.getAttributes());
				if (StringUtils.isEmpty(oAuth2UserInfo.getEmail())) {
						throw new OAuth2AuthenticationProcessingException(
								"Email not found from OAuth2 provider");
				}

				Optional<User> userOptional = userRepository.findByEmail(oAuth2UserInfo.getEmail());
				User user;
				if (userOptional.isPresent()) {
						user = userOptional.get();
						if (!user.getAuthProvider().equals(AuthProvider
								.valueOf(oAuth2UserRequest.getClientRegistration().getRegistrationId()))) {
								throw new OAuth2AuthenticationProcessingException(
										"Looks like you're signed up with " +
												user.getAuthProvider() + " account. Please use your " + user
												.getAuthProvider() +
												" account to login.");
						}
						user = updateExistingUser(user, oAuth2UserInfo);
				} else {
						user = registerNewUser(oAuth2UserRequest, oAuth2UserInfo);
				}

				return UserPrincipal.create(user, oAuth2User.getAttributes());
		}

		/**
		 * Метод для сохранения нового пользователя в базу данных.
		 *
		 * @param oAuth2UserRequest запрос авторизации oauth2.
		 * @param oAuth2UserInfo    данные пользователя oauth2.
		 * @return пользователь.
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

				Optional<Role> optionalRole = roleRepository.findByName("ROLE_USER");

				if (optionalRole.isPresent()) {
						Set<Role> roles = Collections.singleton(optionalRole.get());
						user.setRoles(roles);
				}

				return userRepository.save(user);
		}

		/**
		 * Метод для обновления пользователя в базе данных.
		 *
		 * @param existingUser   существующий пользователь.
		 * @param oAuth2UserInfo данные пользователя oauth2.
		 * @return пользователь.
		 */
		private User updateExistingUser(User existingUser, OAuth2UserInfo oAuth2UserInfo) {
				existingUser.setFullname(oAuth2UserInfo.getName());
				existingUser.setAvatar(oAuth2UserInfo.getImageUrl());
				existingUser.setLastVisit(LocalDateTime.now());
				return userRepository.save(existingUser);
		}

}