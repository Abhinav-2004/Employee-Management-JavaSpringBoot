package com.demo.employeeManagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
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
	
	//User password can be only updated by only user
	@PostMapping("/users/updatepassword/{id}")
	public Users updateUserPasswordById(@PathVariable("id") int id, @RequestBody PasswordUpdateRequest request){
		return userService.updateUserPasswordById(id, request.getOldPassword(), request.getNewPassword());
	
	}
}
