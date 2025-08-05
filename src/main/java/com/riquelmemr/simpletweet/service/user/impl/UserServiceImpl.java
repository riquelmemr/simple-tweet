package com.riquelmemr.simpletweet.service.user.impl;

import com.riquelmemr.simpletweet.dto.request.UpdateUserRequest;
import com.riquelmemr.simpletweet.model.Role;
import com.riquelmemr.simpletweet.model.User;
import com.riquelmemr.simpletweet.enums.RoleEnum;
import com.riquelmemr.simpletweet.exceptions.EntityAlreadyExistsException;
import com.riquelmemr.simpletweet.exceptions.EntityNotFoundException;
import com.riquelmemr.simpletweet.exceptions.ResourceNotAllowedException;
import com.riquelmemr.simpletweet.mapper.UserMapper;
import com.riquelmemr.simpletweet.repository.RoleRepository;
import com.riquelmemr.simpletweet.repository.UserRepository;
import com.riquelmemr.simpletweet.security.JwtUtils;
import com.riquelmemr.simpletweet.service.user.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import static com.riquelmemr.simpletweet.utils.ObjectUtils.isNotNull;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private JwtUtils jwtUtils;

    @Override
    @Transactional
    public void create(User user) {
        User userAlreadyExists = findByUsername(user.getUsername());
        Role basicRole = roleRepository.findByName(RoleEnum.BASIC.name().toUpperCase());

        if (isNotNull(userAlreadyExists)) {
            throw new EntityAlreadyExistsException("User already exists by username.");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Set.of(basicRole));
        userRepository.save(user);
    }

    @Override
    @Transactional
    public User update(String ownerId, UpdateUserRequest request, JwtAuthenticationToken token) {
        User requester = extractUserFromToken(token);
        User owner = findById(ownerId);

        boolean isAdmin = requester.getRoles()
                .stream()
                .anyMatch(role -> role.getName().equals(RoleEnum.ADMIN.name()));

        boolean isOwner = owner.getPk().equals(requester.getPk());

        if (!isOwner && !isAdmin) {
            throw new ResourceNotAllowedException("You do not have permission to update this user.");
        }

        updateUserData(request, owner);
        userRepository.save(owner);
        return owner;
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElse(null);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(String id) {
        return userRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new EntityNotFoundException("User not found with id [" + id + "]"));
    }

    @Override
    public User extractUserFromToken(JwtAuthenticationToken token) {
        String userId = jwtUtils.getIdByToken(token);
        return findById(userId);
    }

    private void updateUserData(UpdateUserRequest request, User owner) {
        if (isNotNull(request.name())) owner.setName(request.name());
        if (isNotNull(request.username())) owner.setUsername(request.username());
        if (isNotNull(request.email())) owner.setEmail(request.email());
        if (isNotNull(request.bio())) owner.setBio(request.bio());
    }
}
