package com.backend.integration.Service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.backend.integration.Entity.EmailDetails;
import com.backend.integration.Entity.ForgotCodeEntity;
import com.backend.integration.Entity.User;
import com.backend.integration.Entity.VerificationCodeEntity;
import com.backend.integration.Exceptions.UserNotFoundException;
import com.backend.integration.Exceptions.VerificationCodeException;
import com.backend.integration.Repo.ForgotCodeRepo;
import com.backend.integration.Repo.UserRepo;
import com.backend.integration.Repo.VerificationCodeRepo;
import com.backend.integration.Service.AuthService;
import com.backend.integration.Service.EmailService;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.transaction.Transactional;

import java.io.File;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
// Service class responsible for handling email-related operations
@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender javaMailSender; // Autowired JavaMailSender for sending emails

    @Autowired
    private AuthService authService; // Autowired AuthService for authentication-related operations

    @Autowired
    private UserRepo userRepo; // Autowired UserRepo for user data retrieval

    @Autowired
    private ForgotCodeRepo forgotCodeRepo; // Autowired ForgotCodeRepo for handling forgot codes

    @Autowired
    private VerificationCodeRepo verificationCodeRepo; // Autowired VerificationCodeRepo for handling verification codes

    @Value("${spring.mail.username}")
    private String sender; // Email sender's address

    // Storage for generated and entered verification codes, and password reset codes
    private final Map<String, String> generatedCodeStorage = new HashMap<>();
    private final Map<String, String> enteredCodeStorage = new HashMap<>();
    private final Map<String, VerificationCodeEntity> verificationCodeMap = new ConcurrentHashMap<>();
    private final Map<String, String> passwordResetCodes = new HashMap<>();

    // Method to send a simple email using provided EmailDetails
    @Override
    public String sendSimpleMail(EmailDetails details) {
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom(sender); // Set the sender's email address
            mailMessage.setTo(details.getRecipient()); // Set the recipient's email address
            mailMessage.setText(details.getMsgBody()); // Set the email body
            mailMessage.setSubject(details.getSubject()); // Set the email subject

            javaMailSender.send(mailMessage); // Send the email using JavaMailSender
            return "Mail Sent Successfully";
        } catch (Exception e) {
            // Log the specific exception details and return an error message
            return "Error while Sending Mail: " + e.getMessage();
        }
    }

 // Method to send an email with an attachment using provided EmailDetails
@Override
public String sendMailWithAttachment(EmailDetails details) {
    try {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

        mimeMessageHelper.setFrom(sender); // Set the sender's email address
        mimeMessageHelper.setTo(details.getRecipient()); // Set the recipient's email address
        mimeMessageHelper.setText(details.getMsgBody()); // Set the email body
        mimeMessageHelper.setSubject(details.getSubject()); // Set the email subject

        FileSystemResource file = new FileSystemResource(new File(details.getAttachment()));
        mimeMessageHelper.addAttachment(file.getFilename(), file); // Add the attachment to the email

        javaMailSender.send(mimeMessage); // Send the email with attachment
        return "Mail sent Successfully";
    } catch (MessagingException e) {
        // Log the specific exception details and return an error message
        return "Error while sending mail: " + e.getMessage();
    }
}

// Method to generate and store a verification code for a user
@Override
@Transactional
public String generateAndStoreVerificationCode(String userEmail) {
    return generateAndStoreVerificationCode(userEmail, getDefaultExpirationTimeInMillis());
}

// Overloaded method to generate and store a verification code with a specific expiration time
@Override
@Transactional
public String generateAndStoreVerificationCode(String userEmail, Long expirationTimeInMillis) {
    // Fetch the user from the repository based on userEmail
    User user = userRepo.findByEmail(userEmail);

    if (user != null) {
        String generatedCode = generateRandomCode();

        // Use a default expiration time if not provided
        if (expirationTimeInMillis == null) {
            expirationTimeInMillis = getDefaultExpirationTimeInMillis();
        }

        // Save verification code with expiration time to the database
        verificationCodeRepo.save(new VerificationCodeEntity(user, generatedCode, expirationTimeInMillis));

        // Store verification code in memory (if needed)
        generatedCodeStorage.put(userEmail, generatedCode);

        return generatedCode;
    } else {
        // Handle the case where the user is not found
        return "User not found";
    }
}

