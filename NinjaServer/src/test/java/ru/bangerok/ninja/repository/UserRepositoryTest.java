package ru.bangerok.ninja.repository;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.bangerok.ninja.persistence.dao.base.RepositoryLocator;
import ru.bangerok.ninja.persistence.model.user.User;

/**
 * Класс для тестирования репозитория, работающего с сущностью User.
 *
 * @author v.kuznetsov
 * @version 1.0
 */
@SpringBootTest
public class UserRepositoryTest extends AbstractRepositoryTest {

		@Autowired
		private RepositoryLocator repositoryLocator;

		/**
		 * Метод для тестирования поиска пользователя в базе данных по id.
		 */
		@Test
		void checkingUserFindById() {
				User newUser = repositoryLocator.getUserRepository().save(factory.getNewUser());

				User userFoundById = repositoryLocator.getUserRepository().findById(newUser.getId())
						.orElse(null);
				assertNotNull(userFoundById);
				repositoryLocator.getUserRepository().delete(userFoundById);
		}

		/**
		 * Метод для тестирования поиска пользователя в базе данных по электронной почте.
		 */
		@Test
		void checkingUserFindByEmail() {
				User newUser = repositoryLocator.getUserRepository().save(factory.getNewUser());

				User userFoundByEmail = repositoryLocator.getUserRepository()
						.findByEmail(newUser.getEmail()).orElse(null);
				assertNotNull(userFoundByEmail);
				repositoryLocator.getUserRepository().delete(userFoundByEmail);
		}

		/**
		 * Метод для тестирования поиска пользователя в базе данных по providerId.
		 */
		@Test
		void checkingUserFindByProviderId() {
				User newUser = repositoryLocator.getUserRepository().save(factory.getNewUser());

				User userFoundByProviderId = repositoryLocator.getUserRepository()
						.findByProviderId(newUser.getProviderId()).orElse(null);
				assertNotNull(userFoundByProviderId);
				repositoryLocator.getUserRepository().delete(userFoundByProviderId);
		}

		/**
		 * Метод для тестирования проверки доступности электронной почты в базе данных.
		 */
		@Test
		void checkingUserExistByEmail() {
				User newUser = repositoryLocator.getUserRepository().save(factory.getNewUser());

				boolean existsByEmail = repositoryLocator.getUserRepository()
						.existsByEmail(newUser.getEmail());
				assertTrue(existsByEmail);
				repositoryLocator.getUserRepository().delete(newUser);
		}
}