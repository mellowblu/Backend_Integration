package com.backend.integration.Repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.integration.Entity.ForgotCodeEntity;
import com.backend.integration.Entity.User;

// Repository interface for accessing and managing ForgotCodeEntity entities in the database
public interface ForgotCodeRepo extends JpaRepository<ForgotCodeEntity, Long> {

    // Find a ForgotCodeEntity by its unique forgot code
    ForgotCodeEntity findByForgotCode(String forgotCode);

    // Find an optional ForgotCodeEntity by the associated user
    Optional<ForgotCodeEntity> findByUser(User user);

    // Find an optional ForgotCodeEntity by the email address of the associated user
    Optional<ForgotCodeEntity> findByUserEmail(String userEmail);
}
