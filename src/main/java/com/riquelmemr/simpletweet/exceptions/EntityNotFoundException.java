package com.riquelmemr.simpletweet.exceptions;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(String message) {
        super("Entity not found with data [" + message + "]");
    }
}
