package com.demo.employeeManagement.controller;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.employeeManagement.entities.Employees;
import com.demo.employeeManagement.service.EmployeeService;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@CrossOrigin(origins = "http://localhost:5173") // Allow frontend
@RestController
public class EmployeeController {
	@Autowired
	private EmployeeService employeeService;
	private static final Logger logger =  LoggerFactory.getLogger(EmployeeController.class);
	//Request to Retrieve all the employees in the organisation
	@GetMapping("/employees/all")
	public List<Employees>getAllBooks() {
		logger.info("Fetching all employees");
		List<Employees> empList = employeeService.getAllEmployees();
		return empList;
	}
	//Request to Retrieve by Id 
	@GetMapping("/employees/{id}")
	public Employees getEmployeeById(@PathVariable("id") int id ) {
		logger.info("Fetching employee with ID: {}", id);
		Employees employee = employeeService.findEmployeeById(id);
		return employee;
	}
	
	//Request to update a employee by Id
	@PutMapping("/employees/update/{id}")
	public Employees updateEmployeeById(@PathVariable("id") int id, @RequestBody Employees employee ) {
		//System.out.print(employee);
		logger.info("Updating employee with ID: {}", id);
		Employees emp = employeeService.updateEmployeeById(id, employee);
		return emp;
	}
	//Request to delete a employee By Id
	@DeleteMapping("/employees/delete/{id}")
	public Boolean deleteEmployeeById(@PathVariable("id") int id) {
		logger.info("Deleting employee with ID: {}", id);
		return employeeService.deleteEmployeeById(id);
	}
	//Request to add a employee by Id
	@PostMapping("/employees/add")
	public Employees addEmployee(@RequestBody Employees emp) {
		logger.info("Adding new employee: {}", emp.getName());
		return employeeService.addEmployee(emp);
	}
	//Request to get all employees of a department
	@GetMapping("/employees/department/{department}")
	public List<Employees> getEmployeesByDepartment(@PathVariable("department") String department){
		 logger.info("Fetching employees from department: {}", department);
		return employeeService.findEmployeeByDepartment(department);
	}
	//Request to get all employees by job-title
	@GetMapping("/employees/job/{job}")
	public List<Employees> getEmployeesByJob(@PathVariable("job") String job){
		logger.info("Fetching employees with job title: {}", job);
		return employeeService.findEmployeeByJob(job);
	}
	
	
}
