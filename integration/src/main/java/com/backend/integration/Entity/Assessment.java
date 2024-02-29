package com.backend.integration.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Assessment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long assessment_id;
    
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    
    private String assessment_title;


    public Long getAssessment_id() {
        return this.assessment_id;
    }

    public void setAssessment_id(Long assessment_id) {
        this.assessment_id = assessment_id;
    }

    public Course getCourse() {
        return this.course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public String getAssessment_title() {
        return this.assessment_title;
    }

    public void setAssessment_title(String assessment_title) {
        this.assessment_title = assessment_title;
    }
    

 
   
    
}
