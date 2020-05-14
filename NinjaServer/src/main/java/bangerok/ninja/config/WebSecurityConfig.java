package bangerok.ninja.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

		@Override
		protected void configure(HttpSecurity http) throws Exception {
				http
						.cors().and()
						.authorizeRequests(a -> a
								.antMatchers("/**", "/auth/**").permitAll()
								.anyRequest().authenticated()
						)
						.exceptionHandling(e -> e
								.authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
						)
						.csrf(c -> c
								.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
						)
						.logout(l -> l
								.deleteCookies("remove")
								.invalidateHttpSession(true)
								.logoutSuccessUrl("/auth/logoutSuccess")
								.permitAll()
						)
						.oauth2Login(o -> o
								.authorizationEndpoint()
								.baseUri("/login").and()
								.defaultSuccessUrl("/auth/loginSuccess")
						);
		}
}
