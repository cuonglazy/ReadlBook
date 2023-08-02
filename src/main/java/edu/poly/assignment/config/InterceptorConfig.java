package edu.poly.assignment.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import edu.poly.assignment.service.AuthInterceptor;
import edu.poly.assignment.service.GlobalInterceptor;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

	@Autowired
	GlobalInterceptor global;

	@Autowired
	AuthInterceptor auth;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// TODO Auto-generated method stub
		registry.addInterceptor(global)
				.addPathPatterns("/**")
				.excludePathPatterns("/assets/**");

		registry.addInterceptor(auth)
				.addPathPatterns("/account/**", "/admin/**", "/cart/**")
				.excludePathPatterns("/assert/index", "/account/register","/account/index", "/logout");
		// .excludePathPatterns("/assets/**");
	}
}
