package com.security.app.core.hinterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletRequestWrapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class LogInterceptor implements HandlerInterceptor {

	Logger log = org.slf4j.LoggerFactory.getLogger(this.getClass());

	@Override
	// Before data is transmitted back
	// This is used to perform operations after completing the request and response.
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object object, Exception arg3)
			throws Exception {
		log.info("Request is complete");
	}

	@Override
	// After task before the interceptor action
	// This is used to perform operations before sending the response to the client.
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object object, ModelAndView model)
			throws Exception {
		log.info("Handler execution is complete");
	}

	@Override
	// Execute task before the interceptor action
	// This is used to perform operations before sending the request to the
	// controller. This method should return true to return the response to the
	// client.
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
		log.info("Before Handler execution");
		String authHedaerValue = request.getHeader("Authorization");
		if (authHedaerValue != null) {
			log.info("Auth Value: "+authHedaerValue);
			if (authHedaerValue.contains("EXIT")) {
				return false;
			}
		}
		return true;
	}

}
