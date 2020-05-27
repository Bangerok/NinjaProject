package ru.bangerok.ninja.repo;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.bangerok.ninja.controller.AuthController;
import ru.bangerok.ninja.domain.User;

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

		@Override
		Optional<User> findById(Long id);

		Optional<User> findByEmail(String email);

		Optional<User> findByProviderId(String id);

		Boolean existsByEmail(String email);
}
