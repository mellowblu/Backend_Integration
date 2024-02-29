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
public class ForumComment {

    @Id
    @GeneratedValue

    private Long fcomment_id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name="comment", length = 1000)
    private String comment;

    @Column(name="date_posted", nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDate datePosted;

    @ManyToOne
    @JoinColumn(name = "forum_id")
    private Forum forum;


    public Long getFcomment_id() {
        return this.fcomment_id;
    }

    public void setFcomment_id(Long fcomment_id) {
        this.fcomment_id = fcomment_id;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getComment() {
        return this.comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDate getDatePosted() {
        return this.datePosted;
    }

    public void setDatePosted(LocalDate datePosted) {
        this.datePosted = datePosted;
    }

    public Forum getForum() {
        return this.forum;
    }

    public void setForum(Forum forum) {
        this.forum = forum;
    }

}
