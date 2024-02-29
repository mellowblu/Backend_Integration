package com.backend.integration.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.integration.Entity.AssessmentQuestion;
import com.backend.integration.Repo.AssessmentQuestionRepository;


@Service
public class AssessmentQuestionService {

    @Autowired
    private AssessmentQuestionRepository assessmentQuestionRepository;

    public List<AssessmentQuestion> getAssessmentQuestionsByAssessmentId(Long assessmentId) {
        return assessmentQuestionRepository.findByAssessmentId(assessmentId);
    }

    public List<AssessmentQuestion> getAllAssessmentQuestions() {
        return assessmentQuestionRepository.findAll();
    }

    public Optional<AssessmentQuestion> getAssessmentQuestionById(Long aquestion_id) {
        return assessmentQuestionRepository.findById(aquestion_id);
    }

    public AssessmentQuestion saveOrUpdateAssessmentQuestion(AssessmentQuestion assessmentQuestion) {
        return assessmentQuestionRepository.save(assessmentQuestion);
    }

    public void deleteAssessmentQuestion(Long aquestion_id) {
        assessmentQuestionRepository.deleteById(aquestion_id);
    }

}