// Method to get the default expiration time in milliseconds
private long getDefaultExpirationTimeInMillis() {
    // Implement this method to provide a default expiration time
    // This could be based on some configuration or constant value
    return System.currentTimeMillis() + (5 * 60 * 1000); // Example: expiration time is 5 minutes from now
}

// Method to get stored verification information for a user
@Override
public VerificationCodeEntity getStoredVerificationInfoForUser(String userEmail) {
    // Find the stored verification information for the given user email
    Optional<VerificationCodeEntity> verificationInfoOptional = verificationCodeRepo.findByUserEmail(userEmail);

    return verificationInfoOptional.orElse(null);
}

// Method to verify a code entered by a user
@Override
@Transactional
public boolean verifyCode(String userEmail, String enteredCode) {
    // Get the stored verification info for the user
    Optional<VerificationCodeEntity> verificationInfoOptional = verificationCodeRepo.findByUserEmail(userEmail);

    if (verificationInfoOptional.isPresent()) {
        VerificationCodeEntity verificationInfo = verificationInfoOptional.get();

        String storedCode = verificationInfo.getVerificationCode();
        long expirationTimeInMillis = verificationInfo.getExpirationTimeInMillis();

        // Log values for debugging
        System.out.println("Current Time: " + System.currentTimeMillis());
        System.out.println("Expiration Time: " + expirationTimeInMillis);
        System.out.println("Stored Code: " + storedCode);
        System.out.println("Entered Code: " + enteredCode);

        // Check if the code is expired
        if (System.currentTimeMillis() <= expirationTimeInMillis && enteredCode.equals(storedCode)) {
            // Code is valid, update the user's verification status
            authService.updateUserVerificationStatus(userEmail, true);
            return true;
        }
    }

    return false;
}

// Method to send a forgot code via email
@Override
public void sendForgotCodeViaEmail(String userEmail, String forgotCode) {
    // Implement the logic to send the forgot code via email
    // You can use the injected JavaMailSender or any other email sending mechanism
    // Include the user's email address, subject, and the generated forgot code in the email
    // Example:
    EmailDetails emailDetails = new EmailDetails();
    emailDetails.setRecipient(userEmail);
    emailDetails.setSubject("Forgot Code");
    emailDetails.setMsgBody("Your forgot code is: " + forgotCode);

    sendSimpleMail(emailDetails);
}

// Method to check if a verification code is expired for a user
@Override
public boolean isVerificationCodeExpired(String email) {
    VerificationCodeEntity verificationCodeEntity = verificationCodeMap.get(email);
    if (verificationCodeEntity != null) {
        Instant expirationTime = verificationCodeEntity.getExpirationTime();
        return Instant.now().isAfter(expirationTime);
    }
    return true; // Assume expired if no verification code is found
}

// Method to get the stored verification code for a user
@Override
public String getStoredCodeForUser(String userEmail) {
    return generatedCodeStorage.getOrDefault(userEmail, "");
}

// Method to get the entered code for a user
@Override
public String getEnteredCodeForUser(String verificationCode) {
    return enteredCodeStorage.getOrDefault(verificationCode, "");
}

// Method to resend a verification code to a user
@Override
@Transactional
public String resendVerificationCode(String userEmail) {
    User user = userRepo.findByEmail(userEmail);

    if (user != null) {
        // Check if there's an existing verification code
        Optional<VerificationCodeEntity> existingVerificationCode = verificationCodeRepo.findByUserEmail(userEmail);

        if (existingVerificationCode.isPresent()) {
            // Use the existing verification code and update its expiration time
            VerificationCodeEntity verificationCodeEntity = existingVerificationCode.get();
            verificationCodeEntity.setExpirationTimeInMillis(getDefaultExpirationTimeInMillis());
            verificationCodeRepo.save(verificationCodeEntity);

            // You can send the code by email or any other communication method
            return verificationCodeEntity.getVerificationCode();
        } else {
            // Generate a new verification code and store it
            String generatedCode = generateRandomCode();
            long expirationTimeInMillis = getDefaultExpirationTimeInMillis();

            verificationCodeRepo.save(new VerificationCodeEntity(user, generatedCode, expirationTimeInMillis));
            generatedCodeStorage.put(userEmail, generatedCode);

            // You can send the code by email or any other communication method
            return generatedCode;
        }
    } else {
        return "User not found";
    }
}

// Method to generate a verification code
@Override
@Transactional
public String generateVerificationCode() {
    // Implement the logic to generate a verification code
    return generateRandomCode();
}

