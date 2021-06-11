package ru.bangerok.ninja.security.oauth2;

import static ru.bangerok.ninja.enumeration.Roles.ROLE_USER;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import ru.bangerok.ninja.config.SecurityConfig;
import ru.bangerok.ninja.enumeration.AuthProvider;
import ru.bangerok.ninja.exception.auth.OAuth2AuthenticationProcessingException;
import ru.bangerok.ninja.exception.resource.ResourceNotFoundException;
import ru.bangerok.ninja.persistence.dao.base.RepositoryLocator;
import ru.bangerok.ninja.persistence.model.user.Role;
import ru.bangerok.ninja.persistence.model.user.User;
import ru.bangerok.ninja.security.UserPrincipal;
import ru.bangerok.ninja.security.oauth2.user.OAuth2UserInfo;
import ru.bangerok.ninja.security.oauth2.user.OAuth2UserInfoFactory;
import ru.bangerok.ninja.service.MessageService;

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

  private final MessageService messageService;
  private final RepositoryLocator repositoryLocator;
  private final OAuth2UserInfoFactory userInfoFactory;

  /**
   * Method for getting user authentication after successful authorization from external
   * provider.
   *
   * @param oAuth2UserRequest oauth2 authorization request.
   * @return {@link OAuth2User} after successful authorization.
   * @throws OAuth2AuthenticationProcessingException email not found from OAuth2 provider.
   * @throws ResourceNotFoundException               a role with this name was not found.
   */
  @Override
  public OAuth2User loadUser(OAuth2UserRequest oAuth2UserRequest)
      throws OAuth2AuthenticationProcessingException, ResourceNotFoundException {
    OAuth2User oAuth2User = super.loadUser(oAuth2UserRequest);

    return processOAuth2User(oAuth2UserRequest, oAuth2User);
  }

  /**
   * Method for creating an authentication user after successful authorization from an external
   * provider.
   *
   * @param oAuth2UserRequest oauth2 authorization request.
   * @param oAuth2User        oauth2 user with data after authorization.
   * @return {@link OAuth2User} after creating it.
   * @throws OAuth2AuthenticationProcessingException email not found from OAuth2 provider.
   * @throws ResourceNotFoundException               a role with this name was not found.
   */
  private OAuth2User processOAuth2User(OAuth2UserRequest oAuth2UserRequest, OAuth2User oAuth2User)
      throws OAuth2AuthenticationProcessingException, ResourceNotFoundException {
    OAuth2UserInfo oAuth2UserInfo = userInfoFactory
        .getOAuth2UserInfo(oAuth2UserRequest.getClientRegistration().getRegistrationId(),
            oAuth2User.getAttributes());

    final String email = oAuth2UserInfo.getEmail();
    if (!StringUtils.hasText(email)) {
      throw new OAuth2AuthenticationProcessingException(messageService.getMessageWithArgs(
          "auth.error.email.not.found", new Object[] {email})
      );
    }

    User user = repositoryLocator.getUserRepository().findByEmail(email)
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
   * @param oAuth2UserRequest oAuth2 authorization request.
   * @param oAuth2UserInfo    oAuth2 user data.
   * @return Registered {@link User}.
   * @throws ResourceNotFoundException a role with this name was not found.
   */
  private User registerNewUser(OAuth2UserRequest oAuth2UserRequest,
                               OAuth2UserInfo oAuth2UserInfo) throws ResourceNotFoundException {
    User user = new User();
    user.setAuthProvider(
        AuthProvider.valueOf(oAuth2UserRequest.getClientRegistration().getRegistrationId()));
    user.setProviderId(oAuth2UserInfo.getId());
    user.setFullname(oAuth2UserInfo.getName());
    user.setEmail(oAuth2UserInfo.getEmail());
    user.setAvatar(oAuth2UserInfo.getImageUrl());
    user.setLastVisit(LocalDateTime.now());
    user.setEmailVerified(true);

    Role userRole = repositoryLocator.getRoleRepository().findByValue(ROLE_USER).orElseThrow(
        () -> new ResourceNotFoundException(messageService.getMessage("role.error.not.found"))
    );
    List<Role> roles = Stream.of(userRole)
        .collect(Collectors.toCollection(ArrayList::new));
    user.setRoles(roles);

    return repositoryLocator.getUserRepository().save(user);
  }

  /**
   * Method for updating a user in a database.
   *
   * @param existingUser   existing user.
   * @param oAuth2UserInfo oauth2 user data.
   * @return Updated {@link User}.
   */
  private User updateExistingUser(User existingUser, OAuth2UserInfo oAuth2UserInfo) {
    existingUser.setFullname(oAuth2UserInfo.getName());
    existingUser.setAvatar(oAuth2UserInfo.getImageUrl());
    existingUser.setLastVisit(LocalDateTime.now());
    return repositoryLocator.getUserRepository().save(existingUser);
  }
}