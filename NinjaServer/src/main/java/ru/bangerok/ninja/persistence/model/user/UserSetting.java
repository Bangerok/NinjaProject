package ru.bangerok.ninja.persistence.model.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ru.bangerok.ninja.persistence.model.base.BaseEntity;
import ru.bangerok.ninja.persistence.model.user.User;

/**
 * Entity for the table with user settings. Used to store the data of authorized users and their
 * roles.
 *
 * @author v.kuznetsov
 * @since 0.1.0
 */
@Getter
@Setter
@EqualsAndHashCode(of = {"name"}, callSuper = true)
@ToString(of = {"name", "value"}, callSuper = true)
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
		@JoinColumn(name="base_id", insertable = false, updatable = false)
		private User user;
}
