package com.demo.employeeManagement.controller;

import com.demo.employeeManagement.service.UserRoleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserRoleControllerTest {

    @Mock
    private UserRoleService userRoleService;

    @InjectMocks
    private UserRoleController userRoleController;

    private UserRoleUpdateRequest request;

    @BeforeEach
    void setUp() {
        request = new UserRoleUpdateRequest();
        request.setUser_id(1);
        request.setRole_id(2);
    }

    @Test
    void testDeleteUserRole() {
        doNothing().when(userRoleService).deleteRoleToUser(1, 2);

        boolean result = userRoleController.deleteUserRole(request);

        assertTrue(result);
        verify(userRoleService, times(1)).deleteRoleToUser(1, 2);
    }

    @Test
    void testAddUserRole() {
        doNothing().when(userRoleService).addRoleToUser(1, 2);

        String result = userRoleController.addUserRole(request);

        assertNotNull(result);
        assertEquals("user id - 1 added with role id - 2", result);
        verify(userRoleService, times(1)).addRoleToUser(1, 2);
    }
}
