package com.demo.employeeManagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.demo.employeeManagement.dao.UserRepository;
import com.demo.employeeManagement.entities.Users;


@Service
public class UserService{
	@Autowired
    private UserRepository userRepository;
	public Users findUser(String email) {
		return userRepository.findByUsername(email);
	}
	
}
