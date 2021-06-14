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
import ru.bangerok.ninja.exception.auth.Oauth2AuthenticationProcessingException;
import ru.bangerok.ninja.exception.resource.ResourceNotFoundException;
import ru.bangerok.ninja.persistence.dao.base.RepositoryLocator;
import ru.bangerok.ninja.persistence.model.user.Role;
import ru.bangerok.ninja.persistence.model.user.User;
import ru.bangerok.ninja.security.UserPrincipal;
import ru.bangerok.ninja.security.oauth2.user.Oauth2UserInfo;
import ru.bangerok.ninja.security.oauth2.user.Oauth2UserInfoFactory;
import ru.bangerok.ninja.service.MessageService;

/**
 * <p> A service class that updates or registers a new user using data received from an external
 * authorization provider. </p>
 * Connects here: {@link SecurityConfig}.
 *
 * @author v.kuznetsov
 * @since 0.3.0
 */
@RequiredArgsConstructor
@Service
@Transactional
public class CustomOauth2UserService extends DefaultOAuth2UserService {

  private final MessageService messageService;
  private final RepositoryLocator repositoryLocator;
  private final Oauth2UserInfoFactory userInfoFactory;

  /**
   * Method for getting user authentication after successful authorization from external
   * provider.
   *
   * @param oauth2UserRequest oauth2 authorization request.
   * @return {@link OAuth2User} after successful authorization.
   * @throws Oauth2AuthenticationProcessingException email not found from OAuth2 provider.
   * @throws ResourceNotFoundException               a role with this name was not found.
   */
  @Override
  public OAuth2User loadUser(OAuth2UserRequest oauth2UserRequest)
      throws Oauth2AuthenticationProcessingException, ResourceNotFoundException {
    OAuth2User oauth2User = super.loadUser(oauth2UserRequest);

    return processOauth2User(oauth2UserRequest, oauth2User);
  }

  /**
   * Method for creating an authentication user after successful authorization from an external
   * provider.
   *
   * @param oauth2UserRequest oauth2 authorization request.
   * @param oauth2User        oauth2 user with data after authorization.
   * @return {@link OAuth2User} after creating it.
   * @throws Oauth2AuthenticationProcessingException email not found from OAuth2 provider.
   * @throws ResourceNotFoundException               a role with this name was not found.
   */
  private OAuth2User processOauth2User(OAuth2UserRequest oauth2UserRequest, OAuth2User oauth2User)
      throws Oauth2AuthenticationProcessingException, ResourceNotFoundException {
    Oauth2UserInfo oauth2UserInfo = userInfoFactory
        .getOauth2UserInfo(oauth2UserRequest.getClientRegistration().getRegistrationId(),
            oauth2User.getAttributes());

    final String email = oauth2UserInfo.getEmail();
    if (!StringUtils.hasText(email)) {
      throw new Oauth2AuthenticationProcessingException(messageService.getMessageWithArgs(
          "auth.error.email.not.found", new Object[] {email})
      );
    }

    User user = repositoryLocator.getUserRepository().findByEmail(email)
        .map(
            value -> updateExistingUser(value, oauth2UserInfo)
        ).orElseGet(
            () -> registerNewUser(oauth2UserRequest, oauth2UserInfo)
        );

    return UserPrincipal.create(user, oauth2User.getAttributes());
  }

  /**
   * Method for saving a new user to the database.
   *
   * @param oauth2UserRequest oAuth2 authorization request.
   * @param oauth2UserInfo    oAuth2 user data.
   * @return registered {@link User}.
   * @throws ResourceNotFoundException a role with this name was not found.
   */
  private User registerNewUser(OAuth2UserRequest oauth2UserRequest,
                               Oauth2UserInfo oauth2UserInfo) throws ResourceNotFoundException {
    User user = new User();
    user.setAuthProvider(
        AuthProvider.valueOf(oauth2UserRequest.getClientRegistration().getRegistrationId()));
    user.setProviderId(oauth2UserInfo.getId());
    user.setFullname(oauth2UserInfo.getName());
    user.setEmail(oauth2UserInfo.getEmail());
    user.setAvatar(oauth2UserInfo.getImageUrl());
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
   * @param oauth2UserInfo oauth2 user data.
   * @return updated {@link User}.
   */
  private User updateExistingUser(User existingUser, Oauth2UserInfo oauth2UserInfo) {
    existingUser.setFullname(oauth2UserInfo.getName());
    existingUser.setAvatar(oauth2UserInfo.getImageUrl());
    existingUser.setLastVisit(LocalDateTime.now());
    return repositoryLocator.getUserRepository().save(existingUser);
  }
}