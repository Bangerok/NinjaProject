package ru.bangerok.ninja.persistence.dao.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;
import ru.bangerok.ninja.persistence.dao.RoleRepository;
import ru.bangerok.ninja.persistence.dao.UserRepository;
import ru.bangerok.ninja.persistence.dao.VerificationTokenRepository;
import ru.bangerok.ninja.service.impl.UserServiceImpl;

/**
 * Сервисный класс для получения нужных репозиториев.
 * <p>
 * Используется, например, здесь: {@link UserServiceImpl}
 *
 * @author v.kuznetsov
 * @version 1.0
 */
@Service
@Data
@AllArgsConstructor
public class RepositoryLocator {

		private final UserRepository userRepository;
		private final RoleRepository roleRepository;
		private final VerificationTokenRepository tokenRepository;
}