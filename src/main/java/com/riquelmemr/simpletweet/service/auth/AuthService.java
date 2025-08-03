package com.riquelmemr.simpletweet.service.auth;

import com.riquelmemr.simpletweet.dto.request.LoginRequest;
import com.riquelmemr.simpletweet.dto.response.LoginResponse;

public interface AuthService {
    LoginResponse login(LoginRequest loginRequest);
}
