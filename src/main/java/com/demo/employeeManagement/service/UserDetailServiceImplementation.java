package com.demo.employeeManagement.service;

import java.io.Console;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.demo.employeeManagement.entities.Users;

@Service
public class UserDetailServiceImplementation implements UserDetailsService{
	@Autowired
	private UserService userService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Users users = userService.findUser(username);
		if(users==null) {
			throw new UsernameNotFoundException("user not found for email - "+username);
		}
		
		return new org.springframework.security.core.userdetails.User(users.getUsername(), users.getPassword(),users.getRoles());
	}
	
}
