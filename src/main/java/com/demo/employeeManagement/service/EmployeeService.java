package com.demo.employeeManagement.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	private static final Logger logger = LoggerFactory.getLogger(EmployeeService.class);

	
	
	public List<Employees> getAllEmployees(){
		logger.info("Fetching all employees");
        List<Employees> employees = employeeRepository.findAll();
        logger.debug("Total employees fetched: {}", employees.size());
        return employees;
	}
	
	public Employees findEmployeeById(int id) {
		 logger.info("Fetching employee with ID: {}", id);
	        Optional<Employees> optional = employeeRepository.findById(id);
	        if (optional.isPresent()) {
	            logger.debug("Employee found: {}", optional.get());
	            return optional.get();
	        } else {
	            logger.warn("Employee with ID {} not found", id);
	            return null;
	        }
	}
	public List<Employees> findEmployeeByDepartment(String department) {
		logger.info("Fetching employees in department: {}", department);
        List<Employees> list = employeeRepository.findByDepartment(department);
        logger.debug("Total employees in department '{}': {}", department, list.size());
        return list;
	}
	
	public Employees updateEmployeeById(int id, Employees employee) {
		logger.info("Updating employee with ID: {}", id);
        Optional<Employees> optional = employeeRepository.findById(id);
        if (optional.isPresent()) {
            Employees oldEmployee = optional.get();
            oldEmployee.setId(employee.getId());
            oldEmployee.setName(employee.getName());
            oldEmployee.setJoining_date(employee.getJoining_date());
            oldEmployee.setAge(employee.getAge());
            oldEmployee.setDepartment(employee.getDepartment());
            oldEmployee.setJob(employee.getJob());

            Employees updatedEmployee = employeeRepository.save(oldEmployee);
            logger.debug("Updated employee: {}", updatedEmployee);
            return updatedEmployee;
        } else {
            logger.warn("Cannot update, employee with ID {} not found", id);
            return null;
        }
	}
		
	public Boolean deleteEmployeeById(int id) {
		logger.info("Deleting employee with ID: {}", id);
        employeeRepository.deleteById(id);
        logger.debug("Employee with ID {} deleted", id);
        return true;
	}
	
	public Employees addEmployee(Employees emp) {
		logger.info("Adding new employee: {}", emp.getName());
        Employees savedEmployee = employeeRepository.save(emp);
        logger.debug("Employee added with ID: {}", savedEmployee.getId());
        return savedEmployee;
	}
	
	public List<Employees> findEmployeeByJob(String job) {
		logger.info("Fetching employees with job title: {}", job);
        List<Employees> list = employeeRepository.findByJob(job);
        logger.debug("Total employees with job '{}': {}", job, list.size());
        return list;
	}
	
}
