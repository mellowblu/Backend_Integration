package com.backend.integration.Service;

import java.time.LocalDateTime;

import com.backend.integration.Entity.EmailDetails;
import com.backend.integration.Entity.User;
import com.backend.integration.Entity.VerificationCodeEntity;

public interface EmailService {

    // Method to get the expiration time
    LocalDateTime getExpirationTime();

    // Method to set the expiration time
    void setExpirationTime(LocalDateTime expirationTime);

    // Method to send a simple email
    String sendSimpleMail(EmailDetails details);

    // Method to send an email with an attachment
    String sendMailWithAttachment(EmailDetails details);

    // Existing method to get a stored verification code for a user
    String getStoredCodeForUser(String userEmail);

    // Method to save a verification code entity
    void saveVerificationCode(VerificationCodeEntity verificationCodeEntity);

    // New method to generate and store a verification code with a default expiration time
    String generateAndStoreVerificationCode(String userEmail);

    // New method to generate and store a verification code with a custom expiration time
    String generateAndStoreVerificationCode(String userEmail, Long expirationTimeInMillis);

    // New method to get the entered code for a verification code
    String getEnteredCodeForUser(String verificationCode);

    // New method to store the entered code for a verification code
    void storeEnteredCode(String verificationCode, String enteredCode);

    // Existing method to verify the entered code
    boolean verifyCode(String userEmail, String enteredCode);

    // Method to get stored verification information for a user
    VerificationCodeEntity getStoredVerificationInfoForUser(String userEmail);

    // Method to check if the verification code is expired
    boolean isVerificationCodeExpired(String email);

    // Method to generate a verification code
    String generateVerificationCode();

    // Method to resend a verification code
    String resendVerificationCode(String userEmail);

    // Method to generate and store a forgot code
    String generateAndStoreForgotCode(String userEmail);

    // Method to send a forgot code via email
    void sendForgotCodeViaEmail(String userEmail, String forgotCode);

    // Method to generate and store a forgot code with a custom expiration time
    String generateAndStoreForgotCode(String userEmail, Long expirationTimeInMillis);

    // Method to check if a forgot code is valid
    boolean isForgotCodeValid(String userEmail, String enteredCode);

    // Method to reset a user's password
    void resetPassword(String userEmail, String verificationCode, String newPassword);

    // Method to get a stored code for a user
    String getStoredCode(String userEmail);

    // Method to initiate the forgot password process
    void initiateForgotPassword(String email);

    // Method to update a user's password
    void updatePassword(String userEmail, String newPassword);

    // Method to resend a forgot code
    String resendForgotCode(String userEmail);

    // Method to update a forgot code for a user
    void updateForgotCode(User user, String newForgotCode, Long forgotExpirationTimeInMillis);
}
