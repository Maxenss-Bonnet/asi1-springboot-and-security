package com.security.app.auth.controller;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.stereotype.Component;
@Component
public class JwtAuthConverter implements Converter<Jwt, AbstractAuthenticationToken> {

    @Value("${spring.security.oauth2.client.registration.keycloak.client-id}")
    private String oAuthClientName;

    private final JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();

    @Override
    public AbstractAuthenticationToken convert(Jwt jwt) {
        Collection<GrantedAuthority> authorities = Stream.concat(
                jwtGrantedAuthoritiesConverter.convert(jwt).stream(),
                extractRoles(jwt).stream()
        ).collect(Collectors.toSet());

        return new JwtAuthenticationToken(jwt, authorities);
    }

    private Collection<? extends GrantedAuthority> extractRoles(Jwt jwt) {
        Set<String> roles = new HashSet<>();

        // Extract roles from realm_access
        Map<String, Object> realmAccess = jwt.getClaim("realm_access");
        if (realmAccess != null && realmAccess.containsKey("roles")) {
            roles.addAll((Collection<? extends String>) realmAccess.get("roles"));
        }

        // Extract roles from resource_access.demo
        Map<String, Object> resourceAccess = jwt.getClaim("resource_access");
        if (resourceAccess != null && resourceAccess.containsKey(oAuthClientName)) {
            Map<String, Object> demoAccess = (Map<String, Object>) resourceAccess.get(oAuthClientName);
            if (demoAccess != null && demoAccess.containsKey("roles")) {
                roles.addAll((Collection<? extends String>) demoAccess.get("roles"));
            }
        }

        // Debugging extracted roles
        System.out.println("Extracted roles: " + roles);

        return roles.stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.toUpperCase()))
                .collect(Collectors.toSet());
    }



}

