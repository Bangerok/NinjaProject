package ru.bangerok.ninja.service.impl;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bangerok.ninja.controller.exception.user.UserAlreadyExistException;
import ru.bangerok.ninja.controller.exception.user.UserNotFoundException;
import ru.bangerok.ninja.controller.payload.request.LoginRequest;
import ru.bangerok.ninja.controller.payload.request.RegisterRequest;
import ru.bangerok.ninja.enumeration.AuthProvider;
import ru.bangerok.ninja.persistence.dao.base.RepositoryLocator;
import ru.bangerok.ninja.persistence.model.user.Role;
import ru.bangerok.ninja.persistence.model.user.User;
import ru.bangerok.ninja.persistence.model.user.VerificationToken;
import ru.bangerok.ninja.security.TokenProvider;
import ru.bangerok.ninja.security.UserPrincipal;
import ru.bangerok.ninja.service.MessageService;
import ru.bangerok.ninja.service.UserService;

@RequiredArgsConstructor
@Service
@Transactional
public class UserServiceImpl implements UserService {

		private final RepositoryLocator repositoryLocator;
		private final MessageService messageService;
		private final PasswordEncoder passwordEncoder;
		private final AuthenticationManager authenticationManager;
		private final TokenProvider tokenProvider;

		@Override
		public User registerNewUserAccount(RegisterRequest registerData)
				throws UserAlreadyExistException {
				if (repositoryLocator.getUserRepository().existsByEmail(registerData.getEmail())) {
						throw new UserAlreadyExistException(
								messageService.getMessageWithArgs(
										"user.error.exist.email",
										new Object[]{
												registerData.getEmail()
										}
								)
						);
				}

				User user = new User();
				user.setUsername(registerData.getUsername());
				user.setEmail(registerData.getEmail());
				user.setAuthProvider(AuthProvider.local);
				user.setPassword(passwordEncoder.encode(registerData.getPassword()));

				Role userRole = repositoryLocator.getRoleRepository().findByName("ROLE_USER").orElse(null);
				if (Objects.nonNull(userRole)) {
						user.setRoles(Collections.singleton(userRole));
				}

				return repositoryLocator.getUserRepository().save(user);
		}

		@Override
		public String creatingTokenForAuthUser(LoginRequest loginData) {
				Authentication authentication = authenticationManager.authenticate(
						new UsernamePasswordAuthenticationToken(
								loginData.getEmail(),
								loginData.getPassword()
						)
				);

				User user = repositoryLocator.getUserRepository()
						.findByEmail(loginData.getEmail()).orElseThrow(() -> new UserNotFoundException(
								messageService.getMessage(
										"user.error.not.found"
								)
						));

				user.setLastVisit(LocalDateTime.now());
				repositoryLocator.getUserRepository().save(user);
				SecurityContextHolder.getContext().setAuthentication(authentication);

				return tokenProvider.createToken(authentication);
		}

		@Override
		public User getCurrentUser(UserPrincipal currentUser) {
				long currentUserId = Optional.ofNullable(currentUser)
						.orElseThrow(() -> new UserNotFoundException(
								messageService.getMessage(
										"user.error.not.found.auth"
								)
						)).getId();

				return repositoryLocator.getUserRepository()
						.findById(currentUserId).orElseThrow(() -> new UserNotFoundException(
								messageService.getMessage(
										"user.error.not.found"
								)
						));
		}

		@Override
		public User getUser(String verificationToken) {
				Optional<VerificationToken> tokenOptional = repositoryLocator.getTokenRepository()
						.findByToken(verificationToken);

				return tokenOptional.map(VerificationToken::getUser).orElse(null);

		}

		@Override
		public VerificationToken createVerificationTokenForUser(User user) {
				VerificationToken myToken = new VerificationToken();
				myToken.setToken(UUID.randomUUID().toString());
				myToken.setUser(user);
				myToken.setExpiryDate(LocalDateTime.now().plusDays(1));

				return repositoryLocator.getTokenRepository().save(myToken);
		}

		@Override
		public VerificationToken generateNewVerificationToken(String existingVerificationToken) {
				Optional<VerificationToken> tokenOptional = repositoryLocator.getTokenRepository()
						.findByToken(existingVerificationToken);
				if (tokenOptional.isPresent()) {
						VerificationToken token = tokenOptional.get();
						token.setToken(UUID.randomUUID().toString());
						token.setExpiryDate(LocalDateTime.now().plusDays(1));
						return repositoryLocator.getTokenRepository().save(token);
				}

				return null;
		}

		@Override
		public VerificationToken getVerificationToken(String verificationToken) {
				return repositoryLocator.getTokenRepository().findByToken(verificationToken).orElse(null);
		}

		@Override
		public void saveRegisteredUser(User user) {
				repositoryLocator.getUserRepository().save(user);
		}
}