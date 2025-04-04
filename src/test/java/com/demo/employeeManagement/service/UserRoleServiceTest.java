package com.demo.employeeManagement.service;

import com.demo.employeeManagement.dao.RoleRepository;
import com.demo.employeeManagement.dao.UserRepository;
import com.demo.employeeManagement.entities.Role;
import com.demo.employeeManagement.entities.Users;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class UserRoleServiceTest {

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserRoleService userRoleService;

    private Users mockUser;
    private Role mockRole;

    @BeforeEach
    void setUp() {
        mockUser = new Users();
        mockUser.setId(1);
        mockUser.setUsername("testUser");
        mockUser.setRoles(new HashSet<>());

        mockRole = new Role();
        mockRole.setId(100);
        mockRole.setName("Admin");
    }

    @Test
    void testAddRoleToUser_Success() {
        when(userRepository.findById(1)).thenReturn(Optional.of(mockUser));
        when(roleRepository.findById(100)).thenReturn(Optional.of(mockRole));
        when(userRepository.save(mockUser)).thenReturn(mockUser);

        userRoleService.addRoleToUser(1, 100);

        assertTrue(mockUser.getRoles().contains(mockRole));
        verify(userRepository).save(mockUser);
    }

    @Test
    void testAddRoleToUser_UserOrRoleNotFound() {
        when(userRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(NullPointerException.class, () -> userRoleService.addRoleToUser(1, 100));
        verify(userRepository, never()).save(any());
    }

    @Test
    void testDeleteRoleToUser_Success() {
        mockUser.getRoles().add(mockRole); // user has the role initially

        when(userRepository.findById(1)).thenReturn(Optional.of(mockUser));
        when(roleRepository.findById(100)).thenReturn(Optional.of(mockRole));
        when(userRepository.save(mockUser)).thenReturn(mockUser);

        userRoleService.deleteRoleToUser(1, 100);

        assertFalse(mockUser.getRoles().contains(mockRole));
        verify(userRepository).save(mockUser);
    }

    @Test
    void testDeleteRoleToUser_UserOrRoleNotFound() {
        when(userRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(NullPointerException.class, () -> userRoleService.deleteRoleToUser(1, 100));
        verify(userRepository, never()).save(any());
    }
}

