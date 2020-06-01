package ru.bangerok.ninja.repository;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.bangerok.ninja.persistence.model.User;
import ru.bangerok.ninja.persistence.dao.UserRepository;

/**
 * Класс для тестирования репозитория, работающего с сущностью User.
 *
 * @author v.kuznetsov
 * @version 1.0
 */
@SpringBootTest
public class UserRepositoryTest extends AbstractRepositoryTest {

		@Autowired
		private UserRepository userRepository;

		/**
		 * Метод для тестирования поиска пользователя в базе данных по id.
		 */
		@Test
		void checkingUserFindById() {
				User newUser = userRepository.save(factory.getNewUser());

				User userFoundById = userRepository.findById(newUser.getId()).orElse(null);
				assertNotNull(userFoundById);
				userRepository.delete(userFoundById);
		}

		/**
		 * Метод для тестирования поиска пользователя в базе данных по электронной почте.
		 */
		@Test
		void checkingUserFindByEmail() {
				User newUser = userRepository.save(factory.getNewUser());

				User userFoundByEmail = userRepository.findByEmail(newUser.getEmail()).orElse(null);
				assertNotNull(userFoundByEmail);
				userRepository.delete(userFoundByEmail);
		}

		/**
		 * Метод для тестирования поиска пользователя в базе данных по providerId.
		 */
		@Test
		void checkingUserFindByProviderId() {
				User newUser = userRepository.save(factory.getNewUser());

				User userFoundByProviderId = userRepository.findByProviderId(newUser.getProviderId()).orElse(null);
				assertNotNull(userFoundByProviderId);
				userRepository.delete(userFoundByProviderId);
		}

		/**
		 * Метод для тестирования проверки доступности электронной почты в базе данных.
		 */
		@Test
		void checkingUserExistByEmail() {
				User newUser = userRepository.save(factory.getNewUser());

				boolean existsByEmail = userRepository.existsByEmail(newUser.getEmail());
				assertTrue(existsByEmail);
				userRepository.delete(newUser);
		}
}