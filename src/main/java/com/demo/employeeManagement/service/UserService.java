package com.demo.employeeManagement.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.demo.employeeManagement.controller.PasswordUpdateRequest;
import com.demo.employeeManagement.dao.UserRepository;
import com.demo.employeeManagement.entities.Users;


@Service
public class UserService{
	@Autowired
    private UserRepository userRepository;
	//To check whether password is matching or not
	private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);
	
	public Users findUser(String email) {
		return userRepository.findByUsername(email);
	}
	public  Users findUserById(int id ) {
		Optional <Users>optional  = userRepository.findById(id);
		if(optional.get()==null) {
			throw new NullPointerException();
		}
		return optional.get();
	}
	public String generateHash(String input) {
        return passwordEncoder.encode(input);
    }

    // Method to verify hash
    public boolean verifyHash(String input, String hashed) {
        return passwordEncoder.matches(input, hashed);
    }
	public Users updateUserPasswordById(int id,String oldPassword, String newPassword) {
		//first check old password is correct or not
		
		Optional <Users> optional = userRepository.findById(id);
		if(optional.isEmpty()) {
			throw new NullPointerException("User not found with the given id");
		}
		else {
			//check passwords
			Users oldUsers = optional.get();
			if(verifyHash(oldPassword, oldUsers.getPassword())) {
				//means user is updating his own account
				oldUsers.setPassword(generateHash(newPassword));
				return oldUsers;
			}
			else {
				System.out.println("Password Mismatch");
				return oldUsers;
				
			}
		}
		
	}
}
