package bangerok.ninja.service;

import bangerok.ninja.domain.User;
import bangerok.ninja.domain.UserSubscription;
import bangerok.ninja.repo.UserDetailsRepo;
import bangerok.ninja.repo.UserSubscriptionRepo;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {

		private final UserDetailsRepo userDetailsRepo;
		private final UserSubscriptionRepo userSubscriptionRepo;

		@Autowired
		public ProfileService(
				UserDetailsRepo userDetailsRepo,
				UserSubscriptionRepo userSubscriptionRepo
		) {
				this.userDetailsRepo = userDetailsRepo;
				this.userSubscriptionRepo = userSubscriptionRepo;
		}

		public User changeSubscription(User channel, User subscriber) {
				List<UserSubscription> subcriptions = channel.getSubscribers()
						.stream()
						.filter(subscription ->
								subscription.getSubscriber().equals(subscriber)
						)
						.collect(Collectors.toList());

				if (subcriptions.isEmpty()) {
						UserSubscription subscription = new UserSubscription(channel, subscriber);
						channel.getSubscribers().add(subscription);
				} else {
						channel.getSubscribers().removeAll(subcriptions);
				}

				return userDetailsRepo.save(channel);
		}

		public List<UserSubscription> getSubscribers(User channel) {
				return userSubscriptionRepo.findByChannel(channel);
		}

		public UserSubscription changeSubscriptionStatus(User channel, User subscriber) {
				UserSubscription subscription = userSubscriptionRepo
						.findByChannelAndSubscriber(channel, subscriber);
				subscription.setActive(!subscription.isActive());

				return userSubscriptionRepo.save(subscription);
		}
}
