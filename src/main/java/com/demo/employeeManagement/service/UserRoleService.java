package com.demo.employeeManagement.service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.employeeManagement.dao.RoleRepository;
import com.demo.employeeManagement.dao.UserRepository;
import com.demo.employeeManagement.entities.Role;
import com.demo.employeeManagement.entities.Users;

import jakarta.transaction.Transactional;

@Service
public class UserRoleService {
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private UserRepository userRepository;
	 private static final Logger logger = LoggerFactory.getLogger(UserRoleService.class);
	
	public void deleteRoleToUser(int userId,int roleId ) {
logger.info("Attempting to remove role (ID: {}) from user (ID: {})", roleId, userId);
        
        Optional<Users> optionalUser = userRepository.findById(userId);
        Optional<Role> optionalRole = roleRepository.findById(roleId);

        if (optionalUser.isPresent() && optionalRole.isPresent()) {
            Users user = optionalUser.get();
            Role role = optionalRole.get();
            user.getRoles().remove(role);
            userRepository.save(user);
            logger.debug("Successfully removed role ID {} from user ID {}", roleId, userId);
        } else {
            logger.error("Failed to remove role from user - User ID: {}, Role ID: {}", userId, roleId);
            throw new NullPointerException("Either the user or role does not exist");
        }
	}

    public void addRoleToUser(int userId, int roleId) {
    	logger.info("Attempting to add role (ID: {}) to user (ID: {})", roleId, userId);

        Optional<Users> optionalUser = userRepository.findById(userId);
        Optional<Role> optionalRole = roleRepository.findById(roleId);

        if (optionalUser.isPresent() && optionalRole.isPresent()) {
            Users user = optionalUser.get();
            Role role = optionalRole.get();
            user.getRoles().add(role);
            userRepository.save(user);
            logger.debug("Successfully added role ID {} to user ID {}", roleId, userId);
        } else {
            logger.error("Failed to add role to user - User ID: {}, Role ID: {}", userId, roleId);
            throw new NullPointerException("Either the user or role does not exist");
        }
    }
    
}
