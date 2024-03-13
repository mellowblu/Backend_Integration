package com.backend.integration.Dto.Request;

import com.backend.integration.Entity.Enums.Role;

public record JwtDto(
    String accessToken,
    String userId,
    String userName,
    String firstName,
    String lastName,
    String email,
    String phoneNumber,
    Role role
) {
    // Constructor with parameters
    public JwtDto {
        // Any additional initialization logic here
    }

    // Constructor with only accessToken parameter
    public JwtDto(String accessToken) {
        this(accessToken, null, null, null, null, null, null, null);
    }

    // Example of a with method to create a new instance with additional fields
    public JwtDto withUserInformation(String userId, String userName, String firstName, String lastName, String email, String phoneNumber, Role role) {
        return new JwtDto(this.accessToken(), userId, userName, firstName, lastName, email, phoneNumber, role);
    }
}
