package com.riquelmemr.simpletweet.service.user.impl;

import com.riquelmemr.simpletweet.dto.request.UpdateUserRequest;
import com.riquelmemr.simpletweet.enums.RoleEnum;
import com.riquelmemr.simpletweet.exceptions.EntityAlreadyExistsException;
import com.riquelmemr.simpletweet.exceptions.EntityNotFoundException;
import com.riquelmemr.simpletweet.exceptions.ResourceNotAllowedException;
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
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import java.util.List;
import java.util.Optional;
import java.util.Set;

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

    @Spy
    @InjectMocks
    private UserServiceImpl userService;

    private User user;
    private User requester;
    private User owner;
    private Role basicRole;
    private JwtAuthenticationToken token;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setUsername("john");
        user.setPassword("123456");

        requester = new User();
        requester.setPk(1L);
        requester.setUsername("requester");

        owner = new User();
        owner.setPk(2L);
        owner.setUsername("owner");

        token = mock(JwtAuthenticationToken.class);

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

    @Test
    void shouldAllowOwnerToUpdateHimself() {
        // Arrange
        requester.setPk(2L);
        requester.setRoles(Set.of(basicRole));

        doReturn(requester).when(userService).extractUserFromToken(token);
        when(userRepository.findById(2L)).thenReturn(Optional.of(owner));

        UpdateUserRequest request = new UpdateUserRequest(null, null, "New name", null);

        // Act
        User userUpdated = userService.update(2L, request, token);

        // Assert
        verify(userRepository).save(owner);
        assertEquals(owner, userUpdated);
    }

    @Test
    void shouldAllowAdminToUpdateOtherUser() {
        // Arrange
        requester.setRoles(Set.of(new Role(RoleEnum.ADMIN.name())));

        doReturn(requester).when(userService).extractUserFromToken(token);
        when(userRepository.findById(2L)).thenReturn(Optional.of(owner));

        UpdateUserRequest request = new UpdateUserRequest(null, null, "Admin Updated Name", null);

        // Act
        User userUpdated = userService.update(2L, request, token);

        // Assert
        verify(userRepository).save(owner);
        assertEquals(owner, userUpdated);
    }


    @Test
    void shouldThrowExceptionWhenUnauthorizedUserTriesToUpdateOtherUser() {
        // Arrange
        requester.setRoles(Set.of(new Role(RoleEnum.BASIC.name())));

        doReturn(requester).when(userService).extractUserFromToken(token);
        when(userRepository.findById(2L)).thenReturn(java.util.Optional.of(owner));

        UpdateUserRequest request = new UpdateUserRequest(null, null, "Should not pass", null);

        // Act & Assert
        assertThrows(ResourceNotAllowedException.class,
                () -> userService.update(2L, request, token));

        verify(userRepository, never()).save(any());
    }

    @Test
    void shouldFindUserByUsernameWhenExists() {
        // Arrange
        User user = new User();
        user.setUsername("john");

        when(userRepository.findByUsername("john"))
                .thenReturn(Optional.of(user));

        // Act
        User found = userService.findByUsername("john");

        // Assert
        assertNotNull(found);
        assertEquals("john", found.getUsername());
    }

    @Test
    void shouldReturnNullWhenUserNotFoundByUsername() {
        // Arrange
        when(userRepository.findByUsername("john"))
                .thenReturn(Optional.empty());

        // Act
        User found = userService.findByUsername("john");

        // Assert
        assertNull(found);
    }

    @Test
    void shouldReturnAllUsers() {
        // Arrange
        User u1 = new User();
        u1.setUsername("alice");
        User u2 = new User();
        u2.setUsername("bob");

        when(userRepository.findAll())
                .thenReturn(List.of(u1, u2));

        // Act
        List<User> users = userService.findAll();

        // Assert
        assertEquals(2, users.size());
        assertEquals("alice", users.get(0).getUsername());
        assertEquals("bob", users.get(1).getUsername());
    }

    @Test
    void shouldFindUserByIdWhenExists() {
        // Arrange
        User user = new User();
        user.setPk(1L);

        when(userRepository.findById(1L))
                .thenReturn(Optional.of(user));

        // Act
        User found = userService.findById(1L);

        // Assert
        assertNotNull(found);
        assertEquals(1L, found.getPk());
    }

    @Test
    void shouldThrowExceptionWhenUserNotFoundById() {
        // Arrange
        when(userRepository.findById(99L))
                .thenReturn(Optional.empty());

        // Act & Assert
        EntityNotFoundException ex = assertThrows(
                EntityNotFoundException.class,
                () -> userService.findById(99L)
        );

        assertEquals("User not found with id [99]", ex.getMessage());
    }
}