package com.backend.integration.Repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.integration.Entity.VerificationCodeEntity;

// Repository interface for accessing and managing VerificationCodeEntity entities in the database
public interface VerificationCodeRepo extends JpaRepository<VerificationCodeEntity, Long> {

    // Find a verification code entity by user email
    Optional<VerificationCodeEntity> findByUserEmail(String userEmail);
}
