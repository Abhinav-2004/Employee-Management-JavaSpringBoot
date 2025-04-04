package com.demo.employeeManagement.controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import com.demo.employeeManagement.entities.Users;
import com.demo.employeeManagement.service.UserService;
@CrossOrigin(origins = "http://localhost:5173")
@RestController
public class UserController {
	@Autowired
	private UserService userService;
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	@GetMapping("/users/{email}")
	public Users getUsers(@PathVariable("email") String email){
		logger.info("Fetching user with email: {}", email);
        Users user = userService.findUser(email);
        if (user != null) {
            logger.debug("Found user with ID: {}", user.getId());
        } else {
            logger.warn("User with email {} not found", email);
        }
        return user;
	}
	
	//User password can be only updated by only user by matching both previous and present password
	@PostMapping("/users/updatepassword/{id}")
	public Users updateUserPasswordById(@PathVariable("id") int id, @RequestBody PasswordUpdateRequest request){
		 logger.info("Updating password for user ID: {}", id);
	        return userService.updateUserPasswordById(id, request.getOldPassword(), request.getNewPassword());
	
	}
	//Username update by user
	@PostMapping("/users/updateusername/{id}")
	public Users updateUsernameById(@PathVariable("id") int id, @RequestBody UsernameUpdateRequest request){
	       logger.info("Updating username for user ID: {}", id);
		return userService.updateUsernameById(id, request.getNewUsername(), request.getCurrentPassword());
	
	}
	//Add a new user to user table only done by admin
	@PostMapping("/users/add")
	public Users addNewUser(@RequestBody Users newUser) {
		logger.info("Adding new user with username: {}", newUser.getUsername());
		return userService.addNewUser(newUser);
	}
	//Delete a user by id in table only done by admin
	@DeleteMapping("/users/delete/{id}")
	public boolean deleteUser(@PathVariable("id") int id) {
		logger.info("Deleting user with ID: {}", id);
        userService.deleteUser(id);
        logger.debug("User with ID {} deleted successfully", id);
        return true;
	}
}


 
