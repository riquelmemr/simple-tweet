package com.riquelmemr.simpletweet.mapper;

import com.riquelmemr.simpletweet.dto.LoginResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    LoginResponse toLoginResponseDto(String accessToken, Long expiresIn);
}
