package com.riquelmemr.simpletweet.service.user.impl;

import com.riquelmemr.simpletweet.dto.CreateUserRequest;
import com.riquelmemr.simpletweet.entities.Role;
import com.riquelmemr.simpletweet.entities.User;
import com.riquelmemr.simpletweet.exceptions.EntityAlreadyExistsException;
import com.riquelmemr.simpletweet.mapper.UserMapper;
import com.riquelmemr.simpletweet.repository.RoleRepository;
import com.riquelmemr.simpletweet.repository.UserRepository;
import com.riquelmemr.simpletweet.service.user.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

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

    @Override
    @Transactional
    public void create(User user) {
        User userAlreadyExists = findByUsername(user.getUsername());
        Role basicRole = roleRepository.findByName(Role.Values.BASIC.name());

        if (isNotNull(userAlreadyExists)) {
            throw new EntityAlreadyExistsException("User already exists by username.");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Set.of(basicRole));
        userRepository.save(user);
    }

    @Override
    public void create(CreateUserRequest createUserRequest) {
        User user = userMapper.toModel(createUserRequest);
        create(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElse(null);
    }
}
