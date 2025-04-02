package com.demo.employeeManagement.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.employeeManagement.entities.Employees;

@Repository
public interface EmployeeRepository extends JpaRepository<Employees, Integer>{
	List<Employees> findByDepartment(String department);
	List<Employees> findByJob(String job);
}
