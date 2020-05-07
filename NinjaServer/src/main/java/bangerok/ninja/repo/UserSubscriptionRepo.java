package bangerok.ninja.repo;

import bangerok.ninja.domain.User;
import bangerok.ninja.domain.UserSubscription;
import bangerok.ninja.domain.UserSubscriptionId;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserSubscriptionRepo extends JpaRepository<UserSubscription, UserSubscriptionId> {

		List<UserSubscription> findBySubscriber(User user);

		List<UserSubscription> findByChannel(User channel);

		UserSubscription findByChannelAndSubscriber(User channel, User subscriber);
}
