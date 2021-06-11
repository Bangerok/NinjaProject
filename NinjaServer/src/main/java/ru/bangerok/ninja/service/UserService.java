package ru.bangerok.ninja.service;

import org.springframework.security.core.AuthenticationException;
import ru.bangerok.ninja.exception.resource.ResourceAlreadyExistException;
import ru.bangerok.ninja.exception.resource.ResourceNotFoundException;
import ru.bangerok.ninja.persistence.model.user.User;
import ru.bangerok.ninja.persistence.model.user.VerificationToken;
import ru.bangerok.ninja.rest.payload.request.LoginRequest;
import ru.bangerok.ninja.rest.payload.request.RegisterRequest;
import ru.bangerok.ninja.security.UserPrincipal;

/**
 * Service class for working with user entity and its authorization/authentication.
 *
 * @author v.kuznetsov
 * @since 0.3.15
 */
public interface UserService {

  /**
   * Method for getting data of the current authenticated user.
   *
   * @param currentUser current logged in user.
   * @return {@link User} or null.
   */
  User getCurrentUser(UserPrincipal currentUser);

  /**
   * Method for authenticating the logged in user and creating an authentication token for him.
   *
   * @param loginData user authentication data.
   * @return AuthenticationToken or throwing an exception.
   * @throws AuthenticationException   invalid credentials for user authentication.
   * @throws ResourceNotFoundException no user found by email.
   */
  String creatingTokenForAuthUser(LoginRequest loginData)
      throws AuthenticationException, ResourceNotFoundException;

  /**
   * Method for registering a new user and storing it in the database.
   *
   * @param registerData user registration data.
   * @return {@link User} or throwing an exception.
   * @throws ResourceAlreadyExistException a user with this email already exists.
   * @throws ResourceNotFoundException     a role with this name was not found.
   */
  User registerNewUserAccount(RegisterRequest registerData)
      throws ResourceAlreadyExistException, ResourceNotFoundException;

  /**
   * Method for obtaining a verification token from the database by its token value.
   *
   * @param verificationToken email verification token.
   * @return {@link VerificationToken} or throwing an exception.
   * @throws ResourceNotFoundException no verification token found by field value - token.
   */
  VerificationToken getVerificationToken(String verificationToken)
      throws ResourceNotFoundException;

  /**
   * Method for updating the verification token with a new token value.
   *
   * @param existingVerificationToken the existing value of the verification token.
   * @return {@link VerificationToken} by existing verification token or throwing an exception.
   * @throws ResourceNotFoundException no verification token found by field value - token.
   */
  VerificationToken generateNewVerificationToken(String existingVerificationToken)
      throws ResourceNotFoundException;

  /**
   * Method for creating and saving a token for email verification.
   *
   * @param user registering user.
   * @return verification token.
   */
  VerificationToken createVerificationTokenForUser(User user);

  /**
   * Method for saving user data to database.
   *
   * @param user user data.
   */
  void saveRegisteredUser(User user);
}