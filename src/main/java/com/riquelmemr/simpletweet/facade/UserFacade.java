package com.riquelmemr.simpletweet.facade;

import com.riquelmemr.simpletweet.dto.request.CreateUserRequest;
import com.riquelmemr.simpletweet.dto.request.UpdateUserRequest;
import com.riquelmemr.simpletweet.entities.User;
import com.riquelmemr.simpletweet.mapper.UserMapper;
import com.riquelmemr.simpletweet.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserFacade {
    @Autowired
    private UserService userService;
    @Autowired
    private UserMapper userMapper;

    public void register(CreateUserRequest request) {
        User user = userMapper.toModel(request);
        userService.create(user);
    }

    public List<User> findAll() {
        return userService.findAll();
    }

    public User update(String ownerId, UpdateUserRequest request, JwtAuthenticationToken token) {
        return userService.update(ownerId, request, token);
    }
}
