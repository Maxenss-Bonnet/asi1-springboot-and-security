package com.security.app.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public class AppAuthProvider extends DaoAuthenticationProvider {



    public AppAuthProvider(UserDetailsService userDetailsService) {
        super(userDetailsService);
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UsernamePasswordAuthenticationToken auth = (UsernamePasswordAuthenticationToken) authentication;
        String name = auth.getName();
        String password = auth.getCredentials()
                .toString();
        
        UserDetails user = this.getUserDetailsService().loadUserByUsername(name);
        if (user == null) {
            throw new BadCredentialsException("Username/Password does not match for " + auth.getPrincipal());
            
        }else if(!this.getPasswordEncoder().matches(password, user.getPassword())) {
        	   throw new BadCredentialsException("Username/Password does not match for " + auth.getPrincipal());
        }
        return new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
    }
    
    @Override
    public boolean supports(Class<?> authentication) {
    	return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}