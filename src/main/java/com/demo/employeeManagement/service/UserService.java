package com.demo.employeeManagement.service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	private static final Logger logger = LoggerFactory.getLogger(UserService.class);
	private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);
	
	public Users findUser(String email) {
		logger.info("Fetching user by email: {}", email);
        return userRepository.findByUsername(email);
	}
	public  Users findUserById(int id ) {
		logger.info("Fetching user by ID: {}", id);
        Optional<Users> optional = userRepository.findById(id);
        if (optional.isEmpty()) {
            logger.error("User not found with ID: {}", id);
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
		logger.info("Updating password for user ID: {}", id);
        Optional<Users> optional = userRepository.findById(id);

        if (optional.isEmpty()) {
            logger.error("User not found for password update with ID: {}", id);
            throw new NullPointerException("User not found with the given id");
        }

        Users user = optional.get();
        if (verifyHash(oldPassword, user.getPassword())) {
            user.setPassword(generateHash(newPassword));
            logger.debug("Password updated successfully for user ID: {}", id);
            return userRepository.save(user);
        } else {
            logger.warn("Password mismatch for user ID: {}", id);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Password Mismatch");
        }
		
	}
	//Update Username By Id can only be done by User itself
	public Users updateUsernameById(int id, String newUsername, String currentPassword) {
		logger.info("Updating username for user ID: {}", id);
        Optional<Users> optional = userRepository.findById(id);

        if (optional.isEmpty()) {
            logger.error("User not found for username update with ID: {}", id);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User ID not found");
        }

        Users user = optional.get();
        if (verifyHash(currentPassword, user.getPassword())) {
            user.setUsername(newUsername);
            logger.debug("Username updated successfully for user ID: {}", id);
            return userRepository.save(user);
        } else {
            logger.warn("Password mismatch while updating username for user ID: {}", id);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Password Mismatch");
        }
	}

	
	
	public Users addNewUser(Users newUser) {
		 logger.info("Adding new user: {}", newUser.getUsername());
	        return userRepository.save(newUser);
	}
	
	public void deleteUser(int id) {
		logger.info("Deleting user with ID: {}", id);
        userRepository.deleteById(id);
	}
}
