package com.riquelmemr.simpletweet.dto.response;

public record ErrorResponse(
        String timestamp,
        int status,
        String error,
        String message
) {}
