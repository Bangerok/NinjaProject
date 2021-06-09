package ru.bangerok.ninja.persistence.dao;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;
import ru.bangerok.ninja.rest.controllers.auth.AuthController;
import ru.bangerok.ninja.persistence.model.user.User;

/**
 * Repository java class for communicating with the database and working with the user entity.
 * <p>
 * Used for example here: {@link AuthController}.
 *
 * @author v.kuznetsov
 * @since 0.1.0
 */
@Repository
public interface UserRepository extends ReactiveCrudRepository<User, Long> {

		/**
		 * Method for finding a user by his id in the database.
		 *
		 * @param id user ID.
		 * @return Mono with found user.
		 */
		@SuppressWarnings("NullableProblems")
		Mono<User> findById(Long id);

		/**
		 * Method for finding a user by his email in the database.
		 *
		 * @param email user's email.
		 * @return Mono with found user.
		 */
		Mono<User> findByEmail(String email);

		/**
		 * Method for finding a user by his id from an external provider in the database.
		 *
		 * @param providerId user id on the external authorization provider.
		 * @return Mono with found user.
		 */
		Mono<User> findByProviderId(String providerId);

		/**
		 * Method for checking for the existence of email in the database.
		 *
		 * @param email user verified email.
		 * @return true if mail already exists, otherwise false.
		 */
		boolean existsByEmail(String email);
}