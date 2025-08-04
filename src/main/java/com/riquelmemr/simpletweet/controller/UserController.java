package com.riquelmemr.simpletweet.controller;

import com.riquelmemr.simpletweet.dto.request.CreateUserRequest;
import com.riquelmemr.simpletweet.dto.request.UpdateUserRequest;
import com.riquelmemr.simpletweet.dto.response.UserResponse;
import com.riquelmemr.simpletweet.entities.User;
import com.riquelmemr.simpletweet.facade.UserFacade;
import com.riquelmemr.simpletweet.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserFacade userFacade;
    @Autowired
    private UserMapper userMapper;

    @PostMapping("/create")
    public ResponseEntity<Void> createUser(@RequestBody CreateUserRequest dto) {
        userFacade.register(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<List<User>> findAllUsers() {
        List<User> users = userFacade.findAll();
        return ResponseEntity.ok(users);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable String id,
                                                   @RequestBody UpdateUserRequest request,
                                                   JwtAuthenticationToken token) {
        User user = userFacade.update(id, request, token);
        return ResponseEntity.ok(userMapper.toUserResponseDto(user));
    }
}
