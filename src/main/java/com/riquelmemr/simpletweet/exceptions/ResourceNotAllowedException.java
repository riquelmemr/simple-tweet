package com.riquelmemr.simpletweet.exceptions;

public class ResourceNotAllowedException extends RuntimeException {
    public ResourceNotAllowedException(String message) {
        super(message);
    }
}
