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

import com.backend.integration.Entity.Assessment;
import com.backend.integration.Service.AssessmentService;

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin("http://localhost:5173")
public class AssessmentController {

    @Autowired
    private AssessmentService assessmentService;

    @GetMapping("/assessments")
    public List<Assessment> getAllAssessments() {
        return assessmentService.getAllAssessments();
    }

    @GetMapping("/assessment/{assessment_id}")
    public ResponseEntity<Assessment> getAssessmentbyId(@PathVariable Long assessment_id) {
        Optional<Assessment> assessment = assessmentService.getAssessnentById(assessment_id);
        return assessment.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/assessment")
    public ResponseEntity<Assessment> createAssessment(@RequestBody Assessment assessment) {
        Assessment createdAssessment = assessmentService.saveOrUpdateAssessment(assessment);
        return new ResponseEntity<>(createdAssessment, HttpStatus.CREATED);
    }

    @PutMapping("/assessment/{assessment_id}")
    public ResponseEntity<Assessment> updateAssessment(@PathVariable Long assessment_id, @RequestBody Assessment assessmentDetails) {
        Optional<Assessment> assessment = assessmentService.getAssessnentById(assessment_id);
        if (assessment.isPresent()) {
            Assessment updatedAssessment = assessmentService.saveOrUpdateAssessment(assessmentDetails);
            return new ResponseEntity<>(updatedAssessment, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/assessment/{assessment_id}")
    public ResponseEntity<Void> deleteAssessment(@PathVariable Long assessment_id) {
        assessmentService.deleteAssessment(assessment_id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
