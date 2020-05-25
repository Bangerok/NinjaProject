package bangerok.ninja.domain;

import bangerok.ninja.domain.base.BaseEntity;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@Entity
@Table(name = "roles")
@EqualsAndHashCode(callSuper = true)
@ToString(of = { "name" }, callSuper = true)
public class Role extends BaseEntity {

		@Column(name = "name")
		private String name;

		@ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
		private Set<User> users;
}
