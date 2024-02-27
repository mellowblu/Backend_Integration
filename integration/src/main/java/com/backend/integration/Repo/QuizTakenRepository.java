package com.backend.integration.Repo;



import org.springframework.data.jpa.repository.JpaRepository;


import com.backend.integration.Entity.QuizTaken;

public interface QuizTakenRepository extends JpaRepository<QuizTaken, Long> {

    
}
