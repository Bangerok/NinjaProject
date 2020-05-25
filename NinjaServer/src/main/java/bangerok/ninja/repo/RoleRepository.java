package bangerok.ninja.repo;

import bangerok.ninja.domain.Role;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

		Optional<Role> findByName(String name);
}
