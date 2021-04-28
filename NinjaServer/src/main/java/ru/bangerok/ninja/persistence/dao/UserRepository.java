package ru.bangerok.ninja.persistence.dao;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import ru.bangerok.ninja.controller.AuthController;
import ru.bangerok.ninja.persistence.model.user.User;

/**
 * Repository java класс для связи с базой данных и работой с сущностью пользователя.
 * <p>
 * Используется, например, здесь: {@link AuthController}.
 *
 * @author v.kuznetsov
 * @version 1.0
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

		/**
		 * Метод для поиска пользователя по его id в базе данных.
		 *
		 * @param id идентификатор пользователя.
		 * @return Optional с найденным пользователем.
		 */
		@SuppressWarnings("NullableProblems")
		@Override
		Optional<User> findById(Long id);

		/**
		 * Метод для поиска пользователя по его электронной почте в базе данных.
		 *
		 * @param email электронная почта пользователя.
		 * @return Optional с найденным пользователем.
		 */
		Optional<User> findByEmail(String email);

		/**
		 * Метод для поиска пользователя по его id с внешнего провайдера в базе данных.
		 *
		 * @param providerId идентификатор пользователя на внешнем провайдере авторизации.
		 * @return Optional с найденным пользователем.
		 */
		Optional<User> findByProviderId(String providerId);

		/**
		 * Метод для проверки на занятость электронной почты в базе данных.
		 *
		 * @param email проверяемая электронная почта пользователя.
		 * @return true, если почта уже существует, иначе false.
		 */
		boolean existsByEmail(String email);
}