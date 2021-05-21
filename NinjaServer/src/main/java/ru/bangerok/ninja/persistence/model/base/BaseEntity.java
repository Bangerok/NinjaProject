package ru.bangerok.ninja.persistence.model.base;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

/**
 * Base class for all entities.
 *
 * @author v.kuznetsov
 * @since 0.3.3
 */
@Getter
@Setter
@EqualsAndHashCode(of = {"id"})
@ToString(of = {"id", "status"})
@MappedSuperclass
public class BaseEntity {

		/**
		 * Private field that stores information about the id of the record in the database.
		 * Automatically generated when saved to the database. No need to pre-assign.
		 */
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name = "base_id", unique = true, nullable = false)
		private Long id;

		/**
		 * Private field that stores information about the date the record was saved in the database. No
		 * need to pre-assign.
		 */
		@CreatedDate
		@Column(name = "base_created_date", nullable = false)
		private Date created;

		/**
		 * Private field that stores information about the date the record was updated in the database.
		 * No need to pre-assign.
		 */
		@LastModifiedDate
		@Column(name = "base_updated_date")
		private Date updated;

		/**
		 * Private field that stores information about the status of a record in the database. It is not
		 * necessary to pre-appoint.
		 */
		@Enumerated(EnumType.STRING)
		@Column(name = "base_status", nullable = false)
		private BaseStatus status;

		/**
		 * Private method that is executed before saving the record to the database.
		 */
		@PrePersist
		private void prePersistFunction() {
				created = new Date();
				status = BaseStatus.ACTIVE;
		}

		/**
		 * Private method that is executed before updating a record in the database.
		 */
		@PreUpdate
		private void preUpdateFunction() {
				updated = new Date();
		}
}