package com.riquelmemr.simpletweet.service.user;

import com.riquelmemr.simpletweet.dto.CreateUserRequest;
import com.riquelmemr.simpletweet.entities.User;

public interface UserService {
    void create(CreateUserRequest user);
    void create(User user);
    User findByUsername(String username);
}
