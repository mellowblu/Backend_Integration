package com.backend.integration.Exceptions;

public class VerificationCodeException extends RuntimeException {

    // Constructor to create an instance of the exception with a specific message
    public VerificationCodeException(String message) {
        super(message);
    }
}