package ru.bangerok.enterprise.ninja.repository;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.bangerok.enterprise.ninja.persistence.dao.UserRepository;

/**
 * Class for testing the repository working with the User entity.
 *
 * @author Vladislav [Bangerok] Kuznetsov.
 * @since 0.3.9
 */
@SpringBootTest
class UserRepositoryTest extends AbstractRepositoryTest {

  @Autowired
  private UserRepository userRepository;

  /**
   * Method for testing the search for a user in the database by id.
   */
  @Test
  void checkingUserFindById() {
    var newUser = userRepository.save(factory.getNewUser());

    var userFoundById = userRepository.findById(newUser.getId())
        .orElse(null);
    assertNotNull(userFoundById);
    userRepository.delete(userFoundById);
  }

  /**
   * Method for testing a user search in a database by email.
   */
  @Test
  void checkingUserFindByEmail() {
    var newUser = userRepository.save(factory.getNewUser());

    var userFoundByEmail = userRepository
        .findByEmail(newUser.getEmail()).orElse(null);
    assertNotNull(userFoundByEmail);
    userRepository.delete(userFoundByEmail);
  }

  /**
   * Method for testing user search in the database by providerId.
   */
  @Test
  void checkingUserFindByProviderId() {
    var newUser = userRepository.save(factory.getNewUser());

    var userFoundByProviderId = userRepository
        .findByProviderId(newUser.getProviderId()).orElse(null);
    assertNotNull(userFoundByProviderId);
    userRepository.delete(userFoundByProviderId);
  }

  /**
   * Method for testing email availability check in database.
   */
  @Test
  void checkingUserExistByEmail() {
    var newUser = userRepository.save(factory.getNewUser());

    var existsByEmail = userRepository
        .existsByEmail(newUser.getEmail());
    Assertions.assertTrue(existsByEmail);
    userRepository.delete(newUser);
  }
}