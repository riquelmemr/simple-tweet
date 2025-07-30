package com.riquelmemr.simpletweet.service.user.impl;

import com.riquelmemr.simpletweet.entities.User;
import com.riquelmemr.simpletweet.exceptions.EntityNotFoundException;
import com.riquelmemr.simpletweet.repository.UserRepository;
import com.riquelmemr.simpletweet.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final static String BAD_CREDENTIALS_ERROR = "User or password is invalid.";

    @Autowired
    private UserRepository userRepository;

    public void create(User user) {
        userRepository.save(user);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException(BAD_CREDENTIALS_ERROR));
    }
}
