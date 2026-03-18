package com.security.app.config.security;

import com.security.app.auth.controller.AppAuthProvider;
import com.security.app.auth.controller.AuthService;
import com.security.app.auth.controller.JwtAuthEntryPoint;
import com.security.app.auth.controller.JwtRequestFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final AuthService authService;
    private final JwtAuthEntryPoint jwtAuthenticationEntryPoint;
	private final JwtRequestFilter jwtRequestFilter;
	private final PasswordEncoder passwordEncoder;
	public SecurityConfig( AuthService authService, 
							JwtAuthEntryPoint jwtAuthenticationEntryPoint,
							JwtRequestFilter jwtRequestFilter,
							PasswordEncoder passwordEncoder) {
		this.authService=authService;
		this.jwtAuthenticationEntryPoint=jwtAuthenticationEntryPoint;
		this.jwtRequestFilter=jwtRequestFilter;
		this.passwordEncoder=passwordEncoder;
	}

	// Security chain for JWT
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		// use to allow direct login call without hidden value csfr (Cross Site Request
		// Forgery) needed
		http.csrf(csrf->csrf.disable());
		http.exceptionHandling(ex->ex
				.authenticationEntryPoint(jwtAuthenticationEntryPoint));
		http.sessionManagement(sess->sess
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		http.authenticationProvider(getProvider())
				.authorizeHttpRequests(
						auth-> auth
								.requestMatchers("/login").permitAll()
								.requestMatchers("/hero/**").authenticated()
								.anyRequest().authenticated());
		// Add a filter to validate the tokens with every request
		http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
		return http.build();
	}

		@Bean
		public AuthenticationProvider getProvider() {
			AppAuthProvider provider = new AppAuthProvider(authService);
			provider.setPasswordEncoder(passwordEncoder);
			return provider;
		}


		// update due to WebSecurityConfigurerAdapter deprecated
		@Bean
		AuthenticationManager authenticationManager(AuthenticationConfiguration authConfiguration) throws Exception {
			return authConfiguration.getAuthenticationManager();
		}
//	@Bean
//	public PasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder();
//	}
}