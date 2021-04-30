package ru.bangerok.ninja.persistence.model.user;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import ru.bangerok.ninja.persistence.model.base.BaseEntity;

/**
 * The essence of the verification token. Used to store issued tokens for user email verification.
 *
 * @author v.kuznetsov
 * @since 0.4.3
 */
@Data
@Entity
@Table(name = "verification_tokens")
@EqualsAndHashCode(of = {"token", "user", "expiryDate"}, callSuper = true)
@ToString(of = {"token", "user", "expiryDate"}, callSuper = true)
public class VerificationToken extends BaseEntity {

		/**
		 * Private field that stores the email verification token.
		 */
		@Column(name = "token", unique = true)
		private String token;

		/**
		 * Private field that stores the date after which the verification token expires.
		 */
		@Column(name = "expiry_date")
		private LocalDateTime expiryDate;

		/**
		 * Private field, in which the user for whom the verification token was issued.
		 */
		@OneToOne
		@JoinTable(name = "user_token",
				joinColumns = {@JoinColumn(name = "token_id", referencedColumnName = "base_id")},
				inverseJoinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "base_id")})
		private User user;
}