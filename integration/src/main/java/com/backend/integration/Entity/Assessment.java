package com.backend.integration.Entity;

import jakarta.persistence.Column;
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

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    
    private String assessment_title;

     @Column(name="question", length = 1000)
    private String question;

    @Column(name="correct_answer", length = 1000)
    private String correct_answer;

    @Column(name="choice_1", length = 1000)
    private String choice_1;

    @Column(name="choice_2", length = 1000)
    private String choice_2;

    @Column(name="choice_3", length = 1000)
    private String choice_3;

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

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getAssessment_title() {
        return this.assessment_title;
    }

    public void setAssessment_title(String assessment_title) {
        this.assessment_title = assessment_title;
    }

    public String getQuestion() {
        return this.question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getCorrect_answer() {
        return this.correct_answer;
    }

    public void setCorrect_answer(String correct_answer) {
        this.correct_answer = correct_answer;
    }

    public String getChoice_1() {
        return this.choice_1;
    }

    public void setChoice_1(String choice_1) {
        this.choice_1 = choice_1;
    }

    public String getChoice_2() {
        return this.choice_2;
    }

    public void setChoice_2(String choice_2) {
        this.choice_2 = choice_2;
    }

    public String getChoice_3() {
        return this.choice_3;
    }

    public void setChoice_3(String choice_3) {
        this.choice_3 = choice_3;
    }

   
    
}
