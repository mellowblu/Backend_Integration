package com.backend.integration.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.integration.Entity.Question;

public interface QuestionRepository extends JpaRepository<Question, Long> {

}
