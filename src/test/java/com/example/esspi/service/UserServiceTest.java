package com.example.esspi.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.example.esspi.model.User;
import com.example.esspi.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Test
    void testFindByUsername() {
        // Given
        String username = "testUser";
        User mockUser = new User();
        mockUser.setUsername(username);
        when(userRepository.findByUsername(username)).thenReturn(mockUser);

        // When
        User result = userService.findByUsername(username);

        // Then
        assertNotNull(result);
        assertEquals(username, result.getUsername());
    }

    @Test
    @Rollback(true)
    void testCreateUser() {
        // Given
        String username = "newUser";
        String password = "password123";

        // When
        userService.createUser(username, password);

        // Then
        verify(userRepository, times(1)).createUser(username, password);
    }
}
