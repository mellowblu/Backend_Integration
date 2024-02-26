package com.backend.integration.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long question_id;

    @Column(name="question", length = 1000)
    private String question;
    
    @ManyToOne
    @JoinColumn(name = "quiz_id")
    private Quiz quiz;

    @Column(name="correct_answer", length = 1000)
    private String correct_answer;

    @Column(name="choice_1", length = 1000)
    private String choice_1;

    @Column(name="choice_2", length = 1000)
    private String choice_2;

    @Column(name="choice_3", length = 1000)
    private String choice_3;


    public Long getQuestion_id() {
        return this.question_id;
    }

    public void setQuestion_id(Long question_id) {
        this.question_id = question_id;
    }

    public String getQuestion() {
        return this.question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Quiz getQuiz() {
        return this.quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
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
