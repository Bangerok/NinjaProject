package ru.bangerok.ninja.service;

import ru.bangerok.ninja.controller.exception.user.UserAlreadyExistException;
import ru.bangerok.ninja.controller.payload.request.LoginRequest;
import ru.bangerok.ninja.controller.payload.request.RegisterRequest;
import ru.bangerok.ninja.persistence.model.user.User;
import ru.bangerok.ninja.persistence.model.user.VerificationToken;
import ru.bangerok.ninja.security.UserPrincipal;

/**
 * Service class for working with user entity and its authorization/authentication.
 *
 * @author v.kuznetsov
 * @since 0.3.15
 */
public interface UserService {

		/**
		 * Method for registering a new user and storing it in the database.
		 *
		 * @param registerData user registration data.
		 * @return created in the database, or if failed, null.
		 * @throws UserAlreadyExistException user already exists.
		 */
		User registerNewUserAccount(RegisterRequest registerData) throws UserAlreadyExistException;

		/**
		 * Method for authenticating the logged in user and creating an authentication token for him.
		 *
		 * @param loginData user authentication data.
		 * @return authentication token.
		 */
		String creatingTokenForAuthUser(LoginRequest loginData);

		/**
		 * Method for getting data of the current authenticated user.
		 *
		 * @param currentUser current logged in user.
		 * @return authenticated user data.
		 */
		User getCurrentUser(UserPrincipal currentUser);

		/**
		 * Method for getting the user who was issued the email verification token.
		 *
		 * @param verificationToken email verification token.
		 * @return the user for whom the email verification token was issued.
		 */
		User getUser(String verificationToken);

		/**
		 * Method for creating and saving a token for email verification.
		 *
		 * @param user registering user.
		 * @return verification token.
		 */
		VerificationToken createVerificationTokenForUser(User user);

		/**
		 * Method for updating the verification token with a new token value.
		 *
		 * @param existingVerificationToken the existing value of the verification token.
		 * @return updated verification token.
		 */
		VerificationToken generateNewVerificationToken(String existingVerificationToken);

		/**
		 * Method for obtaining a verification token from the database by its token value.
		 *
		 * @param verificationToken email verification token.
		 * @return verification token or null.
		 */
		VerificationToken getVerificationToken(String verificationToken);

		/**
		 * Method for saving user data to database.
		 *
		 * @param user user data.
		 */
		void saveRegisteredUser(User user);
}