// Method to store entered code for a user
@Override
public void storeEnteredCode(String verificationCode, String enteredCode) {
    enteredCodeStorage.put(verificationCode, enteredCode);
}

// Method to get the stored verification code for a user
@Override
public String getStoredCode(String userEmail) {
    return generatedCodeStorage.getOrDefault(userEmail, "");
}

// Method to save a verification code entity
@Override
public void saveVerificationCode(VerificationCodeEntity verificationCodeEntity) {
    verificationCodeRepo.save(verificationCodeEntity);
}

// Method to generate a random code
private String generateRandomCode() {
    Random random = new Random();
    return String.format("%06d", random.nextInt(999999));
}

// Method to get the default expiration time in LocalDateTime
@Override
public LocalDateTime getExpirationTime() {
    // Implement this method if you need to retrieve the expiration time
    // This could be based on some configuration or constant value
    return LocalDateTime.now().plusMinutes(5); // Example: expiration time is 5 minutes from now
}

// Method to set the expiration time (if needed)
@Override
public void setExpirationTime(LocalDateTime expirationTime) {
    // Implement this method if you need to set the expiration time
    // This could be useful if you want to customize the expiration time dynamically
    // For now, you can leave it empty if not needed
}

// Method to generate a forgot code
private String generateForgotCode() {
    Random random = new Random();
    return String.format("%06d", random.nextInt(999999));
}

// Method to update the forgot code for a user
@Override
@Transactional
public void updateForgotCode(User user, String newForgotCode, Long forgotExpirationTimeInMillis) {
    // Fetch the existing forgot code entity for the user
    Optional<ForgotCodeEntity> existingForgotCodeOptional = forgotCodeRepo.findByUser(user);

    if (existingForgotCodeOptional.isPresent()) {
        // Update the existing forgot code entity
        ForgotCodeEntity existingForgotCode = existingForgotCodeOptional.get();
        existingForgotCode.setForgotCode(newForgotCode);
        existingForgotCode.setForgotExpirationTimeInMillis(forgotExpirationTimeInMillis);
        forgotCodeRepo.save(existingForgotCode);
    } else {
        // Create a new forgot code entity and save it
        ForgotCodeEntity newForgotCodeEntity = new ForgotCodeEntity(user, newForgotCode,forgotExpirationTimeInMillis);
        forgotCodeRepo.save(newForgotCodeEntity);
    }
}

// Method to get the default expiration time for forgot codes in milliseconds
private long getDefaultForgotCodeExpirationTimeInMillis() {
    // Implement this method to provide a default expiration time for forgot codes
    // This could be based on some configuration or constant value
    return System.currentTimeMillis() + (24 * 60 * 60 * 1000); // Example: expiration time is 24 hours from now
}

// Constant for the default expiration time for forgot codes in milliseconds
private static final long FORGOT_CODE_EXPIRATION_TIME_MILLIS = 30 * 60 * 1000;
// Method to initiate the forgot password process
@Override
@Transactional
public void initiateForgotPassword(String email) {
    // Check if the user with the provided email exists
    User user = userRepo.findByEmail(email);
    if (user == null) {
        // Handle the case where the user is not found
        throw new UserNotFoundException("User not found for email: " + email);
    }

    // Check if there's an existing forgot code
    Optional<ForgotCodeEntity> existingForgotCode = forgotCodeRepo.findByUser(user);

    String forgotCode;
    long forgotExpirationTimeInMillis = FORGOT_CODE_EXPIRATION_TIME_MILLIS; // Declare it here

    if (existingForgotCode.isPresent()) {
        // Use the existing forgot code from memory
        forgotCode = generateForgotCode();
        forgotExpirationTimeInMillis = existingForgotCode.get().getForgotExpirationTimeInMillis(); // Retrieve from existing entity
    } else {
        // Generate a new forgot code
        forgotCode = generateForgotCode();

        // Create the forgot code entity
        forgotExpirationTimeInMillis = getDefaultForgotCodeExpirationTimeInMillis(); // Use your default expiration time logic here

        ForgotCodeEntity forgotCodeEntity = new ForgotCodeEntity(user, forgotCode, forgotExpirationTimeInMillis);

        // Save the forgot code to the database
        forgotCodeRepo.save(forgotCodeEntity);
    }

    // Update the forgot code in memory and in the database
    Map<String, Object> forgotCodeInfo = new HashMap<>();
    forgotCodeInfo.put("code", forgotCode);
    forgotCodeInfo.put("expirationTime", forgotExpirationTimeInMillis);

    passwordResetCodes.put(user.getEmail(), forgotCode);
    updateForgotCode(user, forgotCode, forgotExpirationTimeInMillis);

    // Send the forgot code via email
    sendForgotCodeViaEmail(user.getEmail(), forgotCode);
}

