package bangerok.ninja.dto;

import bangerok.ninja.domain.Message;
import bangerok.ninja.domain.Views;
import com.fasterxml.jackson.annotation.JsonView;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@JsonView(Views.FullMessage.class)
public class MessagePageDto {

		private List<Message> messages;
		private int currentPage;
		private int totalPages;
}
