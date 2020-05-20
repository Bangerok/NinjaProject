package bangerok.ninja.domain;

import com.fasterxml.jackson.annotation.JsonView;
import java.io.Serializable;
import javax.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserSubscriptionId implements Serializable {

		@JsonView(Views.Id.class)
		private long channelId;
		@JsonView(Views.Id.class)
		private long subscriberId;
}
