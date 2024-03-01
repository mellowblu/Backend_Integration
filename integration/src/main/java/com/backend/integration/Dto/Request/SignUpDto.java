package com.backend.integration.Dto.Request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.backend.integration.Entity.Enums.Role;

public record SignUpDto(
        @Email String email,
        @NotBlank @Size(min = 8) String password,
        @NotBlank String firstName,
        @NotBlank String lastName,
        @NotBlank String userName,
        @NotBlank @Pattern(regexp = "09\\d{9}") String phoneNumber,
        Role role
) {
    // Getter for email
    public String getEmail() {
        return email;
    }

    // Getter for userName
    public String getUserName() {
        return userName;
    }

    // Getter for role
    public Role getRole() {
        return role;
    }

    // Getter for phoneNumber
    public String getPhoneNumber() {
        return phoneNumber;
    }
}
