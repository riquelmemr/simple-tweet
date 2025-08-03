package com.riquelmemr.simpletweet.facade;

import com.riquelmemr.simpletweet.dto.request.CreateUserRequest;
import com.riquelmemr.simpletweet.entities.User;
import com.riquelmemr.simpletweet.mapper.UserMapper;
import com.riquelmemr.simpletweet.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserFacade {
    @Autowired
    private UserService userService;
    @Autowired
    private UserMapper userMapper;

    public void register(CreateUserRequest dto) {
        User user = userMapper.toModel(dto);
        userService.create(user);
    }

    public List<User> findAll() {
        return userService.findAllUsers();
    }
}
