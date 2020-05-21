package bangerok.ninja.repo;

import bangerok.ninja.domain.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDetailsRepo extends JpaRepository<User, Long> {

		@EntityGraph(attributePaths = {"subscriptions", "subscribers"})
		@Override
		Optional<User> findById(Long id);

		@EntityGraph(attributePaths = {"subscriptions", "subscribers"})
		Optional<User> findByEmail(String email);

		@EntityGraph(attributePaths = {"subscriptions", "subscribers"})
		Optional<User> findByProviderId(String id);

		Boolean existsByEmail(String email);
}
