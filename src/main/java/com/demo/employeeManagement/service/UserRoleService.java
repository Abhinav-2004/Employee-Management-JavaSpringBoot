package com.demo.employeeManagement.service;

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
	
	
	public void deleteRoleToUser(int userId,int roleId ) {
		 Optional<Users> optional1 = userRepository.findById(userId);
	        Optional<Role> optional2 = roleRepository.findById(roleId);
	        if(optional1.get()!=null && optional2.get()!=null) {
	        	Users user = optional1.get();
	        	Role role = optional2.get();
	        	user.getRoles().remove(role); // Add the role to the user
	            userRepository.save(user); // Save user (JPA updates role_user table)
	        }
	        else {
	        	throw new NullPointerException("Either of the role or user doesn't exists");
	        }
	}

    public void addRoleToUser(int userId, int roleId) {
        Optional<Users> optional1 = userRepository.findById(userId);
        Optional<Role> optional2 = roleRepository.findById(roleId);
        if(optional1.get()!=null && optional2.get()!=null) {
        	Users user = optional1.get();
        	Role role = optional2.get();
        	user.getRoles().add(role); // Add the role to the user
            userRepository.save(user); // Save user (JPA updates role_user table)
        }
        else {
        	throw new NullPointerException("Either of the role or user doesn't exists");
        }
    }
    
}
