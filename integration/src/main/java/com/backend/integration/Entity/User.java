package com.backend.integration.Entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.backend.integration.Entity.Enums.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "user")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "user_id")
public class User implements UserDetails {

    // Primary key for the User entity
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_id;

    // Ensure emails are unique
    @Column(unique = true)
    private String email;

    // Ensure phone numbers are unique
    @Column(unique = true)
    private String phoneNumber;

    // Ensure usernames are unique
    @Column(unique = true)
    private String userName;

    private String password;

    private String firstName;

    private String lastName;

    // Binary Large Objects for storing profile picture and signature data
    @Lob
    @Column(name = "profile_picture", columnDefinition = "BLOB")
    private byte[] profilePicture;

    @Lob
    @Column(name = "signature_data", columnDefinition = "BLOB")
    private byte[] signatureData;

    // Enumeration representing the role of the user (INSTRUCTOR or STUDENT)
    @Enumerated(EnumType.STRING)
    private Role role;

    // Flag indicating whether the user is verified
    @Column(name = "is_verified")
    private boolean isVerified;

    // Number of failed login attempts
    @Column(name = "failed_login_attempts")
    private int failedLoginAttempts;

    // Timestamp of the last failed login attempt
    @Column(name = "last_failed_login")
    private LocalDateTime lastFailedLogin;

    // Timestamp until which the account is locked
    @Column(name = "account_locked_until")
    private LocalDateTime accountLockedUntil;

    // Transient field to exclude from database mapping
    @Transient
    private String imageType;

    // One-to-One relationship with ForgotCodeEntity, mapped by the "user" attribute in ForgotCodeEntity
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private ForgotCodeEntity forgotCodeEntity;

    // One-to-One relationship with VerificationCodeEntity, mapped by the "user" attribute in VerificationCodeEntity
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private VerificationCodeEntity verificationCodeEntity;

    // Constructor for creating a new User
    public User(String email, String userName, String password, String firstName, String lastName, String phoneNumber, Role role) {
        this.email = email;
        this.userName = userName;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.role = role;
        this.isVerified = false; // Newly registered users are not verified by default
    }

    // Spring Security UserDetails interface methods

    // Get the authorities (roles) assigned to the user
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER")); // All users have ROLE_USER by default

        if (this.role == Role.INSTRUCTOR) {
            authorities.add(new SimpleGrantedAuthority("ROLE_INSTRUCTOR"));
        } else if (this.role == Role.STUDENT) {
            authorities.add(new SimpleGrantedAuthority("ROLE_STUDENT"));
        }

        return authorities;
    }

    // Get the username of the user
    @Override
    public String getUsername() {
        return userName;
    }

    // Check if the user account is non-expired
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // Check if the user account is non-locked
    @Override
    public boolean isAccountNonLocked() {
        return accountLockedUntil == null || LocalDateTime.now().isAfter(accountLockedUntil);
    }

    // Check if the user credentials are non-expired
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // Check if the user account is enabled
    @Override
    public boolean isEnabled() {
        return isVerified; // Only enable the account if it is verified
    }

    // Getter and setter for VerificationCodeEntity
    public VerificationCodeEntity getVerificationCodeEntity() {
        return verificationCodeEntity;
    }

    public void setVerificationCodeEntity(VerificationCodeEntity verificationCodeEntity) {
        this.verificationCodeEntity = verificationCodeEntity;
    }

    // Getter and setter for imageType
    public String getImageType() {
        return imageType;
    }

    public void setImageType(String imageType) {
        this.imageType = imageType;
    }

    // Getter and setter for ForgotCodeEntity
    public ForgotCodeEntity getForgotCodeEntity() {
        return forgotCodeEntity;
    }

    public void setForgotCodeEntity(ForgotCodeEntity forgotCodeEntity) {
        this.forgotCodeEntity = forgotCodeEntity;
    }
}
