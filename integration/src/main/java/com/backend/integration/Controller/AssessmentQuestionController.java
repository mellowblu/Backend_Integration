package com.backend.integration.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.integration.Entity.AssessmentQuestion;
import com.backend.integration.Service.AssessmentQuestionService;

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin("http://localhost:5173")
public class AssessmentQuestionController {

     @Autowired
    private AssessmentQuestionService assessmentQuestionService;

    @GetMapping("/aquestion-by-assessment/{assessmentId}")
    public ResponseEntity<List<AssessmentQuestion>> getAssessmentQuestionsByAssessmentId(@PathVariable Long assessmentId) {
        List<AssessmentQuestion> assessmentQuestions = assessmentQuestionService.getAssessmentQuestionsByAssessmentId(assessmentId);
        return ResponseEntity.ok(assessmentQuestions);
    }

    @GetMapping("/assessmentquestions")
    public List<AssessmentQuestion> getAllAssessmentQuestions() {
        return assessmentQuestionService.getAllAssessmentQuestions();
    }

    @GetMapping("/assessmentquestion/{aquestion_id}")
    public ResponseEntity<AssessmentQuestion> getAssessmentQuestionById(@PathVariable Long aquestion_id) {
        Optional<AssessmentQuestion> assessmentQuestion = assessmentQuestionService.getAssessmentQuestionById(aquestion_id);
        return assessmentQuestion.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/assessmentquestion")
    public ResponseEntity<AssessmentQuestion> createAssessmentQuestion(@RequestBody AssessmentQuestion assessmentQuestion) {
        AssessmentQuestion createdAssessmentQuestion = assessmentQuestionService.saveOrUpdateAssessmentQuestion(assessmentQuestion);
        return new ResponseEntity<>(createdAssessmentQuestion, HttpStatus.CREATED);
    }

    @PutMapping("/assessmentquestion/{aquestion_id}")
    public ResponseEntity<AssessmentQuestion> updateAssessmentQuestion(@PathVariable Long aquestion_id, @RequestBody AssessmentQuestion aquestionDetails) {
        Optional<AssessmentQuestion> assessmentQuestion = assessmentQuestionService.getAssessmentQuestionById(aquestion_id);
        if (assessmentQuestion.isPresent()) {
            AssessmentQuestion updatedAssessmentQuestion = assessmentQuestionService.saveOrUpdateAssessmentQuestion(aquestionDetails);
            return new ResponseEntity<>(updatedAssessmentQuestion, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/assessmentquestion/{aquestion_id}")
    public ResponseEntity<Void> deleteAssessmentQuestion(@PathVariable Long aquestion_id) {
        assessmentQuestionService.deleteAssessmentQuestion(aquestion_id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
