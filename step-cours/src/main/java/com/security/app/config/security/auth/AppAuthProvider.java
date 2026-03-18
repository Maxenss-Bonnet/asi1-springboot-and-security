package com.security.app.config.security.auth;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;

import com.security.app.user.controller.MyUService;

public class AppAuthProvider extends DaoAuthenticationProvider{
	
	private final MyUService uService;
	public AppAuthProvider(MyUService uService) {
		this.uService = uService;
	}

	
	@Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UsernamePasswordAuthenticationToken auth = (UsernamePasswordAuthenticationToken) authentication;
        String name = auth.getName();
        String password_raw = auth.getCredentials()
                .toString();
        UserDetails user = uService.loadUserByUsername(name);
        if (user == null) {
            throw new BadCredentialsException("Username/Password does not match for " + auth.getPrincipal());
            
        }else
        	if(!this.getPasswordEncoder().matches(password_raw, user.getPassword())) {
        	   throw new BadCredentialsException("Username/Password does not match for " + auth.getPrincipal());
        	}
        return new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
    }
    
    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

}
