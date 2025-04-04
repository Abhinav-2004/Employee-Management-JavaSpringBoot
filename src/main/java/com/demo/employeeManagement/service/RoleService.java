package com.demo.employeeManagement.service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.employeeManagement.dao.RoleRepository;
import com.demo.employeeManagement.entities.Role;

@Service
public class RoleService {
	@Autowired
	private RoleRepository roleRepository;
	private static final Logger logger = LoggerFactory.getLogger(RoleService.class);
	public Role addRole(Role newRole) {
		logger.info("Adding new role: {}", newRole.getName());
        Role savedRole = roleRepository.save(newRole);
        logger.debug("Role saved with ID: {}", savedRole.getId());
        return savedRole;
	}
	public Role updateRoleById(int id, Role newRole) {
		 logger.info("Updating role with ID: {}", id);
	        Optional<Role> optional = roleRepository.findById(id);
	        if (optional.isPresent()) {
	            newRole.setId(id); // ensure ID remains the same
	            Role updatedRole = roleRepository.save(newRole);
	            logger.debug("Role updated: {}", updatedRole);
	            return updatedRole;
	        } else {
	            logger.warn("No role found with ID: {}", id);
	            throw new NullPointerException("No such role found with given ID");
	        }
	}
	
	public boolean deleteRoleById(int id) {
		logger.info("Deleting role with ID: {}", id);
        Optional<Role> optional = roleRepository.findById(id);
        if (optional.isPresent()) {
            Role deletedRole = optional.get();
            roleRepository.delete(deletedRole);
            logger.debug("Role deleted: {}", deletedRole);
            return true;
        } else {
            logger.warn("Attempted to delete non-existent role with ID: {}", id);
            throw new NullPointerException("No such role found with given ID");
        }
		
	}
	
	public List<Role> getRoles(){
		logger.info("Fetching all roles");
        List<Role> roles = roleRepository.findAll();
        logger.debug("Total roles fetched: {}", roles.size());
        return roles;
	}
}
