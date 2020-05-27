package ru.bangerok.ninja.repo;

import ru.bangerok.ninja.domain.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

		@Override
		Optional<User> findById(Long id);

		Optional<User> findByEmail(String email);

		Optional<User> findByProviderId(String id);

		Boolean existsByEmail(String email);
}
