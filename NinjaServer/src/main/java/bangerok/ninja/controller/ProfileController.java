package bangerok.ninja.controller;

import bangerok.ninja.domain.User;
import bangerok.ninja.domain.UserSubscription;
import bangerok.ninja.domain.Views;
import bangerok.ninja.service.ProfileService;
import com.fasterxml.jackson.annotation.JsonView;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("profile")
public class ProfileController {

		private final ProfileService profileService;

		@Autowired
		public ProfileController(ProfileService profileService) {
				this.profileService = profileService;
		}

		@GetMapping("{id}")
		@JsonView(Views.FullProfile.class)
		public User get(@PathVariable("id") User user) {
				return user;
		}

		@PostMapping("change-subscription/{channelId}")
		@JsonView(Views.FullProfile.class)
		public User changeSubscription(
				@AuthenticationPrincipal User subscriber,
				@PathVariable("channelId") User channel
		) {
				if (subscriber.equals(channel)) {
						return channel;
				} else {
						return profileService.changeSubscription(channel, subscriber);
				}
		}

		@GetMapping("get-subscribers/{channelId}")
		@JsonView(Views.IdName.class)
		public List<UserSubscription> subscribers(
				@PathVariable("channelId") User channel
		) {
				return profileService.getSubscribers(channel);
		}

		@PostMapping("change-status/{subscriberId}")
		@JsonView(Views.IdName.class)
		public UserSubscription changeSubscriptionStatus(
				@AuthenticationPrincipal User channel,
				@PathVariable("subscriberId") User subscriber
		) {
				return profileService.changeSubscriptionStatus(channel, subscriber);
		}
}
