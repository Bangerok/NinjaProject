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
 * Сущность токена верификации. Используется для хранения выданных токенов для верификации
 * электронной почты пользователей.
 *
 * @author v.kuznetsov
 * @version 1.0
 */
@Data
@Entity
@Table(name = "verification_tokens")
@EqualsAndHashCode(of = {"token", "user", "expiryDate"}, callSuper = true)
@ToString(of = {"token", "user", "expiryDate"}, callSuper = true)
public class VerificationToken extends BaseEntity {

		/**
		 * Private поле, в котором хранится токен для верификации электронной почты.
		 */
		@Column(name = "token")
		private String token;

		/**
		 * Private поле, в котором хранится дата, после которой действие токена верификации
		 * прекращается.
		 */
		@Column(name = "expiry_date")
		private LocalDateTime expiryDate;

		/**
		 * Private поле, в котором пользователь, для которого был выдан токен верификации.
		 */
		@OneToOne
		@JoinTable(name = "user_token",
				joinColumns = {@JoinColumn(name = "token_id", referencedColumnName = "base_id")},
				inverseJoinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "base_id")})
		private User user;
}
