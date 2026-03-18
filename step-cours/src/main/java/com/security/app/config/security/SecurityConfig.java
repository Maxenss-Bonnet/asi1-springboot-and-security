package com.security.app.config.security;

import com.security.app.config.security.auth.AppAuthProvider;
import com.security.app.config.security.auth.JwtAuthEntryPoint;
import com.security.app.user.controller.MyUService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class SecurityConfig {
	private final MyUService uService;
	private final PasswordEncoder pEncoder;
	@Autowired
	private JwtRequestFilter jwtRequestFilter;
	@Autowired
	private JwtAuthEntryPoint jwtAuthenticationEntryPoint;


	public SecurityConfig(MyUService uService,PasswordEncoder pEncoder) {
		this.uService=uService;
		this.pEncoder=pEncoder;
	}


//	@Bean
//	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//		//use to allow direct login call without hidden value csfr (Cross Site Request Forgery) needed
//		http.csrf().disable();
//				http
//				.authorizeRequests().antMatchers("/login").permitAll().and()
//				.formLogin()
//					.loginProcessingUrl("/login")
//					.permitAll()
//					.and()
//				.logout()
//					.logoutUrl("/logout")
//					.permitAll()
//					.invalidateHttpSession(true)
//					.and()
//				.authorizeRequests()
//					.antMatchers( "/hero").authenticated()
//					.anyRequest().permitAll();
//
//	    return http.build();
//	}

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
								.requestMatchers("/hero/**").hasAnyAuthority("ROLE_ADMIN")
								.anyRequest().authenticated());
		// Add a filter to validate the tokens with every request
		http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
		return http.build();
	}



	@Bean
	public AuthenticationProvider getProvider() {
		AppAuthProvider provider = new AppAuthProvider(uService);
		provider.setUserDetailsService(uService);
		provider.setPasswordEncoder(pEncoder);
		return provider;
	}


	// update due to WebSecurityConfigurerAdapter deprecated
	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration authConfiguration) throws Exception {
		return authConfiguration.getAuthenticationManager();
	}


}