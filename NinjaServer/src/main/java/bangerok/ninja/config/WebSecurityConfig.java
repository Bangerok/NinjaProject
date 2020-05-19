package bangerok.ninja.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

		private final String DEFAULT_CLIENT_URL = "http://localhost:8000";

		@Override
		protected void configure(HttpSecurity http) throws Exception {
				http
						.cors().and().csrf().disable()
						.authorizeRequests(a -> a
								.antMatchers("/**", "/auth/**").permitAll()
								.anyRequest().authenticated()
						)
						.exceptionHandling(e -> e
								.authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
						)
						.logout(l -> l
								.deleteCookies("remove")
								.invalidateHttpSession(true)
								.logoutSuccessUrl(DEFAULT_CLIENT_URL + "/login")
								.permitAll()
						)
						.oauth2Login(o -> o
								.authorizationEndpoint()
								.baseUri("/login").and()
								.defaultSuccessUrl(DEFAULT_CLIENT_URL)
						);
		}
}
