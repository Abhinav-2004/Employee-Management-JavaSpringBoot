package com.demo.employeeManagement.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.employeeManagement.entities.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer>{
	
}
