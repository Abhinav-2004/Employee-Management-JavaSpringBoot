package com.demo.employeeManagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.employeeManagement.entities.Role;
import com.demo.employeeManagement.service.RoleService;

@RestController
public class RoleController {
	@Autowired
	private RoleService roleService;
	//Only Admin can modify Role but everyone can view role codes
	
	
	//To Retrieve all the roles
	@GetMapping("/role/all")
	public List<Role> getRoles(){
		return roleService.getRoles();
	}
	
	
	//To add a new role
	@PostMapping("/role/add")
	public Role addRole(@RequestBody Role newRole) {
		return roleService.addRole(newRole);
	}
	
	//To update an existing role
	@PostMapping("/role/update/{id}")
	public Role updateRoleById(@PathVariable("id") int id, @RequestBody Role newRole) {
		return roleService.updateRoleById(id, newRole);
	}
	
	//To delete and existing role
	@DeleteMapping("/role/delete/{id}")
	public boolean deleteRoleById(@PathVariable("id") int id) {
		return roleService.deleteRoleById(id);
	}
}
