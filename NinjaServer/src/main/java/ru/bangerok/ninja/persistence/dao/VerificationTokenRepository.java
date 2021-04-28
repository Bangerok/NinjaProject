package ru.bangerok.ninja.persistence.dao;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.bangerok.ninja.persistence.model.user.VerificationToken;
import ru.bangerok.ninja.service.impl.UserServiceImpl;

/**
 * Repository java класс для связи с базой данных и работой с сущностью токена верификации.
 * <p>
 * Используется, например, здесь: {@link UserServiceImpl#getVerificationToken(String)}.
 *
 * @author v.kuznetsov
 * @version 1.0
 */
@Repository
public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {

		/**
		 * Метод для поиска токена верификации по его значению токена в базе данных.
		 *
		 * @param token значение токена верификации.
		 * @return Optional с найденным токеном.
		 */
		Optional<VerificationToken> findByToken(String token);
}