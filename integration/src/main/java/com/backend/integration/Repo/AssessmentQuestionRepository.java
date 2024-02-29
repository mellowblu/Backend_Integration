package com.backend.integration.Repo;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.backend.integration.Entity.AssessmentQuestion;


public interface AssessmentQuestionRepository extends JpaRepository<AssessmentQuestion, Long>{

    @Query("SELECT aq FROM AssessmentQuestion aq WHERE aq.assessment.assessment_id = :assessmentId")
    List<AssessmentQuestion> findByAssessmentId(@Param("assessmentId") Long assessmentId);
}
