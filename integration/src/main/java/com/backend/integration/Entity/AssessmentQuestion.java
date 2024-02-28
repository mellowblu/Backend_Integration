package com.backend.integration.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class AssessmentQuestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long aquestion_id;

    @ManyToOne
    @JoinColumn(name = "assessment_id")
    private Assessment assessment;

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


    public Long getAquestion_id() {
        return this.aquestion_id;
    }

    public void setAquestion_id(Long aquestion_id) {
        this.aquestion_id = aquestion_id;
    }

    public Assessment getAssessment() {
        return this.assessment;
    }

    public void setAssessment(Assessment assessment) {
        this.assessment = assessment;
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
