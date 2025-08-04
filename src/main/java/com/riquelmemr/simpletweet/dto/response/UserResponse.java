package com.riquelmemr.simpletweet.dto.response;

import java.util.UUID;

public record UserResponse(UUID id, String username, String name, String email, String bio) {
}
