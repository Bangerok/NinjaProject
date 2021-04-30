package ru.bangerok.ninja.persistence.model.base;

/**
 * Enumeration class for project entity statuses.
 * <p>
 * For a complete picture, see: {@link BaseEntity#getStatus}.
 *
 * @author v.kuznetsov
 * @since 0.3.3
 */
public enum BaseStatus {
		/**
		 * Status indicating the activity of a record in the database.
		 */
		ACTIVE,

		/**
		 * Status indicating inactivity of a record in the database.
		 */
		DELETED
}