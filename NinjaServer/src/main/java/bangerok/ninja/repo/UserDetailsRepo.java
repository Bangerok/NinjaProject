package bangerok.ninja.repo;

import bangerok.ninja.domain.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDetailsRepo extends JpaRepository<User, String> {

		@EntityGraph(attributePaths = {"subscriptions", "subscribers"})
		Optional<User> findById(long id);

		@EntityGraph(attributePaths = {"subscriptions", "subscribers"})
		Optional<User> findByEmail(String email);
}