// Method to generate and store a forgot code for a user
@Override
@Transactional
public String generateAndStoreForgotCode(String userEmail) {
    return generateAndStoreForgotCode(userEmail, getDefaultExpirationTimeInMillis());
}

// Overloaded method to generate and store a forgot code with a custom expiration time for a user
@Override
@Transactional
public String generateAndStoreForgotCode(String userEmail, Long expirationTimeInMillis) {
    User user = userRepo.findByEmail(userEmail);

    if (user != null) {
        // Generate a new forgot code
        String generatedCode = generateRandomCode();

        if (expirationTimeInMillis == null) {
            expirationTimeInMillis = getDefaultForgotCodeExpirationTimeInMillis();
        }

        // Create or update the forgot code entity
        updateForgotCode(user, generatedCode, expirationTimeInMillis);

        // Update the forgot code in memory
        passwordResetCodes.put(userEmail, generatedCode);

        return generatedCode;
    } else {
        return "User not found";
    }
}

// Method to check if a provided forgot code is valid for a user
@Override
@Transactional
public boolean isForgotCodeValid(String userEmail, String enteredCode) {
    // Retrieve the stored code from the map
    String storedCode = passwordResetCodes.get(userEmail);

    if (storedCode != null) {
        // Check if the entered code matches the stored code
        return enteredCode.equals(storedCode);
    }

    return false;
}

// Method to resend a forgot code for a user
@Override
@Transactional
public String resendForgotCode(String userEmail) {
    User user = userRepo.findByEmail(userEmail);

    if (user != null) {
        // Check if there's an existing forgot code
        Optional<ForgotCodeEntity> existingForgotCode = forgotCodeRepo.findByUser(user);

        if (existingForgotCode.isPresent()) {
            // Use the existing forgot code and update its expiration time
            ForgotCodeEntity forgotCodeEntity = existingForgotCode.get();
            forgotCodeEntity.setForgotExpirationTimeInMillis(getDefaultForgotCodeExpirationTimeInMillis());
            forgotCodeRepo.save(forgotCodeEntity);

            // You can send the code by email or any other communication method
            return forgotCodeEntity.getForgotCode();
        } else {
            // Generate a new forgot code and store it
            String generatedCode = generateForgotCode();
            long expirationTimeInMillis = FORGOT_CODE_EXPIRATION_TIME_MILLIS;

            ForgotCodeEntity newForgotCodeEntity = new ForgotCodeEntity(user, generatedCode, expirationTimeInMillis);
            forgotCodeRepo.save(newForgotCodeEntity);

            // You can send the code by email or any other communication method
            return generatedCode;
        }
    } else {
        throw new UserNotFoundException("User not found for email: " + userEmail);
    }
}

// Method to update the user's password
@Override
@Transactional
public void updatePassword(String userEmail, String newPassword) {
    // Find the user by email
    User user = userRepo.findByEmail(userEmail);

    if (user != null) {
        // Update the user's password with the hashed password
        user.setPassword(newPassword);
        userRepo.save(user);
    } else {
        // Handle the case where the user is not found
        throw new UserNotFoundException("User not found for email: " + userEmail);
    }

}

// Method to reset the user's password using a verification code
@Override
@Transactional
public void resetPassword(String userEmail, String verificationCode, String newPassword) {
    // Find the user by email
    User user = userRepo.findByEmail(userEmail);

    if (user != null) {
        // Find the VerificationCodeEntity associated with the user ID and the provided verification code
        Optional<VerificationCodeEntity> verificationCodeOptional = verificationCodeRepo.findByUserEmail(userEmail);
        if (verificationCodeOptional.isPresent()) {
            // Verification code is valid, update the user's password
            user.setPassword(newPassword);
            userRepo.save(user);

            // Optionally, you may want to delete the used verification code from the database
            verificationCodeRepo.delete(verificationCodeOptional.get());
        } else {
            // Handle the case where the verification code is invalid
            throw new VerificationCodeException("Invalid verification code");
        }
    } else {
        // Handle the case where the user is not found
        throw new UserNotFoundException("User not found for email: " + userEmail);
    }
}
}