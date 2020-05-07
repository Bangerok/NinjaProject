package bangerok.ninja.controller;

import bangerok.ninja.domain.Message;
import bangerok.ninja.domain.User;
import bangerok.ninja.domain.Views;
import bangerok.ninja.dto.MessagePageDto;
import bangerok.ninja.service.MessageService;
import com.fasterxml.jackson.annotation.JsonView;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("message")
public class MessageController {

		public static final int MESSAGES_PER_PAGE = 3;

		private final MessageService messageService;

		@Autowired
		public MessageController(MessageService messageService) {
				this.messageService = messageService;
		}

		@GetMapping
		@JsonView(Views.FullMessage.class)
		public MessagePageDto list(
				@AuthenticationPrincipal User user,
				@PageableDefault(size = MESSAGES_PER_PAGE, sort = {
						"id"}, direction = Sort.Direction.DESC) Pageable pageable
		) {
				return messageService.findForUser(pageable, user);
		}

		@GetMapping("{id}")
		@JsonView(Views.FullMessage.class)
		public Message getOne(@PathVariable("id") Message message) {
				return message;
		}

		@PostMapping
		public Message create(
				@RequestBody Message message,
				@AuthenticationPrincipal User user
		) throws IOException {
				return messageService.create(message, user);
		}

		@PutMapping("{id}")
		public Message update(
				@PathVariable("id") Message messageFromDb,
				@RequestBody Message message
		) throws IOException {
				return messageService.update(messageFromDb, message);
		}

		@DeleteMapping("{id}")
		public void delete(@PathVariable("id") Message message) {
				messageService.delete(message);
		}
}
