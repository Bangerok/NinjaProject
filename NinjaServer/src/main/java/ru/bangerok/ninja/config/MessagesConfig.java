package ru.bangerok.ninja.config;

import java.nio.charset.StandardCharsets;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import ru.bangerok.ninja.service.impl.MessageServiceImpl;

/**
 * Configuration java class for declaring and configuring a bean for localization of regular and
 * validation messages.
 *
 * @author v.kuznetsov
 * @since 0.4.4
 */
@Configuration
public class MessagesConfig {

		/**
		 * Getting and configuring a ResourceBundle to localize messages by key.
		 * <p>
		 * Used for example here: {@link MessageServiceImpl}.
		 *
		 * @return configuration object with settings for receiving messages.
		 */
		@Bean
		public ResourceBundleMessageSource messages() {
				var source = new ResourceBundleMessageSource();
				source.setBasenames("messages/labels");
				source.setDefaultEncoding(StandardCharsets.UTF_8.name());
				source.setUseCodeAsDefaultMessage(true);

				return source;
		}
}