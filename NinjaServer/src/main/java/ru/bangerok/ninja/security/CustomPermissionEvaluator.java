package ru.bangerok.ninja.security;

import java.io.Serializable;
import org.hibernate.Hibernate;
import org.hibernate.proxy.HibernateProxy;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import ru.bangerok.ninja.config.MethodSecurityConfig;

/**
 * A class containing methods for checking whether a user has permissions on an entity.
 * <p>
 * Connects here: {@link MethodSecurityConfig}.
 *
 * @author v.kuznetsov
 * @since 0.3.6
 */
public class CustomPermissionEvaluator implements PermissionEvaluator {

		/**
		 * Method for checking if a user has access to a specific object.
		 *
		 * @param auth               authentication data.
		 * @param targetDomainObject the object to which access is requested.
		 * @param permission         permit name.
		 * @return true if access rights are present, otherwise false.
		 */
		@Override
		public boolean hasPermission(Authentication auth, Object targetDomainObject,
				Object permission) {
				if ((auth == null) || (targetDomainObject == null) || !(permission instanceof String)) {
						return false;
				}

				if (targetDomainObject instanceof HibernateProxy) {
						targetDomainObject = Hibernate.unproxy(targetDomainObject);
				}

				final String targetType = targetDomainObject.getClass().getSimpleName().toUpperCase();
				return hasPrivilege(auth, targetType, permission.toString().toUpperCase());
		}

		/**
		 * Method for checking if a user has access to a specific object.
		 *
		 * @param auth       authentication data.
		 * @param targetId   access target id.
		 * @param targetType access target type.
		 * @param permission permit name.
		 * @return true if access rights are present, otherwise false.
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
		 * Method for checking if a user has access to a specific object.
		 *
		 * @param auth       authentication data.
		 * @param targetType access target type.
		 * @param permission permit name.
		 * @return true if access rights are present, otherwise false
		 */
		private boolean hasPrivilege(Authentication auth, String targetType, String permission) {
				return auth.getAuthorities().parallelStream().anyMatch(
						a -> a.getAuthority().startsWith(targetType) && a.getAuthority().contains(permission));
		}
}