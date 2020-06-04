package ru.bangerok.ninja.service;

import ru.bangerok.ninja.controller.exception.UserAlreadyExistException;
import ru.bangerok.ninja.controller.payload.request.LoginRequest;
import ru.bangerok.ninja.controller.payload.request.RegisterRequest;
import ru.bangerok.ninja.persistence.model.user.User;
import ru.bangerok.ninja.persistence.model.user.VerificationToken;
import ru.bangerok.ninja.security.UserPrincipal;

/**
 * Сервисный класс для работы с сущностью пользователя и его авторизацией/аутентификацией.
 *
 * @author v.kuznetsov
 * @version 1.0
 */
public interface UserService {

		/**
		 * Метод для регистрации нового пользователя и сохранение его в базе данных.
		 *
		 * @param registerData данные для регистрации пользователя.
		 * @return созданный в базе данных или, если не удалось, null.
		 * @throws UserAlreadyExistException пользователь уже существует.
		 */
		User registerNewUserAccount(RegisterRequest registerData) throws UserAlreadyExistException;

		/**
		 * Метод для аутентификации авторизующегося пользователя и создание токена аутентификации для
		 * него.
		 *
		 * @param loginData данные для аутентификации пользователя.
		 * @return токен аутентификации.
		 */
		String creatingTokenForAuthentificateUser(LoginRequest loginData);

		/**
		 * Метод для получения данных текущего аутентифицированного пользователя.
		 *
		 * @param currentUser текущий авторизованнай пользователь.
		 * @return данные аутентифицированного пользователя.
		 */
		User getCurrentUser(UserPrincipal currentUser);

		/**
		 * Метод для создания и сохранения токена для верификации электронной почты.
		 *
		 * @param user регистрирующийся пользователь.
		 * @return токен верификации.
		 */
		VerificationToken createVerificationTokenForUser(User user);

		/**
		 * Метод для получения токена верификации из базы данных по его значению токена.
		 *
		 * @param verificationToken регистрирующийся пользователь.
		 * @return токен верификации или null.
		 */
		VerificationToken getVerificationToken(String verificationToken);

		/**
		 * Метод для сохранения данных пользователя в базу данных.
		 *
		 * @param user пользователь.
		 */
		void saveRegisteredUser(User user);
}
