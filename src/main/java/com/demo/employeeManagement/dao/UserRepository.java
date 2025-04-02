package com.demo.employeeManagement.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.employeeManagement.entities.Users;
import java.util.List;



@Repository
public interface UserRepository extends JpaRepository<Users, Integer>{
	Users findByUsername(String username);

}
