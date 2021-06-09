package ru.bangerok.ninja.persistence.dao;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;
import ru.bangerok.ninja.persistence.model.user.VerificationToken;
import ru.bangerok.ninja.service.impl.UserServiceImpl;

/**
 * Repository java class for communicating with the database and working with the entity of the
 * verification token.
 * <p>
 * Used for example here: {@link UserServiceImpl#getVerificationToken(String)}.
 *
 * @author v.kuznetsov
 * @since 0.4.3
 */
@Repository
public interface VerificationTokenRepository extends ReactiveCrudRepository<VerificationToken, Long> {

    /**
     * Method for finding a verification token by its token value in the database.
     *
     * @param Value verification token value.
     * @return Mono with found token.
     */
    Mono<VerificationToken> findByValue(String Value);
}