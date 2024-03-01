package com.backend.integration.Exceptions;

// Custom exception class for representing a situation where a forgot code has expired
public class ForgotCodeExpiredException extends RuntimeException {

    // Constructor with a custom message
    public ForgotCodeExpiredException(String message) {
        super(message);
    }
}
