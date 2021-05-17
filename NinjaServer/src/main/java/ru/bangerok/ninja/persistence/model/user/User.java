package ru.bangerok.ninja.persistence.model.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ru.bangerok.ninja.enumeration.AuthProvider;
import ru.bangerok.ninja.persistence.model.base.BaseEntity;

/**
 * Entity for the table with users. Used to store the data of authorized users and their roles.
 *
 * @author v.kuznetsov
 * @since 0.1.0
 */
@Getter
@Setter
@EqualsAndHashCode(of = {"email"}, callSuper = true)
@ToString(of = {"fullname", "email", "emailVerified", "authProvider"}, callSuper = true)
@Entity
@Table(name = "users")
public class User extends BaseEntity {

		/**
		 * Private field that stores the user's full name.
		 */
		@Column(name = "full_name", nullable = false)
		private String fullname;

		/**
		 * Private field that stores the username.
		 */
		@Column(name = "username")
		private String username;

		/**
		 * Private field that stores a link to the user's avatar.
		 */
		@Column(name = "avatar")
		private String avatar;

		/**
		 * Private field that stores the user's email.
		 */
		@Email
		@Column(name = "email", unique = true, nullable = false)
		private String email;

		/**
		 * Private field that stores information about the user's email confirmation.
		 */
		@Column(name = "email_verified", nullable = false)
		private Boolean emailVerified;

		/**
		 * Private field that stores the type of the user's authorization provider.
		 */
		@Enumerated(EnumType.STRING)
		@Column(name = "auth_provider", nullable = false)
		private AuthProvider authProvider;

		/**
		 * Private field, which stores the user id, which is stored in the database of the authorization
		 * provider.
		 */
		@Column(name = "auth_provider_id")
		private String providerId;

		/**
		 * Private field that stores the date of the user's last visit.
		 */
		@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
		@Column(name = "last_visit_date")
		private LocalDateTime lastVisit;

		/**
		 * Private field that stores, if any, the user's encrypted password.
		 */
		@JsonIgnore
		@Column(name = "password")
		private String password;

		/**
		 * Private field that stores the email verification token.
		 */
		@JsonIgnore
		@OneToOne(mappedBy = "user")
		private VerificationToken verificationToken;

		/**
		 * Private field that stores the list of roles available to this user.
		 */
		@JsonIgnore
		@ManyToMany
		@JoinTable(name = "user_roles",
				joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "base_id")},
				inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "base_id")})
		private Collection<Role> roles;

		/**
		 * A private field that stores a list of user settings.
		 */
		@OneToMany(mappedBy="user")
		private Collection<UserSetting> settings;
}