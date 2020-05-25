package bangerok.ninja.domain;

import bangerok.ninja.domain.base.BaseEntity;
import bangerok.ninja.dto.AuthProvider;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.LocalDateTime;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@Entity
@Table(name = "users")
@EqualsAndHashCode(of = { "username" }, callSuper = true)
@ToString(of = { "fullname" }, callSuper = true)
public class User extends BaseEntity {

		@Column(name = "full_name")
		private String fullname;

		@Column(name = "username")
		private String username;

		@Column(name = "avatar")
		private String avatar;

		@Email
		@Column(name = "email")
		private String email;

		@Column(name = "email_verified")
		private Boolean emailVerified = false;

		@Column(name = "password")
		private String password;

		@Enumerated(EnumType.STRING)
		@Column(name = "auth_provider")
		private AuthProvider authProvider;

		@Column(name = "auth_provider_id")
		private String providerId;

		@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
		@Column(name = "last_visit_date")
		private LocalDateTime lastVisit;

		@JsonIgnore
		@ManyToMany
		@JoinTable(name = "user_roles",
				joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "base_id")},
				inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "base_id")})
		private Set<Role> roles;
}
