package com.riquelmemr.simpletweet.service.auth;

import com.riquelmemr.simpletweet.dto.LoginRequest;
import com.riquelmemr.simpletweet.dto.LoginResponse;

public interface AuthService {
    LoginResponse login(LoginRequest loginRequest);
}
