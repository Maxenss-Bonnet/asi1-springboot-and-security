package com.security.app.core.filter;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class LogFilter implements Filter {

    private Logger logger = LoggerFactory.getLogger(LogFilter.class);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) 
      throws IOException, ServletException {
    	String given_url="";
    	if( request instanceof HttpServletRequest) {
    		given_url = ((HttpServletRequest)request).getRequestURI();
    	}
    	logger.info("Hello from: " + request.getLocalAddr()+" url asked:"+given_url);
        
    	System.out.println("I AM the Filter!");
        chain.doFilter(request, response);
    }

}
