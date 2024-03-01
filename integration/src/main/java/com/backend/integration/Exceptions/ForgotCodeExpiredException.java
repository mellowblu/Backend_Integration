package com.backend.integration.Exceptions;

public class ForgotCodeExpiredException extends RuntimeException {
    public ForgotCodeExpiredException(String message) {
        super(message);
    }
}
