package com.backend.integration.Exceptions;

// Custom exception class for representing an account lock situation
public class AccountLockedException extends RuntimeException {

    // Default constructor
    public AccountLockedException() {
        super();
    }

    // Constructor with a custom message
    public AccountLockedException(String message) {
        super(message);
    }

    // Constructor with a custom message and a cause (nested exception)
    public AccountLockedException(String message, Throwable cause) {
        super(message, cause);
    }
}
