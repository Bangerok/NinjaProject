package bangerok.ninja.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

		@Override
		protected void configure(HttpSecurity http) throws Exception {
				SimpleUrlAuthenticationFailureHandler handler = new SimpleUrlAuthenticationFailureHandler(
						"/");

				http
						.authorizeRequests(a -> a
								.antMatchers("/", "/login**", "/js/**", "/error**").permitAll()
								.anyRequest().authenticated()
						)
						.exceptionHandling(e -> e
								.authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
						)
						.csrf(c -> c
								.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
						)
						.logout(l -> l
								.logoutSuccessUrl("/").permitAll()
						)
						.oauth2Login(o -> o
								.failureHandler((request, response, exception) -> {
										request.getSession().setAttribute("error.message", exception.getMessage());
										handler.onAuthenticationFailure(request, response, exception);
								})
						);
		}
}
