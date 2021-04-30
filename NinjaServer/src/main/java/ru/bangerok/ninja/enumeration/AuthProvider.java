package ru.bangerok.ninja.enumeration;

import ru.bangerok.ninja.persistence.model.user.User;

/**
 * Enumeration class for user authorization providers.
 * <p>
 * For a complete picture, see: {@link User#getAuthProvider}.
 *
 * @author v.kuznetsov
 * @since 0.3.13
 */
public enum AuthProvider {
		/**
		 * Local provider type, which is set during standard registration.
		 */
		local,

		/**
		 * The type of external provider that is set during oauth2 authorization via Google.
		 */
		google
}