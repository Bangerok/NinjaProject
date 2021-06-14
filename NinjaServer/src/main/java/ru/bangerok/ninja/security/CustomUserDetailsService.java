package ru.bangerok.ninja.security;


import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bangerok.ninja.config.SecurityConfig;
import ru.bangerok.ninja.exception.resource.ResourceNotFoundException;
import ru.bangerok.ninja.persistence.dao.base.RepositoryLocator;
import ru.bangerok.ninja.persistence.model.user.User;

/**
 * <p> A service class that allows you to get a user in some way. </p>
 * Connects here: {@link SecurityConfig}.
 *
 * @author v.kuznetsov
 * @since 0.3.0
 */
@RequiredArgsConstructor
@Service
@Transactional
public class CustomUserDetailsService implements UserDetailsService {

  private final RepositoryLocator repositoryLocator;

  /**
   * Method to get create authenticated user based on user retrieved from database via email.
   *
   * @param email email.
   * @return {@link UserDetails} authenticated user.
   * @throws ResourceNotFoundException user not found by email.
   */
  @Override
  public UserDetails loadUserByUsername(String email) throws ResourceNotFoundException {
    User user = repositoryLocator.getUserRepository().findByEmail(email)
        .orElseThrow(() ->
            new ResourceNotFoundException("User with email - %s, not found.", email)
        );

    return UserPrincipal.create(user);
  }

  /**
   * Method to get create authenticated user based on user retrieved from database by id.
   *
   * @param id user ID.
   * @return {@link UserDetails} authenticated user.
   * @throws ResourceNotFoundException user not found by id.
   */
  public UserDetails loadUserById(Long id) throws ResourceNotFoundException {
    User user = repositoryLocator.getUserRepository().findById(id).orElseThrow(
        () -> new ResourceNotFoundException("User with id - %s, not found.", String.valueOf(id))
    );

    return UserPrincipal.create(user);
  }
}