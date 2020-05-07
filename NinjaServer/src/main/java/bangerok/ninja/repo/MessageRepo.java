package bangerok.ninja.repo;

import bangerok.ninja.domain.Message;
import bangerok.ninja.domain.User;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepo extends JpaRepository<Message, Long> {

		@EntityGraph(attributePaths = {"comments"})
		Page<Message> findByAuthorIn(List<User> users, Pageable pageable);
}
