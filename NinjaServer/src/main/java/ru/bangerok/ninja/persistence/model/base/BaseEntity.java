package ru.bangerok.ninja.persistence.model.base;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

/**
 * Базовый класс для всех сущностей.
 *
 * @author v.kuznetsov
 * @version 1.0
 */
@Data
@MappedSuperclass
@EqualsAndHashCode(of = {"id"})
@ToString(of = {"id"})
public class BaseEntity {

		/**
		 * Private поле, в котором хранится информация об id записи в базе данных. Автоматически
		 * генерируется при сохранении в базу данных. Предварительно назначать не нужно.
		 */
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name = "base_id")
		private Long id;

		/**
		 * Private поле, в котором хранится информация о дате сохранения записи в базе данных.
		 * Предварительно назначать не нужно.
		 */
		@CreatedDate
		@Column(name = "base_created_date")
		private Date created;

		/**
		 * Private поле, в котором хранится информация о дате обновления записи в базе данных.
		 * Предварительно назначать не нужно.
		 */
		@LastModifiedDate
		@Column(name = "base_updated_date")
		private Date updated;

		/**
		 * Private поле, в котором хранится информация о статусе записи в базе данных. Предварительно
		 * назначать не нужно.
		 */
		@Enumerated(EnumType.STRING)
		@Column(name = "base_status")
		private BaseStatus status;

		/**
		 * Private метод, который выполняется перед сохранением записи в базу данных.
		 */
		@PrePersist
		private void prePersistFunction() {
				created = new Date();
				status = BaseStatus.ACTIVE;
		}

		/**
		 * Private метод, который выполняется перед обновлением записи в базе данных.
		 */
		@PreUpdate
		private void preUpdateFunction() {
				updated = new Date();
		}
}
