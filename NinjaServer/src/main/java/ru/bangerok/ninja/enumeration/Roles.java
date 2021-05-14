package ru.bangerok.ninja.enumeration;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ru.bangerok.ninja.controller.payload.request.RegisterRequest;
import ru.bangerok.ninja.service.impl.UserServiceImpl;

/**
 * Enumeration class for user roles names.
 * <p>
 * For a complete picture, see: {@link UserServiceImpl#registerNewUserAccount(RegisterRequest)}.
 *
 * @author v.kuznetsov
 * @since 0.5.7
 */
@Getter
@RequiredArgsConstructor
public enum Roles {
		/**
		 * The role is the user. Given upon registration.
		 */
		USER("ROLE_USER"),

		/**
		 * Role - administrator. Not issued yet. TODO: Add, in the future, the award of this role.
		 */
		ADMIN("ROLE_ADMIN");

		/**
		 * The role name that is stored in the database.
		 */
		private final String name;
}
