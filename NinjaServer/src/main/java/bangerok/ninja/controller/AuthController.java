package bangerok.ninja.controller;

import bangerok.ninja.domain.User;
import bangerok.ninja.dto.AuthProvider;
import bangerok.ninja.exception.BadRequestException;
import bangerok.ninja.payload.ApiResponse;
import bangerok.ninja.payload.AuthResponse;
import bangerok.ninja.payload.LoginRequest;
import bangerok.ninja.payload.SignUpRequest;
import bangerok.ninja.repo.RoleRepository;
import bangerok.ninja.repo.UserRepository;
import bangerok.ninja.security.CurrentUser;
import bangerok.ninja.security.TokenProvider;
import bangerok.ninja.security.UserPrincipal;
import java.net.URI;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
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
		private final UserRepository userRepository;
		private final RoleRepository roleRepository;

		public AuthController(
				AuthenticationManager authenticationManager,
				PasswordEncoder passwordEncoder,
				TokenProvider tokenProvider, UserRepository userRepository, RoleRepository roleRepository) {
				this.authenticationManager = authenticationManager;
				this.passwordEncoder = passwordEncoder;
				this.tokenProvider = tokenProvider;
				this.userRepository = userRepository;
				this.roleRepository = roleRepository;
		}

		@PostAuthorize("hasPermission(returnObject, 'READ')")
		@GetMapping("/user")
		public User getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {
				if (Objects.isNull(userPrincipal)) {
						return null;
				}

				return userRepository.findById(userPrincipal.getId()).orElse(null);

		}

		@PostMapping("/login")
		public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

				Authentication authentication = authenticationManager.authenticate(
						new UsernamePasswordAuthenticationToken(
								loginRequest.getEmail(),
								loginRequest.getPassword()
						)
				);

				Optional<User> optionalUser = userRepository.findByEmail(loginRequest.getEmail());
				if (optionalUser.isPresent()) {
						User user = optionalUser.get();
						user.setLastVisit(LocalDateTime.now());
						userRepository.save(user);
				}

				SecurityContextHolder.getContext().setAuthentication(authentication);

				String token = tokenProvider.createToken(authentication);
				return ResponseEntity.ok(new AuthResponse(token));
		}

		@PostMapping("/signup")
		public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
				if (userRepository.existsByEmail(signUpRequest.getEmail())) {
						throw new BadRequestException("Email address already in use.");
				}

				User user = new User();
				user.setUsername(signUpRequest.getUsername());
				user.setEmail(signUpRequest.getEmail());
				user.setAuthProvider(AuthProvider.local);
				user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));

				roleRepository.findByName("ROLE_USER").ifPresent(role -> user.getRoles().add(role));

				User savedUser = userRepository.save(user);

				URI location = URI.create("http://localhost:8000");
				return ResponseEntity.created(location)
						.body(new ApiResponse(true, "User " + savedUser.getUsername() + " registered successfully"));
		}
}
