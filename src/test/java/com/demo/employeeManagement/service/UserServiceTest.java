package com.demo.employeeManagement.service;

import com.demo.employeeManagement.dao.UserRepository;
import com.demo.employeeManagement.entities.Users;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private Users user;

    @BeforeEach
    void setup() {
        user = new Users();
        user.setId(1);
        user.setUsername("test@example.com");
        user.setPassword(userService.generateHash("password123")); // hashed password
    }

    @Test
    void testFindUserByEmail() {
        when(userRepository.findByUsername("test@example.com")).thenReturn(user);
        Users result = userService.findUser("test@example.com");
        assertEquals("test@example.com", result.getUsername());
    }

    @Test
    void testFindUserById_Success() {
        when(userRepository.findById(1)).thenReturn(Optional.of(user));
        Users result = userService.findUserById(1);
        assertNotNull(result);
        assertEquals(1, result.getId());
    }

    @Test
    void testFindUserById_NotFound() {
        when(userRepository.findById(2)).thenReturn(Optional.empty());
        assertThrows(NullPointerException.class, () -> userService.findUserById(2));
    }

    @Test
    void testGenerateAndVerifyHash() {
        String password = "secret";
        String hash = userService.generateHash(password);
        assertTrue(userService.verifyHash(password, hash));
        assertFalse(userService.verifyHash("wrongpassword", hash));
    }

    @Test
    void testUpdateUserPasswordById_Success() {
        when(userRepository.findById(1)).thenReturn(Optional.of(user));
        when(userRepository.save(any(Users.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Users updatedUser = userService.updateUserPasswordById(1, "password123", "newpass456");
        assertNotNull(updatedUser);
        assertTrue(userService.verifyHash("newpass456", updatedUser.getPassword()));
    }

    @Test
    void testUpdateUserPasswordById_WrongOldPassword() {
        when(userRepository.findById(1)).thenReturn(Optional.of(user));
        assertThrows(ResponseStatusException.class,
            () -> userService.updateUserPasswordById(1, "wrongpass", "newpass456"));
    }

    @Test
    void testUpdateUserPasswordById_UserNotFound() {
        when(userRepository.findById(1)).thenReturn(Optional.empty());
        assertThrows(NullPointerException.class,
            () -> userService.updateUserPasswordById(1, "password123", "newpass456"));
    }

    @Test
    void testUpdateUsernameById_Success() {
        when(userRepository.findById(1)).thenReturn(Optional.of(user));
        when(userRepository.save(any(Users.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Users updatedUser = userService.updateUsernameById(1, "newuser@example.com", "password123");
        assertEquals("newuser@example.com", updatedUser.getUsername());
    }

    @Test
    void testUpdateUsernameById_WrongPassword() {
        when(userRepository.findById(1)).thenReturn(Optional.of(user));
        assertThrows(ResponseStatusException.class,
            () -> userService.updateUsernameById(1, "new@example.com", "wrongpassword"));
    }

    @Test
    void testUpdateUsernameById_UserNotFound() {
        when(userRepository.findById(1)).thenReturn(Optional.empty());
        assertThrows(ResponseStatusException.class,
            () -> userService.updateUsernameById(1, "new@example.com", "password123"));
    }

    @Test
    void testAddNewUser() {
        when(userRepository.save(user)).thenReturn(user);
        Users result = userService.addNewUser(user);
        assertEquals(user.getUsername(), result.getUsername());
    }

    @Test
    void testDeleteUser() {
        doNothing().when(userRepository).deleteById(1);
        userService.deleteUser(1);
        verify(userRepository, times(1)).deleteById(1);
    }
}
