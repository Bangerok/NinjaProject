package ru.bangerok.ninja.persistence.model.base;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import ru.bangerok.ninja.persistence.model.views.Views;

/**
 * Base class for all entities.
 *
 * @author v.kuznetsov
 * @since 0.3.3
 */
@Getter
@Setter
@EqualsAndHashCode(doNotUseGetters = true)
@ToString(doNotUseGetters = true)
@MappedSuperclass
public class BaseEntity {

  /**
   * Private field that stores information about the id of the record in the database.
   * Automatically generated when saved to the database. No need to pre-assign.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "base_id", unique = true, nullable = false)
  @JsonView(Views.BaseId.class)
  private Long id;

  /**
   * Private field that stores information about the date the record was saved in the database. No
   * need to pre-assign.
   */
  @CreatedDate
  @Column(name = "base_created_date", nullable = false)
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
  @JsonView(Views.BaseFull.class)
  private LocalDateTime created;

  /**
   * Private field that stores information about the date the record was updated in the database.
   * No need to pre-assign.
   */
  @LastModifiedDate
  @Column(name = "base_updated_date")
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
  @JsonView(Views.BaseFull.class)
  private LocalDateTime updated;

  /**
   * Private field that stores information about the status of a record in the database. It is not
   * necessary to pre-appoint.
   */
  @Column(name = "base_status", nullable = false)
  @Enumerated(EnumType.STRING)
  @JsonView(Views.BaseFull.class)
  private BaseStatus status;

  /**
   * Private method that is executed before saving the record to the database.
   */
  @PrePersist
  private void prePersistFunction() {
    created = LocalDateTime.now();
    status = BaseStatus.ACTIVE;
  }

  /**
   * Private method that is executed before updating a record in the database.
   */
  @PreUpdate
  private void preUpdateFunction() {
    updated = LocalDateTime.now();
  }
}