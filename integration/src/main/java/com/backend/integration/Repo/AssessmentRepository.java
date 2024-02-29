package com.backend.integration.Repo;


import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.integration.Entity.Assessment;

public interface AssessmentRepository extends JpaRepository<Assessment, Long> {

      
}
