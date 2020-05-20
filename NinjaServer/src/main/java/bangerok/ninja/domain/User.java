package bangerok.ninja.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Table(name = "Usr")
@Data
@EqualsAndHashCode(of = {"id"})
@ToString(of = {"id", "name"})
public class User {

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@JsonView(Views.IdName.class)
		private long id;

		@JsonView(Views.IdName.class)
		private String name;

		@JsonView(Views.IdName.class)
		private String userpic;

		@Column(unique=true)
		private String email;

		@JsonView(Views.FullProfile.class)
		private String gender;

		@JsonView(Views.FullProfile.class)
		private String locale;

		@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
		@JsonView(Views.FullProfile.class)
		private LocalDateTime lastVisit;

		@JsonView(Views.FullProfile.class)
		@OneToMany(
				mappedBy = "subscriber",
				orphanRemoval = true
		)
		private Set<UserSubscription> subscriptions = new HashSet<>();

		@JsonView(Views.FullProfile.class)
		@OneToMany(
				mappedBy = "channel",
				orphanRemoval = true,
				cascade = CascadeType.ALL
		)
		private Set<UserSubscription> subscribers = new HashSet<>();
}
