package ru.bangerok.ninja.service.impl;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Objects;
import java.util.Optional;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bangerok.ninja.controller.exception.UserAlreadyExistException;
import ru.bangerok.ninja.controller.payload.request.LoginRequest;
import ru.bangerok.ninja.controller.payload.request.RegisterRequest;
import ru.bangerok.ninja.enumeration.AuthProvider;
import ru.bangerok.ninja.persistence.dao.RoleRepository;
import ru.bangerok.ninja.persistence.dao.UserRepository;
import ru.bangerok.ninja.persistence.model.Role;
import ru.bangerok.ninja.persistence.model.User;
import ru.bangerok.ninja.security.TokenProvider;
import ru.bangerok.ninja.security.UserPrincipal;
import ru.bangerok.ninja.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {

		private final UserRepository userRepository;
		private final PasswordEncoder passwordEncoder;
		private final RoleRepository roleRepository;
		private final AuthenticationManager authenticationManager;
		private final TokenProvider tokenProvider;

		public UserServiceImpl(UserRepository userRepository,
				PasswordEncoder passwordEncoder,
				RoleRepository roleRepository,
				AuthenticationManager authenticationManager,
				TokenProvider tokenProvider) {
				this.userRepository = userRepository;
				this.passwordEncoder = passwordEncoder;
				this.roleRepository = roleRepository;
				this.authenticationManager = authenticationManager;
				this.tokenProvider = tokenProvider;
		}

		@Override
		public User registerNewUserAccount(RegisterRequest registerData)
				throws UserAlreadyExistException {
				if (userRepository.existsByEmail(registerData.getEmail())) {
						throw new UserAlreadyExistException("errors.exists.email");
				}

				User user = new User();
				user.setUsername(registerData.getUsername());
				user.setEmail(registerData.getEmail());
				user.setAuthProvider(AuthProvider.local);
				user.setPassword(passwordEncoder.encode(registerData.getPassword()));

				Role userRole = roleRepository.findByName("ROLE_USER").orElse(null);
				if (Objects.nonNull(userRole)) {
						user.setRoles(Collections.singleton(userRole));
				}

				return userRepository.save(user);
		}

		@Override
		public String creatingTokenForAuthenticateUser(LoginRequest loginData) {
				Authentication authentication = authenticationManager.authenticate(
						new UsernamePasswordAuthenticationToken(
								loginData.getEmail(),
								loginData.getPassword()
						)
				);

				Optional<User> optionalUser = userRepository.findByEmail(loginData.getEmail());
				if (optionalUser.isPresent()) {
						User user = optionalUser.get();
						user.setLastVisit(LocalDateTime.now());
						userRepository.save(user);
				}

				SecurityContextHolder.getContext().setAuthentication(authentication);

				return tokenProvider.createToken(authentication);
		}

		@Override
		public User getCurrentUser(UserPrincipal currentUser) {
				if (Objects.isNull(currentUser)) {
						return null;
				}

				return userRepository.findById(currentUser.getId()).orElse(null);
		}
}
