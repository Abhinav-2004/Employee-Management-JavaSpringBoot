package com.demo.employeeManagement.controller;

import com.demo.employeeManagement.entities.Role;
import com.demo.employeeManagement.service.RoleService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RoleControllerTest {

    @Mock
    private RoleService roleService;

    @InjectMocks
    private RoleController roleController;

    private Role role1;
    private Role role2;

    @BeforeEach
    void setUp() {
        role1 = new Role();
        role1.setId(1);
        role1.setName("ADMIN");

        role2 = new Role();
        role2.setId(2);
        role2.setName("USER");
    }

    @Test
    void testGetRoles() {
        List<Role> roles = Arrays.asList(role1, role2);
        when(roleService.getRoles()).thenReturn(roles);

        List<Role> result = roleController.getRoles();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("ADMIN", result.get(0).getName());
        verify(roleService, times(1)).getRoles();
    }

    @Test
    void testAddRole() {
        when(roleService.addRole(role1)).thenReturn(role1);

        Role result = roleController.addRole(role1);

        assertNotNull(result);
        assertEquals("ADMIN", result.getName());
        verify(roleService, times(1)).addRole(role1);
    }

    @Test
    void testUpdateRoleById() {
        when(roleService.updateRoleById(1, role1)).thenReturn(role1);

        Role result = roleController.updateRoleById(1, role1);

        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals("ADMIN", result.getName());
        verify(roleService, times(1)).updateRoleById(1, role1);
    }

    @Test
    void testDeleteRoleById() {
        when(roleService.deleteRoleById(1)).thenReturn(true);

        boolean result = roleController.deleteRoleById(1);

        assertTrue(result);
        verify(roleService, times(1)).deleteRoleById(1);
    }
}
