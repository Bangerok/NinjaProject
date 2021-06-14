package ru.bangerok.ninja.persistence.dao;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.bangerok.ninja.persistence.model.user.VerificationToken;
import ru.bangerok.ninja.service.impl.UserServiceImpl;

/**
 * <p> Repository java class for communicating with the database and working with the entity of the
 * verification token. </p>
 * Used for example here: {@link UserServiceImpl#getVerificationToken(String)}.
 *
 * @author v.kuznetsov
 * @since 0.4.3
 */
@Repository
public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {

  /**
   * Method for finding a verification token by its token value in the database.
   *
   * @param value verification token value.
   * @return optional with found token.
   */
  Optional<VerificationToken> findByValue(String value);
}