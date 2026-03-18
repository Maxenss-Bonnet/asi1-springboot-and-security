package com.security.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import com.security.app.auth.controller.JwtAuthConverter;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig {
    @Autowired
    JwtAuthConverter authConverter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.GET,"/hero/**").authenticated()
                        .requestMatchers(HttpMethod.POST,"/hero/**").hasRole("ADMIN")
                        .requestMatchers("/helloUser").hasRole("USER")
                        .anyRequest().authenticated()
                )
                .oauth2ResourceServer(oauth2ResourceServer -> oauth2ResourceServer.jwt(Customizer.withDefaults()))
                //Enable Login redirection to oauth server
                .oauth2Login(withDefaults())
                //Use to extract Role from JWT
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(jwt -> jwt
                                .jwtAuthenticationConverter(authConverter)
                        )
                );
        return http.build();
    }
}
