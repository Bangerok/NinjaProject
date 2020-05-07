package bangerok.ninja.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
@EqualsAndHashCode(of = "id")
@ToString(of = "id")
public class UserSubscription implements Serializable {

		@EmbeddedId
		@JsonIgnore
		private UserSubscriptionId id;

		@MapsId("channelId")
		@ManyToOne
		@JsonView(Views.IdName.class)
		@JsonIdentityReference
		@JsonIdentityInfo(
				property = "id",
				generator = ObjectIdGenerators.PropertyGenerator.class
		)
		private User channel;

		@MapsId("subscriberId")
		@ManyToOne
		@JsonView(Views.IdName.class)
		@JsonIdentityReference
		@JsonIdentityInfo(
				property = "id",
				generator = ObjectIdGenerators.PropertyGenerator.class
		)
		private User subscriber;

		@JsonView(Views.IdName.class)
		private boolean active;

		public UserSubscription(User channel, User subscriber) {
				this.channel = channel;
				this.subscriber = subscriber;
				this.id = new UserSubscriptionId(channel.getId(), subscriber.getId());
		}
}
