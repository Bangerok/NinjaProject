package bangerok.ninja;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

@EnableAuthorizationServer
@SpringBootApplication
public class NinjaApplication extends SpringBootServletInitializer {

		public static void main(String[] args) {
				SpringApplication.run(NinjaApplication.class, args);
		}
}
