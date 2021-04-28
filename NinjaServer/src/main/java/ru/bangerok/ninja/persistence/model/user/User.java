package ru.bangerok.ninja.persistence.model.user;

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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import ru.bangerok.ninja.enumeration.AuthProvider;
import ru.bangerok.ninja.persistence.model.base.BaseEntity;

/**
 * Сущность пользователей. Используется для хранения данных авторизованных пользователей и их
 * ролей.
 *
 * @author v.kuznetsov
 * @version 1.0
 */
@Data
@Entity
@Table(name = "users")
@EqualsAndHashCode(of = {"username"}, callSuper = true)
@ToString(of = {"fullname"}, callSuper = true)
public class User extends BaseEntity {

		/**
		 * Private поле, в котором хранится полное имя пользователя.
		 */
		@Column(name = "full_name")
		private String fullname;

		/**
		 * Private поле, в котором хранится логин пользователя.
		 */
		@Column(name = "username")
		private String username;

		/**
		 * Private поле, в котором хранится ссылка на аватарку пользователя.
		 */
		@Column(name = "avatar")
		private String avatar;

		/**
		 * Private поле, в котором хранится электронная почта пользователя.
		 */
		@Email
		@Column(name = "email", unique = true)
		private String email;

		/**
		 * Private поле, в котором хранится информация о подтверждении электронной почты пользователя.
		 */
		@Column(name = "email_verified")
		private Boolean emailVerified;

		/**
		 * Private поле, в котором хранится тип провайдера авторизации пользователя.
		 */
		@Enumerated(EnumType.STRING)
		@Column(name = "auth_provider")
		private AuthProvider authProvider;

		/**
		 * Private поле, в котором хранится id пользователя, который хранится в базе данных провайдера
		 * авторизации.
		 */
		@Column(name = "auth_provider_id", unique = true)
		private String providerId;

		/**
		 * Private поле, в котором хранится дата последнего визита пользователя.
		 */
		@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
		@Column(name = "last_visit_date")
		private LocalDateTime lastVisit;

		/**
		 * Private поле, в котором хранится, если есть, зашифрованный пароль пользователя.
		 */
		@JsonIgnore
		@Column(name = "password")
		private String password;

		/**
		 * Private поле, в котором хранится токен для верификации электронной почты.
		 */
		@JsonIgnore
		@OneToOne(mappedBy = "user")
		private VerificationToken verificationToken;

		/**
		 * Private поле, в котором хранится список ролей, доступных данному пользователю.
		 */
		@JsonIgnore
		@ManyToMany
		@JoinTable(name = "user_roles",
				joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "base_id")},
				inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "base_id")})
		private Set<Role> roles;
}