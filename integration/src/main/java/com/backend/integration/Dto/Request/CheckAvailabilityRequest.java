package com.backend.integration.Dto.Request;

public class CheckAvailabilityRequest {
    private String username;
    private String email;
    private String phoneNumber;

    // Getter and Setter for phoneNumber
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    // Getter and Setter for username
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    // Getter and Setter for email
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Constructors

    // Constructor with only username
    public CheckAvailabilityRequest(String username) {
        this.username = username;
    }

    // Constructor with both username and email
    public CheckAvailabilityRequest(String username, String email) {
        this.username = username;
        this.email = email;
    }

    // Constructor with all fields
    public CheckAvailabilityRequest(String username, String email, String phoneNumber) {
        this.username = username;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
}
