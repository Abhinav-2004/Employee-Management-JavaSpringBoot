package com.demo.employeeManagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.employeeManagement.entities.Employees;
import com.demo.employeeManagement.service.EmployeeService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@RestController

public class EmployeeController {
	@Autowired
	private EmployeeService employeeService;
	
	//Request to Retrieve all the employees in the organisation
	@GetMapping("/employees/all")
	public List<Employees>getAllBooks() {
		List<Employees> empList = employeeService.getAllEmployees();
		return empList;
	}
	
	//Request to Retrieve by Id 
	@GetMapping("/employees/{id}")
	public Employees getEmployeeById(@PathVariable("id") int id ) {
		Employees employee = employeeService.findEmployeeById(id);
		return employee;
		
	}
	
	//Request to update a employee by Id
	@PutMapping("/employees/update/{id}")
	public Employees updateEmployeeById(@PathVariable("id") int id, @RequestBody Employees employee ) {
		//System.out.print(employee);
		Employees emp = employeeService.updateEmployeeById(id, employee);
		return emp;
		
	}
	//Request to delete a employee By Id
	@DeleteMapping("/employees/delete/{id}")
	public Boolean deleteEmployeeById(@PathVariable("id") int id) {
		
		return employeeService.deleteEmployeeById(id);
	}
	//Request to add a employee by Id
	@PostMapping("/employees/add")
	public Employees addEmployee(@RequestBody Employees emp) {
		return employeeService.addEmployee(emp);
	}
	
	
}
