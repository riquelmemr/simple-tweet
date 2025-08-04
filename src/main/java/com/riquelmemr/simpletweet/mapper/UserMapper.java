package com.riquelmemr.simpletweet.mapper;

import com.riquelmemr.simpletweet.dto.request.CreateUserRequest;
import com.riquelmemr.simpletweet.dto.response.LoginResponse;
import com.riquelmemr.simpletweet.dto.response.UserResponse;
import com.riquelmemr.simpletweet.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    LoginResponse toLoginResponseDto(String accessToken, Long expiresIn);
    User toModel(CreateUserRequest createUserRequest);

    @Mapping(source = "pk", target = "id")
    UserResponse toUserResponseDto(User user);
}
