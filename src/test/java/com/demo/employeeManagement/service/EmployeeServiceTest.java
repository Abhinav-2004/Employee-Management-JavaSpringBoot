package com.demo.employeeManagement.service;

import com.demo.employeeManagement.dao.EmployeeRepository;
import com.demo.employeeManagement.entities.Employees;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeService employeeService;

    private Employees emp1;
    private Employees emp2;

    @BeforeEach
    void setUp() {
        emp1 = new Employees();
        emp1.setId(1);
        emp1.setName("Alice");
        emp1.setAge(30);
        emp1.setDepartment("HR");
        emp1.setJob("Recruiter");
        emp1.setJoining_date(Date.valueOf("2020-01-01"));

        emp2 = new Employees();
        emp2.setId(2);
        emp2.setName("Bob");
        emp2.setAge(28);
        emp2.setDepartment("IT");
        emp2.setJob("Developer");
        emp2.setJoining_date(Date.valueOf("2021-06-15"));
    }

    @Test
    void testGetAllEmployees() {
        when(employeeRepository.findAll()).thenReturn(Arrays.asList(emp1, emp2));

        List<Employees> result = employeeService.getAllEmployees();

        assertEquals(2, result.size());
        verify(employeeRepository, times(1)).findAll();
    }

    @Test
    void testFindEmployeeById_Found() {
        when(employeeRepository.findById(1)).thenReturn(Optional.of(emp1));

        Employees result = employeeService.findEmployeeById(1);

        assertNotNull(result);
        assertEquals("Alice", result.getName());
        verify(employeeRepository, times(1)).findById(1);
    }

    @Test
    void testFindEmployeeById_NotFound() {
        when(employeeRepository.findById(99)).thenReturn(Optional.empty());

        Employees result = employeeService.findEmployeeById(99);

        assertNull(result);
        verify(employeeRepository, times(1)).findById(99);
    }

    @Test
    void testFindEmployeeByDepartment() {
        when(employeeRepository.findByDepartment("IT")).thenReturn(List.of(emp2));

        List<Employees> result = employeeService.findEmployeeByDepartment("IT");

        assertEquals(1, result.size());
        assertEquals("Bob", result.get(0).getName());
        verify(employeeRepository, times(1)).findByDepartment("IT");
    }

    @Test
    void testFindEmployeeByJob() {
        when(employeeRepository.findByJob("Developer")).thenReturn(List.of(emp2));

        List<Employees> result = employeeService.findEmployeeByJob("Developer");

        assertEquals(1, result.size());
        assertEquals("Bob", result.get(0).getName());
        verify(employeeRepository, times(1)).findByJob("Developer");
    }

    @Test
    void testUpdateEmployeeById_Found() {
        when(employeeRepository.findById(1)).thenReturn(Optional.of(emp1));
        when(employeeRepository.save(any(Employees.class))).thenReturn(emp1);

        Employees updated = new Employees();
        updated.setId(1);
        updated.setName("Alice Updated");
        updated.setAge(32);
        updated.setDepartment("HR");
        updated.setJob("HR Manager");
        updated.setJoining_date(Date.valueOf("2020-01-01"));

        Employees result = employeeService.updateEmployeeById(1, updated);

        assertNotNull(result);
        assertEquals("Alice Updated", result.getName());
        verify(employeeRepository, times(1)).findById(1);
        verify(employeeRepository, times(1)).save(any(Employees.class));
    }

    @Test
    void testUpdateEmployeeById_NotFound() {
        when(employeeRepository.findById(99)).thenReturn(Optional.empty());

        Employees result = employeeService.updateEmployeeById(99, emp1);

        assertNull(result);
        verify(employeeRepository, times(1)).findById(99);
    }

    @Test
    void testDeleteEmployeeById() {
        doNothing().when(employeeRepository).deleteById(1);

        Boolean result = employeeService.deleteEmployeeById(1);

        assertTrue(result);
        verify(employeeRepository, times(1)).deleteById(1);
    }

    @Test
    void testAddEmployee() {
        when(employeeRepository.save(emp1)).thenReturn(emp1);

        Employees result = employeeService.addEmployee(emp1);

        assertNotNull(result);
        assertEquals("Alice", result.getName());
        verify(employeeRepository, times(1)).save(emp1);
    }
}
