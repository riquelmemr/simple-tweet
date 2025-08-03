package com.riquelmemr.simpletweet.controller;

import com.riquelmemr.simpletweet.dto.request.LoginRequest;
import com.riquelmemr.simpletweet.dto.response.LoginResponse;
import com.riquelmemr.simpletweet.repository.UserRepository;
import com.riquelmemr.simpletweet.service.auth.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private JwtEncoder jwtEncoder;

    @Autowired
    private AuthService authService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/token")
    public ResponseEntity<LoginResponse> login(@RequestBody final LoginRequest loginRequest) {
        LoginResponse loginResponse = authService.login(loginRequest);
        return ResponseEntity.ok().body(loginResponse);
    }
}
