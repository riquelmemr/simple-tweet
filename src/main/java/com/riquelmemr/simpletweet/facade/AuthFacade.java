package com.riquelmemr.simpletweet.facade;

import com.riquelmemr.simpletweet.dto.request.LoginRequest;
import com.riquelmemr.simpletweet.dto.response.LoginResponse;
import com.riquelmemr.simpletweet.service.auth.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AuthFacade {
    @Autowired
    private AuthService authService;

    public LoginResponse login(LoginRequest loginRequest) {
        return authService.login(loginRequest);
    }
}
