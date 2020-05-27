package ru.bangerok.ninja.domain.base;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@Data
@MappedSuperclass
@EqualsAndHashCode(of = { "id" })
@ToString(of = { "id" })
public class BaseEntity {

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name = "base_id")
		private Long id;

		@CreatedDate
		@Column(name = "base_created_date")
		private Date created;

		@LastModifiedDate
		@Column(name = "base_updated_date")
		private Date updated;

		@Enumerated(EnumType.STRING)
		@Column(name = "base_status")
		private BaseStatus status;

		@PrePersist
		private void prePersistFunction(){
				created = new Date();
				status = BaseStatus.ACTIVE;
		}

		@PreUpdate
		private void preUpdateFunction(){
				updated = new Date();
		}

		@PreRemove
		private void preRemoveFunction(){
				status = BaseStatus.DELETED;
		}
}
