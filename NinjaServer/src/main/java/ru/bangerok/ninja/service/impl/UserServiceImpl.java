package ru.bangerok.ninja.service.impl;

import static ru.bangerok.ninja.enumeration.Roles.ROLE_USER;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bangerok.ninja.enumeration.AuthProvider;
import ru.bangerok.ninja.exception.resource.ResourceAlreadyExistException;
import ru.bangerok.ninja.exception.resource.ResourceNotFoundException;
import ru.bangerok.ninja.persistence.dao.RoleRepository;
import ru.bangerok.ninja.persistence.dao.UserRepository;
import ru.bangerok.ninja.persistence.dao.VerificationTokenRepository;
import ru.bangerok.ninja.persistence.model.user.User;
import ru.bangerok.ninja.persistence.model.user.VerificationToken;
import ru.bangerok.ninja.rest.payload.request.LoginRequest;
import ru.bangerok.ninja.rest.payload.request.RegisterRequest;
import ru.bangerok.ninja.security.TokenProvider;
import ru.bangerok.ninja.security.UserPrincipal;
import ru.bangerok.ninja.service.MessageService;
import ru.bangerok.ninja.service.UserService;

/**
 * Service class (implementation) for working with user entity and its authorization/authentication.
 *
 * @author v.kuznetsov
 * @since 0.3.15
 */
@RequiredArgsConstructor
@Slf4j
@Transactional
@Service
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final RoleRepository roleRepository;
  private final VerificationTokenRepository tokenRepository;
  private final MessageService messageService;
  private final PasswordEncoder passwordEncoder;
  private final AuthenticationManager authenticationManager;
  private final TokenProvider tokenProvider;

  @Override
  public User getCurrentUser(UserPrincipal currentUser) {
    if (Objects.isNull(currentUser)) {
      return null;
    }

    return currentUser.getUser();
  }

  @Override
  public String creatingTokenForAuthUser(LoginRequest loginData) {
    var authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(loginData.email(), loginData.password())
    );

    var user = userRepository.findByEmail(loginData.email())
        .orElseThrow(() -> new ResourceNotFoundException(messageService.getMessageWithArgs(
            "user.error.not.found.by.email",
            new Object[] {loginData.email()}
        )));

    user.setLastVisit(LocalDateTime.now());
    userRepository.save(user);
    SecurityContextHolder.getContext().setAuthentication(authentication);
    return tokenProvider.createToken(authentication);
  }

  @Override
  public User registerNewUserAccount(RegisterRequest registerData) {
    if (userRepository.existsByEmail(registerData.email())) {
      throw new ResourceAlreadyExistException(messageService.getMessageWithArgs(
          "user.error.exist.email",
          new Object[] {registerData.email()}
      ));
    }

    var user = new User();
    user.setFullname(registerData.name());
    user.setEmailVerified(false);
    user.setEmail(registerData.email());
    user.setAuthProvider(AuthProvider.LOCAL);
    user.setPassword(passwordEncoder.encode(registerData.password()));
    var userRole = roleRepository.findByValue(ROLE_USER)
        .orElseThrow(() -> new ResourceNotFoundException(messageService.getMessageWithArgs(
            "role.error.not.found.by.name", new Object[] {ROLE_USER.getName()}
        )));
    user.setRoles(Stream.of(userRole).collect(Collectors.toCollection(ArrayList::new)));
    return userRepository.save(user);
  }

  @Override
  public VerificationToken getVerificationToken(String verificationToken) {
    return tokenRepository.findByValue(verificationToken)
        .orElseThrow(() -> new ResourceNotFoundException(messageService.getMessageWithArgs(
            "token.error.not.found.by.token", new Object[] {verificationToken}
        )));
  }

  @Override
  public VerificationToken generateNewVerificationToken(String existingVerificationToken) {
    var token = this.getVerificationToken(existingVerificationToken);
    token.setValue(UUID.randomUUID().toString());
    token.setExpiryDate(LocalDateTime.now().plusDays(1));
    return tokenRepository.save(token);
  }

  @Override
  public VerificationToken createVerificationTokenForUser(User user) {
    var myToken = new VerificationToken();
    myToken.setValue(UUID.randomUUID().toString());
    myToken.setUser(user);
    myToken.setExpiryDate(LocalDateTime.now().plusDays(1));
    return tokenRepository.save(myToken);
  }

  @Override
  public void saveRegisteredUser(User user) {
    userRepository.save(user);
  }
}