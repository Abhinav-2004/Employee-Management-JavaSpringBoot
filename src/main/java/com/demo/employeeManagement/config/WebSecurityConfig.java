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
			.requestMatchers(HttpMethod.POST, "/users/updatepassword/**").hasAnyRole("USER")
			.requestMatchers(HttpMethod.POST, "/users/updateusername/**").hasAnyRole("USER")
			.requestMatchers(HttpMethod.POST, "/users/add").hasAnyRole("ADMIN")
			.requestMatchers(HttpMethod.DELETE, "/users/delete/**").hasAnyRole("ADMIN")
			.requestMatchers(HttpMethod.POST, "/role/add").hasAnyRole("ADMIN")
			.requestMatchers(HttpMethod.POST, "/role/update/**").hasAnyRole("ADMIN")
			.requestMatchers(HttpMethod.DELETE, "/role/delete/**").hasAnyRole("ADMIN")
			.requestMatchers(HttpMethod.GET, "/role/all").hasAnyRole("ADMIN","USER", "MANAGER","INTERN")
			.requestMatchers(HttpMethod.POST, "/user-role/add").hasAnyRole("ADMIN")
			.requestMatchers(HttpMethod.DELETE, "/user-role/delete").hasAnyRole("ADMIN");
			
		});
		http.cors();//to call from browser frontend
		http.userDetailsService(userDetailServiceImplementation);
		http.csrf(csrf-> csrf.disable());//cross-side request forgery
		return http.build();
	
	}
}
