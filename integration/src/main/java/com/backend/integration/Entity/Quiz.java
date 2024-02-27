package com.backend.integration.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long quiz_id;
    private String quiz_title;
    
    @Column(name="quiz_description", length = 1000)
    private String quiz_description;
    
    @ManyToOne
    @JoinColumn(name = "chapter_id")
    private Chapter chapter;

    private Integer target_score;

    public Long getQuiz_id() {
        return this.quiz_id;
    }

    public void setQuiz_id(Long quiz_id) {
        this.quiz_id = quiz_id;
    }

    public String getQuiz_title() {
        return this.quiz_title;
    }

    public void setQuiz_title(String quiz_title) {
        this.quiz_title = quiz_title;
    }

    public String getQuiz_description() {
        return this.quiz_description;
    }

    public void setQuiz_description(String quiz_description) {
        this.quiz_description = quiz_description;
    }

    public Chapter getChapter() {
        return this.chapter;
    }

    public void setChapter(Chapter chapter) {
        this.chapter = chapter;
    }

    public Integer getTarget_score() {
        return this.target_score;
    }

    public void setTarget_score(Integer target_score) {
        this.target_score = target_score;
    }

}
