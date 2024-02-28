package com.backend.integration.Entity; // Package declaration


import java.sql.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType; // Importing CascadeType from jakarta.persistence package
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long course_id; // Unique identifier for the course

    private String course_title; // Title of the course
    private String course_description; // Description of the course
    private Date course_date_created; 


    //  @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id") // Defines the foreign key column in the cOURSE table
    private User instructor; // Associated instructor for the course

    public User getInstructor() {
        return this.instructor;
    }

    public void setInstructor(User instructor) {
        this.instructor = instructor;
    }

    // Mapping many-to-one relationship from Chapter
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Chapter> chapter; // List of chapters associated with the course

    public List<Chapter> getChapter() {
        return this.chapter;
    }

    public void setChapter(List<Chapter> chapter) {
        this.chapter = chapter;
    }

    // Used in adding chapter inside course
    @JsonIgnore
    public void addChapter(Chapter chapter) {
        chapter.setCourse(this); // Set the course for the chapter
        this.getChapter().add(chapter); // Add the chapter to the collection of chapters
    }

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
    
    public Date getCourse_date_created() {
        return this.course_date_created;
    }

    public void setCourse_date_created(Date course_date_created) {
        this.course_date_created = course_date_created;
    }


}

