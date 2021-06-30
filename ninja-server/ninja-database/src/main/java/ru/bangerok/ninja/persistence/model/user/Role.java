package ru.bangerok.ninja.persistence.model.user;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ru.bangerok.ninja.enumeration.Roles;
import ru.bangerok.ninja.persistence.model.base.BaseEntity;

/**
 * Entity for the table with user roles. Used for global server rights. For example, for a separate
 * controller or request. Also, each role has its own list of privileges.
 *
 * @author v.kuznetsov
 * @since 0.3.3
 */
@Getter
@Setter
@EqualsAndHashCode(exclude = {"privileges"}, doNotUseGetters = true, callSuper = true)
@ToString(exclude = {"privileges"}, doNotUseGetters = true, callSuper = true)
@Entity
@Table(name = "roles")
public class Role extends BaseEntity {

  /**
   * Private field that stores the name of the role in the database.
   */
  @Column(name = "value", unique = true, nullable = false)
  @Enumerated(EnumType.STRING)
  private Roles value;
  /**
   * Private field that stores a list of users who have access to this role.
   */
  @ManyToMany(mappedBy = "roles")
  private List<User> users;

  /**
   * Private field that stores the list of privileges available for this role in the database.
   */
  @ManyToMany
  @JoinTable(name = "role_privileges",
      joinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "base_id")},
      inverseJoinColumns = {@JoinColumn(name = "privilege_id", referencedColumnName = "base_id")})
  private List<Privilege> privileges;
}