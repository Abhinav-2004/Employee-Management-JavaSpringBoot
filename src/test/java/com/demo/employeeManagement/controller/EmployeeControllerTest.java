package com.demo.employeeManagement.controller;



import com.demo.employeeManagement.entities.Employees;
import com.demo.employeeManagement.service.EmployeeService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmployeeControllerTest {

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private EmployeeController employeeController;

    private Employees emp1;
    private Employees emp2;

    @BeforeEach
    void setUp() {
        emp1 = new Employees();
        emp1.setId(1);
        emp1.setName("John Doe");
        emp1.setAge(30);
        emp1.setJoining_date(Date.valueOf("2020-01-15"));
        emp1.setDepartment("HR");
        emp1.setJob("Manager");

        emp2 = new Employees();
        emp2.setId(2);
        emp2.setName("Jane Smith");
        emp2.setAge(25);
        emp2.setJoining_date(Date.valueOf("2021-05-10"));
        emp2.setDepartment("IT");
        emp2.setJob("Developer");
    }

    @Test
    void testGetAllEmployees() {
        when(employeeService.getAllEmployees()).thenReturn(Arrays.asList(emp1, emp2));

        List<Employees> result = employeeController.getAllBooks();

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(employeeService, times(1)).getAllEmployees();
    }

    @Test
    void testGetEmployeeById() {
        when(employeeService.findEmployeeById(1)).thenReturn(emp1);

        Employees result = employeeController.getEmployeeById(1);

        assertNotNull(result);
        assertEquals("John Doe", result.getName());
        verify(employeeService, times(1)).findEmployeeById(1);
    }

    @Test
    void testAddEmployee() {
        when(employeeService.addEmployee(emp1)).thenReturn(emp1);

        Employees result = employeeController.addEmployee(emp1);

        assertNotNull(result);
        assertEquals("John Doe", result.getName());
        verify(employeeService, times(1)).addEmployee(emp1);
    }

    @Test
    void testUpdateEmployeeById() {
        when(employeeService.updateEmployeeById(1, emp1)).thenReturn(emp1);

        Employees result = employeeController.updateEmployeeById(1, emp1);

        assertNotNull(result);
        assertEquals(1, result.getId());
        verify(employeeService, times(1)).updateEmployeeById(1, emp1);
    }

    @Test
    void testDeleteEmployeeById() {
        when(employeeService.deleteEmployeeById(1)).thenReturn(true);

        Boolean result = employeeController.deleteEmployeeById(1);

        assertTrue(result);
        verify(employeeService, times(1)).deleteEmployeeById(1);
    }

    @Test
    void testGetEmployeesByDepartment() {
        when(employeeService.findEmployeeByDepartment("HR")).thenReturn(Arrays.asList(emp1));

        List<Employees> result = employeeController.getEmployeesByDepartment("HR");

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(employeeService, times(1)).findEmployeeByDepartment("HR");
    }

    
}

