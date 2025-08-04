package com.riquelmemr.simpletweet.dto.request;

public record UpdateUserRequest(String username,
                                String email,
                                String name,
                                String bio) {
}
