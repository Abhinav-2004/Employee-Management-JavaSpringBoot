package com.demo.employeeManagement.controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	 private static final Logger logger = LoggerFactory.getLogger(RoleController.class);
	
	//To Retrieve all the roles
	@GetMapping("/role/all")
	public List<Role> getRoles(){
		logger.info("Fetching all roles");
		List<Role> roles = roleService.getRoles();
        logger.debug("Retrieved {} roles", roles.size());
        return roles;
	}
	
	
	//To add a new role
	@PostMapping("/role/add")
	public Role addRole(@RequestBody Role newRole) {
		logger.info("Adding new role: {}", newRole.getName());
		Role savedRole = roleService.addRole(newRole);
        logger.debug("Role added with ID: {}", savedRole.getId());
        return savedRole;
	}
	
	//To update an existing role
	@PostMapping("/role/update/{id}")
	public Role updateRoleById(@PathVariable("id") int id, @RequestBody Role newRole) {
		 logger.info("Updating role with ID: {}", id);
	        Role updatedRole = roleService.updateRoleById(id, newRole);
	        logger.debug("Updated role: {}", updatedRole);
	        return updatedRole;
	}
	
	//To delete and existing role
	@DeleteMapping("/role/delete/{id}")
	public boolean deleteRoleById(@PathVariable("id") int id) {
		logger.info("Deleting role with ID: {}", id);
        boolean deleted = roleService.deleteRoleById(id);
        if (deleted) {
            logger.debug("Role with ID {} successfully deleted", id);
        } else {
            logger.warn("Failed to delete role with ID: {}", id);
        }
        return deleted;
	}
}
