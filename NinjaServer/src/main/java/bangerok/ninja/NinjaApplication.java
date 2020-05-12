package bangerok.ninja;

import java.util.Collections;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@SpringBootApplication
@Controller
public class NinjaApplication{

		@GetMapping("/user")
		@ResponseBody
		public Map<String, Object> user(@AuthenticationPrincipal OAuth2User principal) {
				return Collections.singletonMap("name", principal.getAttribute("name"));
		}

		@GetMapping("/error")
		@ResponseBody
		public String error(HttpServletRequest request) {
				String message = (String) request.getSession().getAttribute("error.message");
				request.getSession().removeAttribute("error.message");
				return message;
		}

		public static void main(String[] args) {
				SpringApplication.run(NinjaApplication.class, args);
		}
}
