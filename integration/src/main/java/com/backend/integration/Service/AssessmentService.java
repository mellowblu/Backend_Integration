package com.backend.integration.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.integration.Entity.Assessment;
import com.backend.integration.Repo.AssessmentRepository;


@Service
public class AssessmentService {
    @Autowired
    private AssessmentRepository assessmentRepository;

    public List<Assessment> getAllAssessments() {
        return assessmentRepository.findAll();
    }

    public Optional<Assessment> getAssessnentById(Long assessment_id) {
        return assessmentRepository.findById(assessment_id);
    }

    public Assessment saveOrUpdateAssessment(Assessment assessment) {
        return assessmentRepository.save(assessment);
    }

    public void deleteAssessment(Long assessment_id) {
        assessmentRepository.deleteById(assessment_id);
    }
}
