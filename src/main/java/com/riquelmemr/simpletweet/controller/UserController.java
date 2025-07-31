package com.riquelmemr.simpletweet.controller;

import com.riquelmemr.simpletweet.dto.CreateUserRequest;
import com.riquelmemr.simpletweet.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity<Void> createUser(@RequestBody CreateUserRequest createUserRequest) {
        userService.create(createUserRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
