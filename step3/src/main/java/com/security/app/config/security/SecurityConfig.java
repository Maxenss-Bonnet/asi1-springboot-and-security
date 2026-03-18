package com.security.app.config.security;

import com.security.app.auth.controller.AppAuthProvider;
import com.security.app.auth.controller.AuthService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	private final AuthService authService;

	public SecurityConfig(AuthService authService) {
		this.authService = authService;
	}

	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration authConfiguration) throws Exception {
		return authConfiguration.getAuthenticationManager();
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		//use to allow direct login call without hidden value csfr (Cross Site Request Forgery) needed
		http.csrf(csrf->csrf.disable());
		http.authenticationProvider(getProvider());
		http.authorizeHttpRequests(auth->
						auth.requestMatchers("/hero/**").authenticated()
								.anyRequest().permitAll());
		http
				.formLogin(form-> form.loginProcessingUrl("/login")
						.permitAll())
				.logout(logout->logout.logoutUrl("/logout")
						.permitAll()
						.invalidateHttpSession(true));
		return http.build();
	}

	@Bean
	public AuthenticationProvider getProvider() {
		AppAuthProvider provider = new AppAuthProvider(authService);
		return provider;
	}
}