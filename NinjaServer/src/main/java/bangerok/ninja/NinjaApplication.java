package bangerok.ninja;

import bangerok.ninja.config.WebAppPropertiesConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(WebAppPropertiesConfig.class)
public class NinjaApplication {

		public static void main(String[] args) {
				SpringApplication.run(NinjaApplication.class, args);
		}
}
