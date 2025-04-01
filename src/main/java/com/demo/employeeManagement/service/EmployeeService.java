package com.demo.employeeManagement.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.employeeManagement.dao.EmployeeRepository;
import com.demo.employeeManagement.entities.Employees;

@Service
public class EmployeeService {

   
	@Autowired
	private EmployeeRepository employeeRepository;


	
	
	public List<Employees> getAllEmployees(){
		return employeeRepository.findAll();
	}
	
	public Employees findEmployeeById(int id) {
		Optional<Employees>optional= employeeRepository.findById(id);
		return optional.get();
	}
	public Employees updateEmployeeById(int id, Employees employee) {
		Optional<Employees> optional = employeeRepository.findById(id);
		Employees oldEmployee = optional.get();
		oldEmployee.setId(employee.getId());
		oldEmployee.setName(employee.getName());
		oldEmployee.setJoining_date(employee.getJoining_date());
		oldEmployee.setAge(employee.getAge());
		oldEmployee.setGroup_of_employee(employee.getGroup_of_employee());
		
		return employeeRepository.save(oldEmployee);
	}
		
	public Boolean deleteEmployeeById(int id) {
		employeeRepository.deleteById(id);
		return true;
	}
	
	public Employees addEmployee(Employees emp) {
		return employeeRepository.save(emp);
	}
}
