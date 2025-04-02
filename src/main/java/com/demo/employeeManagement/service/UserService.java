package com.demo.employeeManagement.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
			//System.out.println(oldPassword );
			//System.out.println(oldUsers.getPassword());
			if(verifyHash(oldPassword, oldUsers.getPassword()) && oldUsers.getId()==id) {
				//means user is updating his own account
				oldUsers.setPassword(generateHash(newPassword));
				return userRepository.save(oldUsers);
			}
			else {
				System.out.println("Password Mismatch");
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Password Mismatch");
				
			}
		}
		
	}
	//Update Username By Id can only be done by User itself
	public Users updateUsernameById(int id, String newUsername, String currentPassword) {
		Optional<Users> optional = userRepository.findById(id);
		
		//System.out.println(currentPassword);
		if(optional.get() != null) {
			Users user = optional.get();
			//System.out.println(user.getPassword());
			if(verifyHash(currentPassword,user.getPassword())) {
				//means user is updating his own account
				user.setUsername(newUsername);
				return userRepository.save(user);
			}
			else {
				System.out.println("Password Mismatch");
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Password Mismatch");
				
			}
		}
		else {
			System.out.println("User id not found");
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User ID not found");
		}
	}
	
	public Users addNewUser(Users newUser) {
		return userRepository.save(newUser);
	}
	
	public void deleteUser(int id) {
		userRepository.deleteById(id);
	}
}
