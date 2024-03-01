package com.backend.integration.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ResponseStatus;

// Custom exception class for handling invalid JWT exceptions
@ResponseStatus(HttpStatus.FORBIDDEN)
public class InvalidJwtException extends AuthenticationException {

    // Constructor to create an instance of the exception with a specific message
    public InvalidJwtException(String ex) {
        super(ex);
    }
}