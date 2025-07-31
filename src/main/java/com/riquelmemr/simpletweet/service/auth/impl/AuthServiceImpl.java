package com.riquelmemr.simpletweet.service.auth.impl;

import com.riquelmemr.simpletweet.dto.LoginRequest;
import com.riquelmemr.simpletweet.dto.LoginResponse;
import com.riquelmemr.simpletweet.entities.User;
import com.riquelmemr.simpletweet.mapper.UserMapper;
import com.riquelmemr.simpletweet.security.JwtUtils;
import com.riquelmemr.simpletweet.service.auth.AuthService;
import com.riquelmemr.simpletweet.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.stereotype.Service;

import static com.riquelmemr.simpletweet.utils.ObjectUtils.isNull;

@Service
public class AuthServiceImpl implements AuthService {
    private final static String BAD_CREDENTIALS_ERROR_MESSAGE = "Username or password is invalid.";

    @Autowired
    private UserService userService;
    @Autowired
    private JwtEncoder jwtEncoder;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private UserMapper userMapper;

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        User user = userService.findByUsername(loginRequest.username());

        if (isNull(user)) {
            throw new BadCredentialsException(BAD_CREDENTIALS_ERROR_MESSAGE);
        }

        if (!passwordEncoder.matches(loginRequest.password(), user.getPassword())) {
            throw new BadCredentialsException(BAD_CREDENTIALS_ERROR_MESSAGE);
        }

        String token = jwtUtils.generateJwtToken(user);
        return userMapper.toLoginResponseDto(token, jwtUtils.getJwtExpiresIn());
    }
}
