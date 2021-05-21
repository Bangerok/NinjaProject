package ru.bangerok.ninja.persistence.model.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ru.bangerok.ninja.persistence.model.base.BaseEntity;

/**
 * Entity for the table with user settings. It is used to store the settings for the application of
 * authorized users.
 *
 * @author v.kuznetsov
 * @since 0.5.8
 */
@Getter
@Setter
@EqualsAndHashCode(of = {"name"}, callSuper = true)
@ToString(of = {"name", "value", "user"}, callSuper = true)
@Entity
@Table(name = "user_settings")
public class UserSetting extends BaseEntity {

		/**
		 * A private field that stores the name of setting.
		 */
		@Column(name = "name", nullable = false)
		private String name;

		/**
		 * A private field that stores the value of setting.
		 */
		@Column(name = "value", nullable = false)
		private String value;

		/**
		 * A private field where the user of the saved setting is stored.
		 */
		@ManyToOne
		@JoinColumn(name = "user_id", nullable = false)
		private User user;
}
