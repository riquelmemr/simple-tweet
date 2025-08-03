package com.riquelmemr.simpletweet.dto.request;

public record CreateUserRequest(String username, String name, String email, String password) {
}
