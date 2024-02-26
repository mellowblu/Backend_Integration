package com.backend.integration.Controller; 

import java.io.IOException; 
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.http.HttpStatus; 
import org.springframework.http.ResponseEntity; 
import org.springframework.web.bind.annotation.CrossOrigin; 
import org.springframework.web.bind.annotation.DeleteMapping; 
import org.springframework.web.bind.annotation.GetMapping; 
import org.springframework.web.bind.annotation.PathVariable; 
import org.springframework.web.bind.annotation.PostMapping; 
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody; 
import org.springframework.web.bind.annotation.RequestMapping; 
import org.springframework.web.bind.annotation.RestController; 

import com.backend.integration.Entity.Topic;
import com.backend.integration.Service.TopicService;
import org.springframework.web.bind.annotation.RequestParam;
 

// Controller class for handling topic-related operations
@RestController
@RequestMapping("/api/v1/auth") // Base path for topic related operations
@CrossOrigin("http://localhost:5173") // Allowing requests from this origin
public class TopicController {

    @Autowired // Injection of TopicService dependency
    private TopicService topicService;

    // Method for handling file upload and topic creation
    @PostMapping("/createTopic") //orginal user
        Topic newTopic (@RequestBody Topic newTopic){
       return topicService.saveTopic(newTopic);
    }

    // Method for retrieving all topics
    @GetMapping("/getTopic")
    List<Topic> getAllTopic() { // Retrieves all topics
        return topicService.getAllTopic();
    }

    // Method for retrieving a topic by its ID
    @GetMapping("/topic/{topic_id}")
    Topic getTopicById(@PathVariable Long topic_id) { // Retrieves topic by its ID
        return topicService.getTopicById(topic_id);
    }

    // Method for updating a topic by its ID
    @PutMapping("/topic/{topic_id}")
    Topic updateTopic(@RequestBody Topic newTopic, @PathVariable Long topic_id) { // Updates topic by its ID
        return topicService.updateTopic(newTopic, topic_id);
    }

    // Method for deleting a topic by its ID
    @DeleteMapping("/topic/{topic_id}")
    String deleteTopic(@PathVariable Long topic_id) { // Deletes topic by its ID
        return topicService.deleteTopic(topic_id);
    }

    @GetMapping("/getTopic/byChapter/{chapter_id}")
    public List<Topic> getTopicByChapterId(@PathVariable Long chapter_id) { 
        return topicService.getTopicByChapterId(chapter_id);
    }
    
}
