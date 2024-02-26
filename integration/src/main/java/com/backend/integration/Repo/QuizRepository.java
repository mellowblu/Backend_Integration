package com.backend.integration.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.integration.Entity.Quiz;

public interface QuizRepository extends JpaRepository<Quiz, Long>{

}
