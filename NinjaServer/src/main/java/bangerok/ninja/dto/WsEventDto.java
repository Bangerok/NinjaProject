package bangerok.ninja.dto;

import bangerok.ninja.domain.view.Views;
import com.fasterxml.jackson.annotation.JsonRawValue;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@JsonView(Views.Id.class)
public class WsEventDto {

		private ObjectType objectType;
		private EventType eventType;
		@JsonRawValue
		private String body;
}
