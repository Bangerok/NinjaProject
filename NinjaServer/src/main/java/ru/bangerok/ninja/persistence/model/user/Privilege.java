package ru.bangerok.ninja.persistence.model.user;

import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ru.bangerok.ninja.persistence.model.base.BaseEntity;

/**
 * Entity for table with role privileges. Used for dotted server permissions. For example, for a
 * separate entity.
 *
 * @author v.kuznetsov
 * @since 0.3.4
 */
@Getter
@Setter
@EqualsAndHashCode(of = {"name"}, callSuper = true)
@ToString(of = {"name"}, callSuper = true)
@Entity
@Table(name = "privileges")
public class Privilege extends BaseEntity {

		/**
		 * Private field that stores the name of the privilege in the database.
		 */
		@Column(name = "name", unique = true, nullable = false)
		private String name;

		/**
		 * Private field that stores a list of roles that have access to this privilege.
		 */
		@ManyToMany(mappedBy = "privileges")
		private Collection<Role> roles;
}