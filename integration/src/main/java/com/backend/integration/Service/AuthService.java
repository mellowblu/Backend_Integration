package com.backend.integration.Service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.backend.integration.Dto.Request.SignUpDto;
import com.backend.integration.Entity.User;
import com.backend.integration.Exceptions.AccountLockedException;
import com.backend.integration.Exceptions.InvalidJwtException;
import com.backend.integration.Repo.ForgotCodeRepo;
import com.backend.integration.Repo.UserRepo;

@Service
public class AuthService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private TokenProvider tokenProvider;

    @SuppressWarnings("unused")
    @Autowired
    private ForgotCodeRepo forgotCodeRepo;

    @Override
    public UserDetails loadUserByUsername(String username) {
        // Load user details by email (assuming email is the username)
        var user = userRepo.findByEmail(username);
        return user;
    }

    // Method for user sign-up
    public String signUp(SignUpDto data) throws InvalidJwtException {
        // Check if the email already exists
        if (userRepo.findByEmail(data.email()) != null) {
            throw new InvalidJwtException("Email already exists");
        }

        // Encrypt the password using BCrypt
        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());

        // Create a new user entity
        User newUser = new User(data.email(), data.userName(), encryptedPassword, data.firstName(), data.lastName(), data.phoneNumber(), data.role());

        // Save the new user entity to the database
        userRepo.save(newUser);

        // Generate and return access token after successful sign-up
        return tokenProvider.generateAccessToken(newUser);
    }

    // Method for user sign-in
    public String signIn(String email, String password) throws InvalidJwtException, AccountLockedException {
        // Find the user by email
        var user = userRepo.findByEmail(email);

        // Check if the user exists
        if (user == null) {
            throw new InvalidJwtException("Invalid email or password");
        }

        try {
            // Check if the account is locked
            if (!user.isAccountNonLocked()) {
                throw new AccountLockedException("Account is locked. Try again after 30 minutes.");
            }

            // Check if the entered password matches the stored password
            if (!new BCryptPasswordEncoder().matches(password, user.getPassword())) {
                // Handle failed login attempt
                handleFailedLoginAttempt(user);
                throw new InvalidJwtException("Invalid email or password");
            }

            // Reset failed login attempts upon successful login
            resetFailedLoginAttempts(user);

            // Generate and return access token after successful sign-in
            return tokenProvider.generateAccessToken(user);
        } catch (AccountLockedException e) {
            // Handle the AccountLockedException here, log it, or rethrow as needed
            throw e;
        }
    }

    // Method to handle a failed login attempt
    private void handleFailedLoginAttempt(User user) {
        user.setFailedLoginAttempts(user.getFailedLoginAttempts() + 1);
        user.setLastFailedLogin(LocalDateTime.now());

        // Lock the account if failed login attempts exceed a threshold
        if (user.getFailedLoginAttempts() >= 3) {
            user.setAccountLockedUntil(LocalDateTime.now().plusMinutes(30));
        }

        // Save the updated user entity to the database
        userRepo.save(user);
    }

    // Method to reset failed login attempts
    private void resetFailedLoginAttempts(User user) {
        user.setFailedLoginAttempts(0);
        user.setLastFailedLogin(null);
        user.setAccountLockedUntil(null);

        // Save the updated user entity to the database
        userRepo.save(user);
    }

    // Method to get a user by email
    public User getUserByEmail(String email) {
        return userRepo.findByEmail(email);
    }

    // Method to update the user's verification status
    public void updateUserVerificationStatus(String userEmail, boolean verified) {
        // Find the user by email
        User user = userRepo.findByEmail(userEmail);

        // Update the verification status
        if (user != null) {
            user.setVerified(verified);
            // Save the updated user entity
            userRepo.save(user);
        }
    }
}
