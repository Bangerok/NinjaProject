package ru.bangerok.ninja.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;
import ru.bangerok.ninja.service.impl.MailServiceImpl;

/**
 * Java config class to customize thymeleaf template usage.
 *
 * @author v.kuznetsov
 * @since 0.4.11
 */
@Configuration
@EnableWebMvc
public class ThymeleafConfiguration {

  /**
   * Getting a template engine to be used to load and populate templates.
   * <p>
   * Used for example here: {@link MailServiceImpl}.
   *
   * @return end object - SpringTemplateEngine.
   */
  @Bean
  public SpringTemplateEngine templateEngine() {
    SpringTemplateEngine templateEngine = new SpringTemplateEngine();
    templateEngine.setTemplateResolver(thymeleafTemplateResolver());
    return templateEngine;
  }

  /**
   * Configuration of paths to templates and the formation of the name of files with them.
   *
   * @return configuration object with settings.
   */
  @Bean
  public ITemplateResolver thymeleafTemplateResolver() {
    ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
    templateResolver.setPrefix("templates/");
    templateResolver.setSuffix(".html");
    templateResolver.setTemplateMode("HTML");
    templateResolver.setCharacterEncoding("UTF-8");
    return templateResolver;
  }
}