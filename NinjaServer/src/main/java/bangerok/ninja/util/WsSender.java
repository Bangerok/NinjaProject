package bangerok.ninja.util;

import bangerok.ninja.dto.EventType;
import bangerok.ninja.dto.ObjectType;
import bangerok.ninja.dto.WsEventDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import java.util.function.BiConsumer;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class WsSender {

		private final SimpMessagingTemplate template;
		private final ObjectMapper mapper;

		public WsSender(SimpMessagingTemplate template, ObjectMapper mapper) {
				this.template = template;
				this.mapper = mapper;
		}

		public <T> BiConsumer<EventType, T> getSender(ObjectType objectType, Class<?> view) {
				ObjectWriter writer = mapper
						.setConfig(mapper.getSerializationConfig())
						.writerWithView(view);

				return (EventType eventType, T payload) -> {
						String value;

						try {
								value = writer.writeValueAsString(payload);
						} catch (JsonProcessingException e) {
								throw new RuntimeException(e);
						}

						template.convertAndSend(
								"/topic/activity",
								new WsEventDto(objectType, eventType, value)
						);
				};
		}
}