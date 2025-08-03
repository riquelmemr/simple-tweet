package com.riquelmemr.simpletweet.service.user;

import com.riquelmemr.simpletweet.entities.User;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import java.util.List;

public interface UserService {
    void create(User user);
    User findByUsername(String username);
    User findById(String id);
    List<User> findAllUsers();
    User extractUserFromToken(JwtAuthenticationToken token);
}
