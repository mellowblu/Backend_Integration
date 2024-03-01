package com.backend.integration.Entity;

import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
@Entity
public class VerificationCodeEntity {

    // Primary key for the VerificationCodeEntity
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Ensure verification codes are unique
    @Column(unique = true)
    private String verificationCode;

    // Expiration time in milliseconds
    private long expirationTimeInMillis;

    // Expiration time as Instant
    private Instant expirationTime;

    // Many-to-One relationship with User entity, mapped by the "user" attribute in User entity
    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    // Default constructor
    public VerificationCodeEntity() {
        // Default constructor
    }

    // Updated constructor to include user, verification code, and expiration time
    public VerificationCodeEntity(User user, String verificationCode, long expirationTimeInMillis) {
        this.user = user;
        this.verificationCode = verificationCode;
        this.expirationTimeInMillis = expirationTimeInMillis;
        this.expirationTime = Instant.ofEpochMilli(expirationTimeInMillis);
    }

    // Getters and setters

    // Get the primary key (ID) of the VerificationCodeEntity
    public Long getId() {
        return id;
    }

    // Set the primary key (ID) of the VerificationCodeEntity
    public void setId(Long id) {
        this.id = id;
    }

    // Get the verification code
    public String getVerificationCode() {
        return verificationCode;
    }

    // Set the verification code
    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    // Get the expiration time in milliseconds
    public long getExpirationTimeInMillis() {
        return expirationTimeInMillis;
    }

    // Set the expiration time in milliseconds
    public void setExpirationTimeInMillis(long expirationTimeInMillis) {
        this.expirationTimeInMillis = expirationTimeInMillis;
    }

    // Get the associated user
    public User getUser() {
        return user;
    }

    // Set the associated user
    public void setUser(User user) {
        this.user = user;
    }

    // Get the expiration time as Instant
    public Instant getExpirationTime() {
        return expirationTime;
    }
}
