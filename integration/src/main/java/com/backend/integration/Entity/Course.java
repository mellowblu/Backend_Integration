package com.backend.integration.Entity; // Package declaration

import java.sql.Date; // Import for Date class
import java.util.List; // Import for using List data structure

import com.fasterxml.jackson.annotation.JsonIgnore; // Import for JSON ignore annotation

import jakarta.persistence.CascadeType; // Importing CascadeType from jakarta.persistence package
import jakarta.persistence.Entity; // Importing Entity annotation from jakarta.persistence package
import jakarta.persistence.FetchType; // Importing FetchType from jakarta.persistence package
import jakarta.persistence.GeneratedValue; // Importing GeneratedValue from jakarta.persistence package
import jakarta.persistence.GenerationType; // Importing GenerationType from jakarta.persistence package
import jakarta.persistence.Id; // Importing Id annotation from jakarta.persistence package
import jakarta.persistence.OneToMany; // Importing OneToMany annotation from jakarta.persistence package

@Entity // Marks the class as an entity
public class Course {

    @Id // Marks the field as primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Specifies the generation strategy for the primary key
    private Long course_id; // Unique identifier for the course

    private String course_title; // Title of the course
    private String course_description; // Description of the course
    private Date course_start_date; // Start date of the course
    private Date course_end_date; // End date of the course

    @JsonIgnore // Ignores serialization and deserialization of this property
    // Mapping one-to-many relationship with Chapter entity
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Chapter> chapter; // List of chapters associated with the course

    public List<Chapter> getChapter() { // Getter method for chapters
        return this.chapter;
    }

    public void setChapter(List<Chapter> chapter) { // Setter method for chapters
        this.chapter = chapter;
    }

    // Method to add a chapter to the course
    @JsonIgnore // Ignores serialization and deserialization of this method
    public void addChapter(Chapter chapter) {
        chapter.setCourse(this); // Sets the course for the chapter
        this.getChapter().add(chapter); // Adds the chapter to the collection of chapters
    }

    // Getter and setter methods for course properties
    public Long getCourse_id() {
        return this.course_id;
    }

    public void setCourse_id(Long course_id) {
        this.course_id = course_id;
    }

    public String getCourse_title() {
        return this.course_title;
    }

    public void setCourse_title(String course_title) {
        this.course_title = course_title;
    }

    public String getCourse_description() {
        return this.course_description;
    }

    public void setCourse_description(String course_description) {
        this.course_description = course_description;
    }

    public Date getCourse_start_date() {
        return this.course_start_date;
    }

    public void setCourse_start_date(Date course_start_date) {
        this.course_start_date = course_start_date;
    }

    public Date getCourse_end_date() {
        return this.course_end_date;
    }

    public void setCourse_end_date(Date course_end_date) {
        this.course_end_date = course_end_date;
    }
}
