package com.backend.integration.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.integration.Entity.Enrollment;
import com.backend.integration.Service.EnrollmentService;

@RestController
@RequestMapping("/api/v1/auth")
public class EnrollmentController {

    @Autowired
    private EnrollmentService enrollmentService;

    @GetMapping("/enrollments")
    public List<Enrollment> getAllEnrollments() {
        return enrollmentService.getAllEnrollments();
    }

    @GetMapping("/{enrollmentId}")
    public ResponseEntity<Enrollment> getEnrollmentById(@PathVariable Long enrollmentId) {
        Optional<Enrollment> enrollment = enrollmentService.getEnrollmentById(enrollmentId);
        return enrollment.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/{enroll}")
    public ResponseEntity<Enrollment> createEnrollment(@RequestBody Enrollment enrollment) {
        Enrollment createdEnrollment = enrollmentService.saveOrUpdateEnrollment(enrollment);
        return new ResponseEntity<>(createdEnrollment, HttpStatus.CREATED);
    }

    @PutMapping("/{enrollmentId}")
    public ResponseEntity<Enrollment> updateEnrollment(@PathVariable Long enrollmentId, @RequestBody Enrollment enrollmentDetails) {
        Optional<Enrollment> enrollment = enrollmentService.getEnrollmentById(enrollmentId);
        if (enrollment.isPresent()) {
            Enrollment updatedEnrollment = enrollmentService.saveOrUpdateEnrollment(enrollmentDetails);
            return new ResponseEntity<>(updatedEnrollment, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{enrollmentId}")
    public ResponseEntity<Void> deleteEnrollment(@PathVariable Long enrollmentId) {
        enrollmentService.deleteEnrollment(enrollmentId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
