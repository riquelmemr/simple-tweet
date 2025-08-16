package com.riquelmemr.simpletweet.service.user.impl;

import com.riquelmemr.simpletweet.enums.RoleEnum;
import com.riquelmemr.simpletweet.exceptions.EntityAlreadyExistsException;
import com.riquelmemr.simpletweet.mapper.UserMapper;
import com.riquelmemr.simpletweet.model.Role;
import com.riquelmemr.simpletweet.model.User;
import com.riquelmemr.simpletweet.repository.RoleRepository;
import com.riquelmemr.simpletweet.repository.UserRepository;
import com.riquelmemr.simpletweet.security.JwtUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private RoleRepository roleRepository;
    @Mock
    private UserMapper userMapper;
    @Mock
    private BCryptPasswordEncoder passwordEncoder;
    @Mock
    private JwtUtils jwtUtils;

    @InjectMocks
    private UserServiceImpl userService;

    private User user;
    private Role basicRole;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setUsername("john");
        user.setPassword("123456");

        basicRole = new Role();
        basicRole.setName(RoleEnum.BASIC.name().toUpperCase());
    }

    @Test
    void shouldCreateUserSuccessfully() {
        // Arrange
        when(userRepository.findByUsername("john")).thenReturn(Optional.empty());
        when(roleRepository.findByName(RoleEnum.BASIC.name().toUpperCase())).thenReturn(basicRole);
        when(passwordEncoder.encode("123456")).thenReturn("encodedPassword");

        // Act
        userService.create(user);

        // Assert
        assertEquals("encodedPassword", user.getPassword());
        assertTrue(user.getRoles().contains(basicRole));
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void shouldThrowExceptionWhenUserAlreadyExists() {
        // Arrange
        when(userRepository.findByUsername("john")).thenReturn(Optional.of(new User()));

        // Act & Assert
        EntityAlreadyExistsException exception = assertThrows(
                EntityAlreadyExistsException.class,
                () -> userService.create(user)
        );

        assertEquals("User already exists by username.", exception.getMessage());
        verify(userRepository, never()).save(any());
    }
}