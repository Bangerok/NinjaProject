package bangerok.ninja.domain;

import bangerok.ninja.domain.enumeration.AuthProvider;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Table(name = "Usr", uniqueConstraints = {
		@UniqueConstraint(columnNames = "email")
})
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

		@Email
		@Column(unique=true)
		private String email;

		@JsonView(Views.FullProfile.class)
		private String gender;

		@JsonView(Views.FullProfile.class)
		private String locale;

		private Boolean emailVerified = false;

		@JsonIgnore
		private String password;

		@NotNull
		@Enumerated(EnumType.STRING)
		private AuthProvider provider;

		private String providerId;

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
