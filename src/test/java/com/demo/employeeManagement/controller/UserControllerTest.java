package com.demo.employeeManagement.controller;

import com.demo.employeeManagement.entities.Users;
import com.demo.employeeManagement.service.UserService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    private Users user;

    @BeforeEach
    void setUp() {
        user = new Users();
        user.setId(1);
        user.setUsername("john.doe@example.com");
        user.setPassword("hashedPassword123");
    }

    @Test
    void testGetUsers() {
        when(userService.findUser("john.doe@example.com")).thenReturn(user);

        Users result = userController.getUsers("john.doe@example.com");

        assertNotNull(result);
        assertEquals("john.doe@example.com", result.getUsername());
        verify(userService, times(1)).findUser("john.doe@example.com");
    }

    @Test
    void testUpdateUserPasswordById() {
        PasswordUpdateRequest request = new PasswordUpdateRequest();
        request.setOldPassword("oldPass");
        request.setNewPassword("newPass");

        when(userService.updateUserPasswordById(1, "oldPass", "newPass")).thenReturn(user);

        Users result = userController.updateUserPasswordById(1, request);

        assertNotNull(result);
        assertEquals("john.doe@example.com", result.getUsername());
        verify(userService).updateUserPasswordById(1, "oldPass", "newPass");
    }

    @Test
    void testUpdateUsernameById() {
        UsernameUpdateRequest request = new UsernameUpdateRequest();
        request.setNewUsername("new.doe@example.com");
        request.setCurrentPassword("password");

        user.setUsername("new.doe@example.com");

        when(userService.updateUsernameById(1, "new.doe@example.com", "password")).thenReturn(user);

        Users result = userController.updateUsernameById(1, request);

        assertNotNull(result);
        assertEquals("new.doe@example.com", result.getUsername());
        verify(userService).updateUsernameById(1, "new.doe@example.com", "password");
    }

    @Test
    void testAddNewUser() {
        when(userService.addNewUser(user)).thenReturn(user);

        Users result = userController.addNewUser(user);

        assertNotNull(result);
        assertEquals("john.doe@example.com", result.getUsername());
        verify(userService, times(1)).addNewUser(user);
    }

    @Test
    void testDeleteUser() {
        doNothing().when(userService).deleteUser(1);

        boolean result = userController.deleteUser(1);

        assertTrue(result);
        verify(userService, times(1)).deleteUser(1);
    }
}

