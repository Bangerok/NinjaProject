package bangerok.ninja.domain;

import bangerok.ninja.domain.base.BaseEntity;
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

@Data
@Entity
@Table(name = "roles")
@EqualsAndHashCode(of = { "name" }, callSuper = true)
@ToString(of = { "name" }, callSuper = true)
public class Role extends BaseEntity {

		@Column(name = "name")
		private String name;

		@ManyToMany(mappedBy = "roles")
		private Set<User> users;

		@JsonIgnore
		@ManyToMany
		@JoinTable(name = "role_privileges",
				joinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "base_id")},
				inverseJoinColumns = {@JoinColumn(name = "privilege_id", referencedColumnName = "base_id")})
		private Set<Role> privileges;
}
