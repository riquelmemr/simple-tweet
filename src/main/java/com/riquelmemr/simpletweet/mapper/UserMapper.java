package com.riquelmemr.simpletweet.mapper;

import com.riquelmemr.simpletweet.dto.CreateUserRequest;
import com.riquelmemr.simpletweet.dto.LoginResponse;
import com.riquelmemr.simpletweet.entities.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    LoginResponse toLoginResponseDto(String accessToken, Long expiresIn);
    User toModel(CreateUserRequest createUserRequest);
}
