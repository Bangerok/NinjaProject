package bangerok.ninja.controller;

import bangerok.ninja.domain.User;
import bangerok.ninja.domain.Views;
import bangerok.ninja.repo.UserDetailsRepo;
import com.fasterxml.jackson.annotation.JsonView;
import java.time.LocalDateTime;
import java.util.Objects;
import org.jsoup.internal.StringUtil;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping("auth")
public class AuthController {

		private final String CLIENT_URL = "http://localhost:8000/";

		private final UserDetailsRepo userDetailsRepo;

		public AuthController(UserDetailsRepo userDetailsRepo) {
				this.userDetailsRepo = userDetailsRepo;
		}

		@GetMapping("loginSuccess")
		public RedirectView redirectIntoClient() {
				return configureRedirectView(CLIENT_URL);
		}

		@GetMapping("create-or-get-user")
		@JsonView(Views.IdName.class)
		public User user(@AuthenticationPrincipal OAuth2User principal) {
				if (Objects.isNull(principal)) {
						return null;
				}

				String id = principal.getAttribute("sub");

				if (StringUtil.isBlank(id)) {
						return null;
				}

				User user = userDetailsRepo.findById(id).orElseGet(() -> {
						User newUser = new User();

						newUser.setId(id);
						newUser.setName(principal.getAttribute("name"));
						newUser.setEmail(principal.getAttribute("email"));
						newUser.setGender(principal.getAttribute("gender"));
						newUser.setLocale(principal.getAttribute("locale"));
						newUser.setUserpic(principal.getAttribute("picture"));

						return newUser;
				});

				user.setLastVisit(LocalDateTime.now());

				return userDetailsRepo.save(user);
		}

		private RedirectView configureRedirectView(String redirectUrl) {
				RedirectView redirectView = new RedirectView();
				redirectView.setStatusCode(HttpStatus.FOUND);
				redirectView.setUrl(redirectUrl);

				return redirectView;
		}
}