package com.backend.integration.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Topic {
    @Id
    @GeneratedValue
    private Long topic_id; // Unique identifier for the topic
    private String topic_title; // Title of the topic
    private String topic_description; // Description of the topic
    private byte[] topic_file; // File associated with the topic
    private String topic_link; // Link associated with the topic

    
    // JPA relationship one-to-many between Chapter and Topic
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name ="chapter_id")
    private Chapter chapter; // Associated chapter for the topic

    public Chapter getChapter() {
        return this.chapter; // Getter for associated chapter
    }

    public void setChapter(Chapter chapter) {
        this.chapter = chapter; // Setter for associated chapter
    }

    public Long getTopic_id() {
        return this.topic_id; // Getter for topic_id
    }

    public void setTopic_id(Long topic_id) {
        this.topic_id = topic_id; // Setter for topic_id
    }

    public String getTopic_title() {
        return this.topic_title; // Getter for topic_title
    }

    public void setTopic_title(String topic_title) {
        this.topic_title = topic_title; // Setter for topic_title
    }

    public String getTopic_description() {
        return this.topic_description; // Getter for topic_description
    }

    public void setTopic_description(String topic_description) {
        this.topic_description = topic_description; // Setter for topic_description
    }

    public String getTopic_link() {
        return this.topic_link; // Getter for topic_link
    }

    public void setTopic_link(String topic_link) {
        this.topic_link = topic_link; // Setter for topic_link
    }
    public byte[] getTopic_file() {
        return this.topic_file;
    }

    public void setTopic_file(byte[] topic_file) {
        this.topic_file = topic_file;
    }
}
