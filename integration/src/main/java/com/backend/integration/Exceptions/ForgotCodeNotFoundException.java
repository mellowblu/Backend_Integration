package com.backend.integration.Exceptions;

public class ForgotCodeNotFoundException extends RuntimeException {
    public ForgotCodeNotFoundException(String message) {
        super(message);
    }
}
