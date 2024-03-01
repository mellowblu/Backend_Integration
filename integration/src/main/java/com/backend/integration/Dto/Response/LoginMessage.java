package com.backend.integration.Dto.Response;

public class LoginMessage {
    private String message;
    private Boolean success;

    // Getter and Setter for message
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    // Getter and Setter for success
    public Boolean isSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    // Constructor with parameters
    public LoginMessage(String message, Boolean success) {
        this.message = message;
        this.success = success;
    }
}
