package ru.bangerok.ninja.persistence.dao.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.bangerok.ninja.persistence.dao.RoleRepository;
import ru.bangerok.ninja.persistence.dao.UserRepository;
import ru.bangerok.ninja.persistence.dao.VerificationTokenRepository;
import ru.bangerok.ninja.service.impl.UserServiceImpl;

/**
 * Service class for getting the required repositories.
 * <p>
 * Used for example here: {@link UserServiceImpl}.
 *
 * @author v.kuznetsov
 * @since 0.4.3
 */
@Getter
@RequiredArgsConstructor
@Service
public class RepositoryLocator {

		private final UserRepository userRepository;
		private final RoleRepository roleRepository;
		private final VerificationTokenRepository tokenRepository;
}