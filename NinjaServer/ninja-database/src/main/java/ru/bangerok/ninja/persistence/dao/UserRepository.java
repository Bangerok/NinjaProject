package ru.bangerok.ninja.persistence.dao;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.bangerok.ninja.persistence.model.user.User;

/**
 * <p> Repository java class for communicating with the database and working with the user
 * entity. </p>
 *
 * @author v.kuznetsov
 * @since 0.1.0
 */
@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Long> {

  /**
   * Method for finding a user by his id in the database.
   *
   * @param id user ID.
   * @return optional with found user.
   */
  @SuppressWarnings("NullableProblems")
  @Override
  Optional<User> findById(Long id);

  /**
   * Method for finding a user by his email in the database.
   *
   * @param email user's email.
   * @return optional with found user.
   */
  Optional<User> findByEmail(String email);

  /**
   * Method for finding a user by his id from an external provider in the database.
   *
   * @param providerId user id on the external authorization provider.
   * @return optional with found user.
   */
  Optional<User> findByProviderId(String providerId);

  /**
   * Method for checking for the existence of email in the database.
   *
   * @param email user verified email.
   * @return true if mail already exists, otherwise false.
   */
  boolean existsByEmail(String email);
}