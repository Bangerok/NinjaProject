package ru.bangerok.ninja.service.impl;

import static ru.bangerok.ninja.enumeration.Roles.ROLE_USER;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bangerok.ninja.enumeration.AuthProvider;
import ru.bangerok.ninja.exception.resource.ResourceAlreadyExistException;
import ru.bangerok.ninja.exception.resource.ResourceNotFoundException;
import ru.bangerok.ninja.persistence.dao.base.RepositoryLocator;
import ru.bangerok.ninja.persistence.model.user.Role;
import ru.bangerok.ninja.persistence.model.user.User;
import ru.bangerok.ninja.persistence.model.user.VerificationToken;
import ru.bangerok.ninja.rest.payload.request.LoginRequest;
import ru.bangerok.ninja.rest.payload.request.RegisterRequest;
import ru.bangerok.ninja.security.TokenProvider;
import ru.bangerok.ninja.security.UserPrincipal;
import ru.bangerok.ninja.service.MessageService;
import ru.bangerok.ninja.service.UserService;

@RequiredArgsConstructor
@Slf4j
@Transactional
@Service
public class UserServiceImpl implements UserService {

		private final RepositoryLocator repositoryLocator;
		private final MessageService messageService;
		private final PasswordEncoder passwordEncoder;
		private final AuthenticationManager authenticationManager;
		private final TokenProvider tokenProvider;

		@Override
		public User getCurrentUser(UserPrincipal currentUser) {
				if (Objects.isNull(currentUser)) {
						return null;
				}

				return currentUser.getUser();
		}

		@Override
		public String creatingTokenForAuthUser(LoginRequest loginData) {
				Authentication authentication = authenticationManager.authenticate(
						new UsernamePasswordAuthenticationToken(loginData.email(), loginData.password())
				);

				User user = repositoryLocator.getUserRepository().findByEmail(loginData.email())
						.orElseThrow(() -> new ResourceNotFoundException(messageService.getMessageWithArgs(
								"user.error.not.found.by.email",
								new Object[]{loginData.email()}
						)));

				user.setLastVisit(LocalDateTime.now());
				repositoryLocator.getUserRepository().save(user);
				SecurityContextHolder.getContext().setAuthentication(authentication);
				return tokenProvider.createToken(authentication);
		}

		@Override
		public User registerNewUserAccount(RegisterRequest registerData) {
				if (repositoryLocator.getUserRepository().existsByEmail(registerData.email())) {
						throw new ResourceAlreadyExistException(messageService.getMessageWithArgs(
								"user.error.exist.email",
								new Object[]{registerData.email()}
						));
				}

				User user = new User();
				user.setFullname(registerData.name());
				user.setEmailVerified(false);
				user.setEmail(registerData.email());
				user.setAuthProvider(AuthProvider.local);
				user.setPassword(passwordEncoder.encode(registerData.password()));
				Role userRole = repositoryLocator.getRoleRepository().findByValue(ROLE_USER)
						.orElseThrow(() -> new ResourceNotFoundException(messageService.getMessageWithArgs(
								"role.error.not.found.by.name", new Object[]{ROLE_USER.getName()}
						)));
				user.setRoles(Stream.of(userRole).collect(Collectors.toCollection(ArrayList::new)));
				return repositoryLocator.getUserRepository().save(user);
		}

		@Override
		public VerificationToken getVerificationToken(String verificationToken) {
				return repositoryLocator.getTokenRepository().findByValue(verificationToken)
						.orElseThrow(() -> new ResourceNotFoundException(messageService.getMessageWithArgs(
								"token.error.not.found.by.token", new Object[]{verificationToken}
						)));
		}

		@Override
		public VerificationToken generateNewVerificationToken(String existingVerificationToken) {
				VerificationToken token = this.getVerificationToken(existingVerificationToken);
				token.setValue(UUID.randomUUID().toString());
				token.setExpiryDate(LocalDateTime.now().plusDays(1));
				return repositoryLocator.getTokenRepository().save(token);
		}

		@Override
		public VerificationToken createVerificationTokenForUser(User user) {
				VerificationToken myToken = new VerificationToken();
				myToken.setValue(UUID.randomUUID().toString());
				myToken.setUser(user);
				myToken.setExpiryDate(LocalDateTime.now().plusDays(1));
				return repositoryLocator.getTokenRepository().save(myToken);
		}

		@Override
		public void saveRegisteredUser(User user) {
				repositoryLocator.getUserRepository().save(user);
		}
}