package ru.bangerok.ninja.persistence.model.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ru.bangerok.ninja.persistence.model.base.BaseEntity;

/**
 * Entity for the table with verification tokens. Used to store issued tokens for user email
 * verification.
 *
 * @author v.kuznetsov
 * @since 0.4.3
 */
@Getter
@Setter
@EqualsAndHashCode(doNotUseGetters = true, callSuper = true)
@ToString(doNotUseGetters = true, callSuper = true)
@Entity
@Table(name = "verification_tokens")
public class VerificationToken extends BaseEntity {

  /**
   * Private field that stores the email verification token.
   */
  @Column(name = "value", unique = true, nullable = false)
  private String value;

  /**
   * Private field that stores the date after which the verification token expires.
   */
  @Column(name = "expiry_date", nullable = false)
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
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