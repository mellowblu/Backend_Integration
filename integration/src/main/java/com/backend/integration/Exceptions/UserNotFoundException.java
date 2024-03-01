package com.backend.integration.Exceptions;

// Custom exception class for handling user not found scenarios
public class UserNotFoundException extends RuntimeException {

    // Constructor to create an instance of the exception with a specific message
    public UserNotFoundException(String message) {
        super(message);
    }
}
