package com.demo.employeeManagement.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.demo.employeeManagement.dao.RoleRepository;
import com.demo.employeeManagement.service.UserRoleService;

@RestController
public class UserRoleController {

    
	@Autowired
	private UserRoleService userRoleService;

	//To delete a user-role relationship
	@DeleteMapping("/user-role/delete")
	public boolean deleteUserRole(@RequestBody UserRoleUpdateRequest request) {
		userRoleService.deleteRoleToUser(request.getUser_id(), request.getRole_id());
		return true;
	}
	
	//To add a new user-role relationship
	@PostMapping("/user-role/add")
	public String addUserRole(@RequestBody UserRoleUpdateRequest request) {
		userRoleService.addRoleToUser(request.getUser_id(), request.getRole_id());
		return "user id - "+request.getUser_id()+" added with role id - "+request.getRole_id();
	}
}
