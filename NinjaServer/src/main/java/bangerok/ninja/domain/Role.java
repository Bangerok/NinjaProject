package bangerok.ninja.domain;

import bangerok.ninja.domain.base.BaseEntity;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "roles")
@EqualsAndHashCode(callSuper = true)
public class Role extends BaseEntity {

		@Column(name = "name")
		private String name;

		@ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
		private List<User> users;
}
