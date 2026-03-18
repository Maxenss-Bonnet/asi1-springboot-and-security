package com.security.app.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer ;

import com.security.app.core.hinterceptor.LogInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	@Autowired
	LogInterceptor logInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(logInterceptor);
	}
}