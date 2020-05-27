package bangerok.ninja.config;

import bangerok.ninja.security.CustomUserDetailsService;
import bangerok.ninja.security.RestAuthenticationEntryPoint;
import bangerok.ninja.security.TokenAuthenticationFilter;
import bangerok.ninja.security.oauth2.CustomOAuth2UserService;
import bangerok.ninja.security.oauth2.HttpCookieOAuth2AuthorizationRequestRepository;
import bangerok.ninja.security.oauth2.OAuth2AuthenticationFailureHandler;
import bangerok.ninja.security.oauth2.OAuth2AuthenticationSuccessHandler;
import bangerok.ninja.security.oauth2.OAuth2LogoutSuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

		private final CustomUserDetailsService customUserDetailsService;
		private final CustomOAuth2UserService customOAuth2UserService;
		private final OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler;
		private final OAuth2AuthenticationFailureHandler oAuth2AuthenticationFailureHandler;
		private final OAuth2LogoutSuccessHandler oAuth2LogoutSuccessHandler;

		public SecurityConfig(
				CustomUserDetailsService customUserDetailsService,
				CustomOAuth2UserService customOAuth2UserService,
				OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler,
				OAuth2AuthenticationFailureHandler oAuth2AuthenticationFailureHandler,
				OAuth2LogoutSuccessHandler oAuth2LogoutSuccessHandler) {
				this.customUserDetailsService = customUserDetailsService;
				this.customOAuth2UserService = customOAuth2UserService;
				this.oAuth2AuthenticationSuccessHandler = oAuth2AuthenticationSuccessHandler;
				this.oAuth2AuthenticationFailureHandler = oAuth2AuthenticationFailureHandler;
				this.oAuth2LogoutSuccessHandler = oAuth2LogoutSuccessHandler;
		}

		@Override
		public void configure(AuthenticationManagerBuilder authenticationManagerBuilder)
				throws Exception {
				authenticationManagerBuilder
						.userDetailsService(customUserDetailsService)
						.passwordEncoder(passwordEncoder());
		}

		@Override
		protected void configure(HttpSecurity http) throws Exception {
				http
						.cors().and()
						.sessionManagement()
						.sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
						.csrf().disable()
						.formLogin()
						.disable()
						.httpBasic()
						.disable()
						.exceptionHandling(e -> e
								.authenticationEntryPoint(new RestAuthenticationEntryPoint())
						)
						.authorizeRequests(a -> a
								.antMatchers("/auth/signup", "/auth/login", "/login/**").permitAll()
								.antMatchers("/auth/user").hasRole("USER")
								.anyRequest().authenticated()
						)
						.logout(l -> l
								.deleteCookies("JSESSIONID")
								.invalidateHttpSession(true)
								.logoutSuccessHandler(oAuth2LogoutSuccessHandler)
								.permitAll()
						)
						.oauth2Login(o -> o
								.authorizationEndpoint()
								.baseUri("/login")
								.authorizationRequestRepository(cookieAuthorizationRequestRepository())
								.and()
								.redirectionEndpoint()
								.baseUri("/login/oauth2/code/*")
								.and()
								.userInfoEndpoint()
								.userService(customOAuth2UserService)
								.and()
								.successHandler(oAuth2AuthenticationSuccessHandler)
								.failureHandler(oAuth2AuthenticationFailureHandler)
						)
						.addFilterBefore(tokenAuthenticationFilter(),
								UsernamePasswordAuthenticationFilter.class);
		}

		@Bean(BeanIds.AUTHENTICATION_MANAGER)
		@Override
		public AuthenticationManager authenticationManagerBean() throws Exception {
				return super.authenticationManagerBean();
		}

		@Bean
		public TokenAuthenticationFilter tokenAuthenticationFilter() {
				return new TokenAuthenticationFilter();
		}

		@Bean
		public HttpCookieOAuth2AuthorizationRequestRepository cookieAuthorizationRequestRepository() {
				return new HttpCookieOAuth2AuthorizationRequestRepository();
		}

		@Bean
		public PasswordEncoder passwordEncoder() {
				return new BCryptPasswordEncoder();
		}
}