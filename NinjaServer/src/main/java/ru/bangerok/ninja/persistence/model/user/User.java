package ru.bangerok.ninja.persistence.model.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import java.time.LocalDateTime;
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
import ru.bangerok.ninja.persistence.model.views.Views;

/**
 * Entity for the table with users. Used to store the data of authorized users and their roles.
 *
 * @author v.kuznetsov
 * @since 0.1.0
 */
@Getter
@Setter
@EqualsAndHashCode(
		exclude = {"verificationToken", "roles", "settings"}, doNotUseGetters = true, callSuper = true
)
@ToString(
		exclude = {"verificationToken", "roles", "settings"}, doNotUseGetters = true, callSuper = true
)
@JsonIgnoreProperties({"lastVisit", "verificationToken", "settings", "roles", "password"})
@Entity
@Table(name = "users")
public class User extends BaseEntity {

		/**
		 * Private field that stores the user's full name.
		 */
		@Column(name = "full_name", nullable = false)
		@JsonView(Views.UserShortData.class)
		private String fullname;

		/**
		 * Private field that stores a link to the user's avatar.
		 */
		@Column(name = "avatar")
		@JsonView(Views.UserShortData.class)
		private String avatar;

		/**
		 * Private field that stores the username.
		 */
		@Column(name = "username")
		@JsonView(Views.UserFullData.class)
		private String username;

		/**
		 * Private field that stores the user's email.
		 */
		@Email
		@Column(name = "email", unique = true, nullable = false)
		@JsonView(Views.UserFullData.class)
		private String email;

		/**
		 * Private field that stores information about the user's email confirmation.
		 */
		@Column(name = "email_verified", nullable = false)
		@JsonView(Views.UserFullData.class)
		private Boolean emailVerified;

		/**
		 * Private field that stores the type of the user's authorization provider.
		 */
		@Enumerated(EnumType.STRING)
		@Column(name = "auth_provider", nullable = false)
		@JsonView(Views.UserFullData.class)
		private AuthProvider authProvider;

		/**
		 * Private field, which stores the user id, which is stored in the database of the authorization
		 * provider.
		 */
		@Column(name = "auth_provider_id")
		@JsonView(Views.UserFullData.class)
		private String providerId;

		/**
		 * Private field that stores the date of the user's last visit.
		 */
		@Column(name = "last_visit_date")
		@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
		private LocalDateTime lastVisit;

		/**
		 * Private field that stores the email verification token.
		 */
		@OneToOne(mappedBy = "user", orphanRemoval = true)
		private VerificationToken verificationToken;

		/**
		 * A private field that stores a list of user settings.
		 */
		@OneToMany(mappedBy = "user", orphanRemoval = true)
		private List<UserSetting> settings;

		/**
		 * Private field that stores the list of roles available to this user.
		 */
		@ManyToMany
		@JoinTable(name = "user_roles",
				joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "base_id")},
				inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "base_id")})
		private List<Role> roles;

		/**
		 * Private field that stores, if any, the user's encrypted password.
		 */
		@Column(name = "password")
		private String password;
}