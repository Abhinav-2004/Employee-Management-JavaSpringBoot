package com.demo.employeeManagement.service;

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
	
	public Role addRole(Role newRole) {
		return roleRepository.save(newRole);
	}
	public Role updateRoleById(int id, Role newRole) {
		Optional <Role> optional = roleRepository.findById(id);
		if(optional.get()!=null) {
			
			return roleRepository.save(newRole);
		}
		else {
			throw new NullPointerException("No such user found with given ID");
		}
	}
	
	public boolean deleteRoleById(int id) {
		Optional <Role> optional = roleRepository.findById(id);
		if(optional.get()!=null) {
			
			Role deletedRole = optional.get();
			roleRepository.delete(deletedRole);
			return true;
		}
		else {
			throw new NullPointerException("No such user found with given ID");
		}
		
	}
	
	public List<Role> getRoles(){
		return roleRepository.findAll();
	}
}
