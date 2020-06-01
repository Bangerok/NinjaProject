package ru.bangerok.ninja.security;

import java.io.Serializable;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import ru.bangerok.ninja.config.MethodSecurityConfig;

/**
 * Класс, содержащий меторды проверки наличия у пользователя разрешений на какую либо сущность.
 * <p>
 * Подключается здесь: {@link MethodSecurityConfig}.
 *
 * @author v.kuznetsov
 * @version 1.0
 */
public class CustomPermissionEvaluator implements PermissionEvaluator {

		/**
		 * Метод для проверки наличия доступа у пользователя к определенному объекту.
		 *
		 * @param auth               данные аутентификации.
		 * @param targetDomainObject объект, доступ к которому запрашивается.
		 * @param permission         наименование разрешения.
		 * @return true, если права доступа есть, иначе false.
		 */
		@Override
		public boolean hasPermission(Authentication auth, Object targetDomainObject,
				Object permission) {
				if ((auth == null) || (targetDomainObject == null) || !(permission instanceof String)) {
						return false;
				}
				final String targetType = targetDomainObject.getClass().getSimpleName().toUpperCase();
				return hasPrivilege(auth, targetType, permission.toString().toUpperCase());
		}

		/**
		 * Метод для проверки наличия доступа у пользователя к определенному объекту.
		 *
		 * @param auth       данные аутентификации.
		 * @param targetId   идентификатор целевого объекта доступа.
		 * @param targetType тип целевого объекта доступа.
		 * @param permission наименование разрешения.
		 * @return true, если права доступа есть, иначе false.
		 */
		@Override
		public boolean hasPermission(Authentication auth, Serializable targetId, String targetType,
				Object permission) {
				if ((auth == null) || (targetType == null) || !(permission instanceof String)) {
						return false;
				}
				return hasPrivilege(auth, targetType.toUpperCase(), permission.toString().toUpperCase());
		}

		/**
		 * Метод для проверки наличия доступа у пользователя к определенному объекту.
		 *
		 * @param auth       данные аутентификации.
		 * @param targetType тип целевого объекта доступа.
		 * @param permission наименование разрешения.
		 * @return true, если права на объект найдены, иначе false.
		 */
		private boolean hasPrivilege(Authentication auth, String targetType, String permission) {
				return auth.getAuthorities().parallelStream().anyMatch(
						a -> a.getAuthority().startsWith(targetType) && a.getAuthority().contains(permission));
		}
}
