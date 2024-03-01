package com.backend.integration.Exceptions;

// Custom exception class for representing a situation where a forgot code is not found
public class ForgotCodeNotFoundException extends RuntimeException {

    // Constructor with a custom message
    public ForgotCodeNotFoundException(String message) {
        super(message);
    }
}
