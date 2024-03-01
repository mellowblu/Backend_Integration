package com.backend.integration.Dto.Request;

public record SignInDto(
    String email,
    String password
) {
    // No additional logic in this record class
}
