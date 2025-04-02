package com.demo.employeeManagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
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
	
	//User password can be only updated by only user by matching both previous and present password
	@PostMapping("/users/updatepassword/{id}")
	public Users updateUserPasswordById(@PathVariable("id") int id, @RequestBody PasswordUpdateRequest request){
		return userService.updateUserPasswordById(id, request.getOldPassword(), request.getNewPassword());
	
	}
	//Username update by user
	@PostMapping("/users/updateusername/{id}")
	public Users updateUsernameById(@PathVariable("id") int id, @RequestBody UsernameUpdateRequest request){
		return userService.updateUsernameById(id, request.getNewUsername(), request.getCurrentPassword());
	
	}
	//Add a new user to user table only done by admin
	@PostMapping("/users/add")
	public Users addNewUser(@RequestBody Users newUser) {
		return userService.addNewUser(newUser);
	}
	//Delete a user by id in table only done by admin
	@DeleteMapping("/users/delete/{id}")
	public boolean deleteUser(@PathVariable("id") int id) {
		userService.deleteUser(id);
		return true;
	}
}


 
