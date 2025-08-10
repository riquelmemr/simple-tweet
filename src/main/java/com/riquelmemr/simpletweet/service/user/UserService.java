package com.riquelmemr.simpletweet.service.user;

import com.riquelmemr.simpletweet.dto.request.UpdateUserRequest;
import com.riquelmemr.simpletweet.model.User;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import java.util.List;

public interface UserService {
    void create(User user);
    User update(Long ownerId, UpdateUserRequest request, JwtAuthenticationToken token);
    User findByUsername(String username);
    User findById(Long id);
    List<User> findAll();
    User extractUserFromToken(JwtAuthenticationToken token);
}
