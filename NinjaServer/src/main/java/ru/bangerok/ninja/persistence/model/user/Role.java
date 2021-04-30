package ru.bangerok.ninja.persistence.model.user;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import net.minidev.json.annotate.JsonIgnore;
import ru.bangerok.ninja.persistence.model.base.BaseEntity;

/**
 * Essence of roles. Used for global server rights. For example, for a separate controller or
 * request. Also, each role has its own list of privileges.
 *
 * @author v.kuznetsov
 * @since 0.3.3
 */
@Data
@Entity
@Table(name = "roles")
@EqualsAndHashCode(of = {"name"}, callSuper = true)
@ToString(of = {"name"}, callSuper = true)
public class Role extends BaseEntity {

		/**
		 * Private field that stores the name of the role in the database.
		 */
		@Column(name = "name", unique = true)
		private String name;

		/**
		 * Private field that stores a list of users who have access to this role.
		 */
		@ManyToMany(mappedBy = "roles")
		private Set<User> users;

		/**
		 * Private field that stores the list of privileges available for this role in the database.
		 */
		@JsonIgnore
		@ManyToMany
		@JoinTable(name = "role_privileges",
				joinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "base_id")},
				inverseJoinColumns = {@JoinColumn(name = "privilege_id", referencedColumnName = "base_id")})
		private Set<Privilege> privileges;
}