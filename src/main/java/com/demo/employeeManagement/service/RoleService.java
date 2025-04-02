package com.demo.employeeManagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.employeeManagement.dao.RoleRepository;
import com.demo.employeeManagement.entities.Role;

@Service
public class RoleService {
	@Autowired
	private RoleRepository roleRepository;
	
	public Role addNewRole(int id, String name) {
		Role newRole = new Role();
		newRole.setId(id);
		newRole.setName(name);
		return roleRepository.save(newRole);
	}
}
