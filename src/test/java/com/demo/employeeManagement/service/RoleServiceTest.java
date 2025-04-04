package com.demo.employeeManagement.service;

import com.demo.employeeManagement.dao.RoleRepository;
import com.demo.employeeManagement.entities.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class RoleServiceTest {

    @Mock
    private RoleRepository roleRepository;

    @InjectMocks
    private RoleService roleService;

    private Role role1;
    private Role role2;

    @BeforeEach
    void setUp() {
        role1 = new Role();
        role1.setId(1);
        role1.setName("Admin");

        role2 = new Role();
        role2.setId(2);
        role2.setName("User");
    }

    @Test
    void testAddRole() {
        when(roleRepository.save(role1)).thenReturn(role1);

        Role saved = roleService.addRole(role1);

        assertNotNull(saved);
        assertEquals("Admin", saved.getName());
        verify(roleRepository, times(1)).save(role1);
    }

    @Test
    void testUpdateRoleById_Found() {
        when(roleRepository.findById(1)).thenReturn(Optional.of(role1));
        when(roleRepository.save(any(Role.class))).thenReturn(role1);

        Role updatedRole = new Role();
        updatedRole.setName("SuperAdmin");

        Role result = roleService.updateRoleById(1, updatedRole);

        assertNotNull(result);
        assertEquals(1, result.getId());
        verify(roleRepository, times(1)).findById(1);
        verify(roleRepository, times(1)).save(updatedRole);
    }

    @Test
    void testUpdateRoleById_NotFound() {
        when(roleRepository.findById(99)).thenReturn(Optional.empty());

        Role updatedRole = new Role();
        updatedRole.setName("Ghost");

        assertThrows(NullPointerException.class, () -> roleService.updateRoleById(99, updatedRole));

        verify(roleRepository, times(1)).findById(99);
        verify(roleRepository, times(0)).save(any(Role.class));
    }

    @Test
    void testDeleteRoleById_Found() {
        when(roleRepository.findById(2)).thenReturn(Optional.of(role2));
        doNothing().when(roleRepository).delete(role2);

        boolean result = roleService.deleteRoleById(2);

        assertTrue(result);
        verify(roleRepository, times(1)).findById(2);
        verify(roleRepository, times(1)).delete(role2);
    }

    @Test
    void testDeleteRoleById_NotFound() {
        when(roleRepository.findById(100)).thenReturn(Optional.empty());

        assertThrows(NullPointerException.class, () -> roleService.deleteRoleById(100));

        verify(roleRepository, times(1)).findById(100);
        verify(roleRepository, times(0)).delete(any(Role.class));
    }

    @Test
    void testGetRoles() {
        when(roleRepository.findAll()).thenReturn(Arrays.asList(role1, role2));

        List<Role> roles = roleService.getRoles();

        assertEquals(2, roles.size());
        verify(roleRepository, times(1)).findAll();
    }
}
