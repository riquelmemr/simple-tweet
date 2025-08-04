package com.riquelmemr.simpletweet.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public abstract class BaseController {
    protected <R> ResponseEntity<R> handleResponse(HttpStatus status, R body) {
        return ResponseEntity.status(status).body(body);
    }
}
