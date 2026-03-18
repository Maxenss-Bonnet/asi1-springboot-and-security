package com.security.app.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf->csrf.disable());
        http
                .authorizeHttpRequests(auth -> auth.requestMatchers("/login").permitAll())
                .formLogin(withDefaults())
                .authorizeHttpRequests(auth ->
                        auth.requestMatchers("/hero/**").authenticated()
                                .anyRequest().permitAll())
                .logout(logout-> logout.logoutUrl("/logout").permitAll().invalidateHttpSession(true));
        return http.build();
    }
}