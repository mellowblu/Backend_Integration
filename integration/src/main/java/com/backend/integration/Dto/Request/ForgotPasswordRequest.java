package com.backend.integration.Dto.Request;

public class ForgotPasswordRequest {
    private String email;  // You can also use username if preferred

    // Default constructor for JSON parsing
    public ForgotPasswordRequest() {
    }

    // Constructor with email parameter
    public ForgotPasswordRequest(String email) {
        this.email = email;
    }

    // Getter and setter for email
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
