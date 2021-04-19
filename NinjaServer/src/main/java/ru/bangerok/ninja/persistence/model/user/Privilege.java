package ru.bangerok.ninja.persistence.model.user;

import ru.bangerok.ninja.persistence.model.base.BaseEntity;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * Сущность привилегий. Используется для точечных прав сервера. Например, на отдельную сущность.
 *
 * @author v.kuznetsov
 * @version 1.0
 */
@Data
@Entity
@Table(name = "privileges")
@EqualsAndHashCode(of = {"name"}, callSuper = true)
@ToString(of = {"name"}, callSuper = true)
public class Privilege extends BaseEntity {

		/**
		 * Private поле, в котором хранится наименование привилегии в базе данных.
		 */
		@Column(name = "name", unique = true)
		private String name;

		/**
		 * Private поле, в котором хранится список ролей, которым доступна данная привилегия.
		 */
		@ManyToMany(mappedBy = "privileges")
		private List<Role> roles;
}
