package com.riquelmemr.simpletweet.mapper;

import com.riquelmemr.simpletweet.dto.request.CreateUserRequest;
import com.riquelmemr.simpletweet.dto.response.LoginResponse;
import com.riquelmemr.simpletweet.dto.response.UserResponse;
import com.riquelmemr.simpletweet.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    LoginResponse toLoginResponseDto(String accessToken, Long expiresIn);
    User toModel(CreateUserRequest createUserRequest);

    @Mapping(source = "pk", target = "id")
    UserResponse toUserResponseDto(User user);

    @Mapping(source = "pk", target = "id")
    List<UserResponse> toListUserResponseDto(List<User> users);
}
