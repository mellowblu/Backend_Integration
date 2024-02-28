package com.backend.integration.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.integration.Entity.AssessmentQuestion;

public interface AssessmentQuestionRepository extends JpaRepository<AssessmentQuestion, Long>{

}
