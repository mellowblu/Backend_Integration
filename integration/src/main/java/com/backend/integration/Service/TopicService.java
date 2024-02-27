package com.backend.integration.Service;

import java.util.List;
import java.util.Optional;

// import java.io.File;
// import java.io.FileOutputStream;
// import java.io.IOException;
//import java.util.List;

import org.springframework.beans.factory.annotation.Autowired; // Importing annotation for dependency injection

import org.springframework.stereotype.Service; // Importing annotation to indicate this class as a service
//import org.springframework.web.bind.annotation.PathVariable; // Importing annotation to indicate a method parameter is bound to a URI template variable
//import org.springframework.web.bind.annotation.RequestBody; // Importing annotation to indicate a method parameter should be bound to the body of the web request

import com.backend.integration.Entity.Topic;
//import com.backend.integration.Entity.Topic;
//import com.backend.integration.Exceptions.TopicNotFoundException;
import com.backend.integration.Repo.TopicRepository;

@Service // Annotation to indicate this class as a service
public class TopicService {
    @Autowired // Annotation for dependency injection of TopicRepository bean
    private TopicRepository topicRepository; // Declaration of TopicRepository bean

    public List<Topic> getAllTopic() {
        return topicRepository.findAll();
    }

    public Optional<Topic> getTopicById(Long topic_id) {
        return topicRepository.findById(topic_id);
    }

    public Topic saveOrUpdateTopic(Topic topic) {
        return topicRepository.save(topic);
    }

    public void deleteTopic(Long topic_id) {
        topicRepository.deleteById(topic_id);
    }

    // Function to retrieve all topics
    // public List<Topic> getAllTopic() { // Method signature to retrieve all topics
    //     return topicRepository.findAll(); // referring to findAll() method of TopicRepository interface
    // }

    // // Function to retrieve a topic by its ID
    // public Topic getTopicById(Long topic_id) { // Method signature to retrieve a topic by its ID
    //     return topicRepository.findById(topic_id) // referring to findById() method of TopicRepository interface
    //             .orElseThrow(() -> new TopicNotFoundException(topic_id)); // Handling TopicNotFoundException
    // }


    // // Function to save a new topic
    // public Topic saveTopic(Topic newTopic) {
    //     System.out.println(newTopic.getTopic_id());
    //     System.out.println(newTopic.getTopic_title());
    //     System.out.println(newTopic.getTopic_description());
    //     System.out.println(newTopic.getTopic_file());
    //     System.out.println(newTopic.getTopic_link());
    //     return topicRepository.save(newTopic);
    // }

    // // Function to update a topic
    // // Method to update a topic
    // public Topic updateTopic(@RequestBody Topic newTopic, @PathVariable Long topic_id) {

    //     return topicRepository.findById(topic_id) // referring to findById() method of TopicRepository interface
    //             .map(topic -> { // Using map() to apply changes
    //                 topic.setTopic_title(newTopic.getTopic_title()); // Updating topic title
    //                 topic.setTopic_description(newTopic.getTopic_description()); // Updating topic description
    //                 // topic.setTopic_file(newTopic.getTopic_file()); // Updating topic file
    //                 topic.setTopic_link(newTopic.getTopic_link()); // Updating topic link
    //                 return topicRepository.save(topic); // Saving updated topic
    //             }).orElseThrow(() -> new TopicNotFoundException(topic_id)); // Handling TopicNotFoundException
    // }

    // // Function to delete a topic by its ID
    // // public String deleteTopic(@PathVariable Long topic_id) { // Method signature to delete a topic by its ID
    // //     if (!topicRepository.existsById(topic_id)) { // Checking if topic exists
    // //         throw new TopicNotFoundException(topic_id); // Throwing TopicNotFoundException if topic not found
    // //     }
    // //     topicRepository.deleteTopicById(topic_id); // Deleting topic
    // //     return "Topic with id " + topic_id + " has been successfully deleted"; // Returning success message
    // // }

    // // // Function to retrieve topics by chapter ID
    // // public List<Topic> getTopicByChapterId(Long chapter_id) { // Method signature to retrieve topics by chapter ID
    // //     return topicRepository.findByChapter_id(chapter_id); // referring to findByChapter_id() method of
    // //                                                          // TopicRepository interface
    // // }

}