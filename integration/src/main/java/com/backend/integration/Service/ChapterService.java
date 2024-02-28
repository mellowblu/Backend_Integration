package com.backend.integration.Service;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service; 
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.backend.integration.Entity.Chapter;
import com.backend.integration.Entity.Topic;
import com.backend.integration.Exceptions.ChapterNotFoundException;
import com.backend.integration.Repo.ChapterRepository;
import com.backend.integration.Repo.TopicRepository;

import jakarta.transaction.Transactional;

@Service // Annotation to indicate this class as a service
public class ChapterService {
    @Autowired // Annotation for dependency injection of ChapterRepository bean
    private ChapterRepository chapterRepository; // Declaration of ChapterRepository bean

    @Autowired
    private TopicRepository topicRepository;

    // Function to retrieve all chapters
    public List<Chapter> getAllChapter() { // Method signature to retrieve all chapters
        return chapterRepository.findAll(); // refers to findAll() method of ChapterRepository interface
    }

    // Function to retrieve a chapter by its ID
    public Chapter getChapterById(Long chapter_id) { // Method signature to retrieve a chapter by its ID
        return chapterRepository.findById(chapter_id) // refers to findById() method of ChapterRepository interface
                .orElseThrow(() -> new ChapterNotFoundException(chapter_id)); // Handling ChapterNotFoundException
    }

    // Function to update a chapter
    public Chapter updateChapter(@RequestBody Chapter newChapter, @PathVariable Long chapter_id) { // Method signature to update a chapter
        return chapterRepository.findById(chapter_id) // refers to findById() method of ChapterRepository interface
                .map(chapter -> { // Using map() to apply changes
                    chapter.setChapter_title(newChapter.getChapter_title()); // Updating chapter title
                    chapter.setChapter_date_created(newChapter.getChapter_date_created()); // Updating chapter creation date
                    return chapterRepository.save(chapter); // Saving updated chapter
                }).orElseThrow(() -> new ChapterNotFoundException(chapter_id)); // Handling ChapterNotFoundException
    }

    // Function to delete a chapter by its ID
    // @Transactional
    // public String deleteChapter(Long chapterId) {
    //     // Delete associated topics
    //     int deletedTopicsCount = topicRepository.deleteByChapterId(chapterId);

    //     // Delete the chapter
    //     chapterRepository.deleteById(chapterId);

    //     if (deletedTopicsCount == 0) {
    //         throw new ChapterNotFoundException(chapterId);
    //     }

    //     return "Chapter with id " + chapterId + " and its associated topics have been successfully deleted";
    // }

    // Function to retrieve chapters by course ID
    public List<Chapter> getChapterByCourseId(Long course_id) { // Method signature to retrieve chapters by course ID
        return chapterRepository.findByCourse_id(course_id); // refers to findByCourse_id() method of ChapterRepository interface
    }

    //febraury 13 2024
    // Function to save a new chapter
    public Chapter saveChapter(@RequestBody Chapter newChapter) { // Method signature to save a new chapter
        // Printing chapter details
        System.out.println("Chapter Title: " + newChapter.getChapter_title());
        System.out.println("Chapter Date Created: " + newChapter.getChapter_date_created());
        return chapterRepository.save(newChapter);
    }

    // Function to add a topic to a chapter
    public Chapter addTopicToChapter(Long chapter_id, Topic topic) { // Method signature to add a topic to a chapter
        Chapter chapter = chapterRepository.findById(chapter_id).orElse(null); // Retrieving chapter by ID
        if (chapter != null) { // Checking if chapter exists
            chapter.addTopic(topic); // Adding topic to chapter
            return chapterRepository.save(chapter); // Saving updated chapter
        } throw new ChapterNotFoundException(chapter_id); // Returning error message if chapter not found
    }

    @Transactional
    public String deleteChapter(Long chapterId) {
        Chapter chapter = chapterRepository.findById(chapterId)
                .orElseThrow(() -> new ChapterNotFoundException(chapterId));

        List<Topic> topics = chapter.getTopic();
        for (Topic topic : topics) {
            topicRepository.delete(topic);
        }

        chapterRepository.delete(chapter);

        return "Chapter with id " + chapterId + " and its associated topics have been successfully deleted";
    }
    //febraury 13 2024
}