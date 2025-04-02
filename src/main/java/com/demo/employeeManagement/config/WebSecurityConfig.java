package com.demo.employeeManagement.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.demo.employeeManagement.service.UserDetailServiceImplementation;

@Configuration
public class WebSecurityConfig {
	private final UserDetailServiceImplementation userDetailServiceImplementation;
	
	WebSecurityConfig(UserDetailServiceImplementation userDetailServiceImplementation) {
		this.userDetailServiceImplementation =  userDetailServiceImplementation;
	}
	@Bean
	BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.httpBasic(Customizer.withDefaults());
		http.authorizeHttpRequests((auth)->{
			auth
			.requestMatchers(HttpMethod.GET, "/employees/all").hasAnyRole("USER","MANAGER","ADMIN")
			.requestMatchers(HttpMethod.GET, "/employees/**").hasAnyRole("USER","MANAGER","ADMIN")
			.requestMatchers(HttpMethod.PUT, "/employees/update/**").hasAnyRole("ADMIN","MANAGER")
			.requestMatchers(HttpMethod.DELETE, "/employees/delete/**").hasAnyRole("ADMIN")
			.requestMatchers(HttpMethod.POST, "/employees/add").hasAnyRole("ADMIN")
			.requestMatchers(HttpMethod.POST, "/users/updatepassword/**").hasAnyRole("USER");
			
		});
		http.cors();
		http.userDetailsService(userDetailServiceImplementation);
		http.csrf(csrf-> csrf.disable());//cross-side request forgery
		return http.build();
	
	}
}
