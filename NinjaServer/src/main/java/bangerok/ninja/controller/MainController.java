package bangerok.ninja.controller;

import bangerok.ninja.domain.User;
import bangerok.ninja.domain.Views;
import bangerok.ninja.dto.MessagePageDto;
import bangerok.ninja.repo.UserDetailsRepo;
import bangerok.ninja.service.MessageService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import java.util.HashMap;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MainController {

		private final MessageService messageService;
		private final UserDetailsRepo userDetailsRepo;
		private final ObjectWriter messageWriter;
		private final ObjectWriter profileWriter;
		private final String profile;

		@Autowired
		public MainController(MessageService messageService, UserDetailsRepo userDetailsRepo,
				ObjectMapper mapper) {
				this.messageService = messageService;
				this.userDetailsRepo = userDetailsRepo;

				ObjectMapper objectMapper = mapper
						.setConfig(mapper.getSerializationConfig());

				this.messageWriter = objectMapper
						.writerWithView(Views.FullMessage.class);
				this.profileWriter = objectMapper
						.writerWithView(Views.FullProfile.class);

				profile = "dev";
		}

		@GetMapping
		public String main(
				Model model,
				@AuthenticationPrincipal User user
		) throws JsonProcessingException {
				HashMap<Object, Object> data = new HashMap<>();

				if (user != null) {
						Optional<User> userFromDbOptional = userDetailsRepo.findById(user.getId());

						User userFromDb = null;
						if (userFromDbOptional.isPresent()) {
								userFromDb = userFromDbOptional.get();
						}
						String serializedProfile = profileWriter.writeValueAsString(userFromDb);
						model.addAttribute("profile", serializedProfile);

						Sort sort = Sort.by(Sort.Direction.DESC, "id");
						PageRequest pageRequest = PageRequest.of(0, MessageController.MESSAGES_PER_PAGE, sort);
						MessagePageDto messagePageDto = messageService.findForUser(pageRequest, user);

						String messages = messageWriter.writeValueAsString(messagePageDto.getMessages());

						model.addAttribute("messages", messages);
						data.put("currentPage", messagePageDto.getCurrentPage());
						data.put("totalPages", messagePageDto.getTotalPages());
				} else {
						model.addAttribute("messages", "[]");
						model.addAttribute("profile", "null");
				}

				model.addAttribute("frontendData", data);
				model.addAttribute("isDevMode", "dev".equals(profile));

				return "index";
		}
}
