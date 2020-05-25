package bangerok.ninja.domain;

import bangerok.ninja.domain.base.BaseEntity;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@Entity
@Table(name = "privileges")
@EqualsAndHashCode(of = { "name" }, callSuper = true)
@ToString(of = { "name" }, callSuper = true)
public class Privilege extends BaseEntity {

		@Column(name = "name")
		private String name;

		@ManyToMany(mappedBy = "privileges")
		private List<Role> roles;
}
