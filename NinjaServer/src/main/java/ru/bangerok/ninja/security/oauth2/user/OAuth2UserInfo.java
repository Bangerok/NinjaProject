package ru.bangerok.ninja.security.oauth2.user;

import java.util.Map;

/**
 * An abstract class with common methods and fields for all class objects (if there are several)
 * user data obtained from external authorization providers.
 * <p>
 * Used for example here: {@link GoogleOAuth2UserInfo}.
 *
 * @author v.kuznetsov
 * @since 0.3.0
 */
public abstract class OAuth2UserInfo {

		/**
		 * Protected field that stores user attributes from an external authorization provider.
		 */
		protected final Map<String, Object> attributes;

		public OAuth2UserInfo(Map<String, Object> attributes) {
				this.attributes = attributes;
		}

		/**
		 * Abstract method for getting user id in external authorization provider.
		 *
		 * @return user id in the external authorization provider.
		 */
		public abstract String getId();

		/**
		 * Abstract method for getting username in external authorization provider.
		 *
		 * @return username in the external authorization provider.
		 */
		public abstract String getName();

		/**
		 * Abstract method for getting user email in external authorization provider.
		 *
		 * @return the user's email in the external authorization provider.
		 */
		public abstract String getEmail();

		/**
		 * An abstract method for getting a user's avatar in an external authorization provider.
		 *
		 * @return link to the user's avatar in the external authorization provider.
		 */
		public abstract String getImageUrl();
}