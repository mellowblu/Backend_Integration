package com.backend.integration.Entity;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Forum {

    @Id
    @GeneratedValue

    private Long forum_id;
    
    @Column(name="forum_title", length = 1000)
    private String forum_title;

    @Column(name="forum_content", length = 2500)
    private String forum_content;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    @Column(name="date_posted", nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDate datePosted;


    public Long getForum_id() {
        return this.forum_id;
    }

    public void setForum_id(Long forum_id) {
        this.forum_id = forum_id;
    }

    public String getForum_title() {
        return this.forum_title;
    }

    public void setForum_title(String forum_title) {
        this.forum_title = forum_title;
    }

    public String getForum_content() {
        return this.forum_content;
    }

    public void setForum_content(String forum_content) {
        this.forum_content = forum_content;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDate getDatePosted() {
        return this.datePosted;
    }

    public void setDatePosted(LocalDate datePosted) {
        this.datePosted = datePosted;
    }
    
}
