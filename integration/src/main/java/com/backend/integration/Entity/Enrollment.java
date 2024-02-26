package com.backend.integration.Entity;


import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;


@Entity
public class Enrollment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long enrollmentId;

    private String enrollmentStatus;
    
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user_id;


    @Column(name="start_date", nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDate starDate;


    public Long getEnrollmentId() {
        return this.enrollmentId;
    }

    public void setEnrollmentId(Long enrollmentId) {
        this.enrollmentId = enrollmentId;
    }

    public String getEnrollmentStatus() {
        return this.enrollmentStatus;
    }

    public void setEnrollmentStatus(String enrollmentStatus) {
        this.enrollmentStatus = enrollmentStatus;
    }

    public Course getCourse() {
        return this.course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public User getUser() { 
        return this.user_id;
    }

    public void setUser(User user_id) {
        this.user_id = user_id;
    }

    public LocalDate getStarDate() {
        return this.starDate;
    }

    public void setStarDate(LocalDate starDate) {
        this.starDate = starDate;
    }
   
}
