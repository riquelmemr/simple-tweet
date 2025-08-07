package com.riquelmemr.simpletweet.mapper;

import com.riquelmemr.simpletweet.dto.request.CreateUserRequest;
import com.riquelmemr.simpletweet.dto.response.LoginResponse;
import com.riquelmemr.simpletweet.dto.response.UserResponse;
import com.riquelmemr.simpletweet.model.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserMapper {
    public User toModel(CreateUserRequest request) {
        if (request == null) {
            return null;
        }

        User user = new User();
        user.setUsername(request.username());
        user.setName(request.name());
        user.setEmail(request.email());
        user.setPassword(request.password());
        return user;
    }

    public LoginResponse toLoginResponseDto(String accessToken, Long expiresIn) {
        if (accessToken == null && expiresIn == null) {
            return null;
        }

        return new LoginResponse(accessToken, expiresIn);
    }

    public UserResponse toUserResponseDto(User user) {
        if (user == null) {
            return null;
        }

        return new UserResponse(
                user.getPk(),
                user.getUsername(),
                user.getName(),
                user.getEmail(),
                user.getBio()
        );
    }

    public List<UserResponse> toListUserResponseDto(List<User> users) {
        if (users == null) {
            return null;
        }

        return users.stream()
                .map(this::toUserResponseDto)
                .toList();
    }
}
