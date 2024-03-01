package com.backend.integration.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.integration.Entity.User;

// Repository interface for accessing and managing User entities in the database
public interface UserRepo extends JpaRepository<User, Long> {

    // Find a user by their unique username
    User findByUserName(String userName);

    // Find a user by their unique email address
    User findByEmail(String email);

    // Check if a user exists with the given email address
    boolean existsByEmail(String email);

    // Check if a user exists with the given username
    boolean existsByUserName(String userName);

    // Check if a user exists with the given phone number
    boolean existsByPhoneNumber(String phoneNumber);
}
