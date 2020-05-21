package bangerok.ninja.controller;

import bangerok.ninja.domain.User;
import bangerok.ninja.domain.enumeration.AuthProvider;
import bangerok.ninja.exception.BadRequestException;
import bangerok.ninja.exception.ResourceNotFoundException;
import bangerok.ninja.payload.ApiResponse;
import bangerok.ninja.payload.AuthResponse;
import bangerok.ninja.payload.LoginRequest;
import bangerok.ninja.payload.SignUpRequest;
import bangerok.ninja.repo.UserDetailsRepo;
import bangerok.ninja.security.CurrentUser;
import bangerok.ninja.security.TokenProvider;
import bangerok.ninja.security.UserPrincipal;
import java.net.URI;
import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthController {

		private final AuthenticationManager authenticationManager;
		private final PasswordEncoder passwordEncoder;
		private final TokenProvider tokenProvider;
		private final UserDetailsRepo userDetailsRepo;

		public AuthController(
				AuthenticationManager authenticationManager,
				PasswordEncoder passwordEncoder,
				TokenProvider tokenProvider, UserDetailsRepo userDetailsRepo) {
				this.authenticationManager = authenticationManager;
				this.passwordEncoder = passwordEncoder;
				this.tokenProvider = tokenProvider;
				this.userDetailsRepo = userDetailsRepo;
		}

		@GetMapping("/user")
		public User getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {
				return userDetailsRepo.findById(userPrincipal.getId())
						.orElseThrow(() -> new ResourceNotFoundException("User", "id", userPrincipal.getId()));
		}

		@PostMapping("/login")
		public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

				Authentication authentication = authenticationManager.authenticate(
						new UsernamePasswordAuthenticationToken(
								loginRequest.getEmail(),
								loginRequest.getPassword()
						)
				);

				SecurityContextHolder.getContext().setAuthentication(authentication);

				String token = tokenProvider.createToken(authentication);
				return ResponseEntity.ok(new AuthResponse(token));
		}

		@PostMapping("/signup")
		public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
				if (userDetailsRepo.existsByEmail(signUpRequest.getEmail())) {
						throw new BadRequestException("Email address already in use.");
				}

				// Creating user's account
				User user = new User();
				user.setName(signUpRequest.getName());
				user.setEmail(signUpRequest.getEmail());
				user.setPassword(signUpRequest.getPassword());
				user.setProvider(AuthProvider.local);

				user.setPassword(passwordEncoder.encode(user.getPassword()));

				User result = userDetailsRepo.save(user);
				URI location = URI.create("http://localhost:8000");

				return ResponseEntity.created(location)
						.body(new ApiResponse(true, "User registered successfully"));
		}
}
