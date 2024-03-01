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
        var user = userRepo.findByEmail(username);
        return user;
    }

    public String signUp(SignUpDto data) throws InvalidJwtException {
        if (userRepo.findByEmail(data.email()) != null) {
            throw new InvalidJwtException("Email already exists");
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User newUser = new User(data.email(), data.userName(), encryptedPassword, data.firstName(), data.lastName(), data.phoneNumber(), data.role());
        userRepo.save(newUser);

        // Generate and return access token after successful sign-up
        return tokenProvider.generateAccessToken(newUser);
    }

    public String signIn(String email, String password) throws InvalidJwtException, AccountLockedException {
        var user = userRepo.findByEmail(email);
    
        if (user == null) {
            throw new InvalidJwtException("Invalid email or password");
        }
    
        try {
            if (!user.isAccountNonLocked()) {
                throw new AccountLockedException("Account is locked. Try again after 30 minutes.");
            }
    
            if (!new BCryptPasswordEncoder().matches(password, user.getPassword())) {
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
    

private void handleFailedLoginAttempt(User user) {
    user.setFailedLoginAttempts(user.getFailedLoginAttempts() + 1);
    user.setLastFailedLogin(LocalDateTime.now());
    
    if (user.getFailedLoginAttempts() >= 3) {
        // Lock the account for 30 minutes
        user.setAccountLockedUntil(LocalDateTime.now().plusMinutes(30));
    }
    
    userRepo.save(user);
}

private void resetFailedLoginAttempts(User user) {
    user.setFailedLoginAttempts(0);
    user.setLastFailedLogin(null);
    user.setAccountLockedUntil(null);
    userRepo.save(user);
}


    public User getUserByEmail(String email) {
        return userRepo.findByEmail(email);
    }

    public void updateUserVerificationStatus(String userEmail, boolean verified) {
        // Find the user by email
        User user = userRepo.findByEmail(userEmail);

        // Update the verification status
        if (user != null) {
            user.setVerified(verified);
            userRepo.save(user); // Save the updated user entity
        }
    }
}