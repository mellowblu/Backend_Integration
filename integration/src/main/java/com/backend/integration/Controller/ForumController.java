package com.backend.integration.Controller;

import java.util.List;
import java.util.Optional;

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

import com.backend.integration.Entity.Forum;
import com.backend.integration.Service.ForumService;


@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin("http://localhost:5173")
public class ForumController {

    @Autowired
    private ForumService forumService;

    @GetMapping("/forums")
    public List<Forum> getAllForums() {
        return forumService.getAllForums();
    }

    @GetMapping("/forum/{forum_id}")
    public ResponseEntity<Forum> getForumById(@PathVariable Long forum_id) {
        Optional<Forum> forum = forumService.getForumById(forum_id);
        return forum.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/forum")
    public ResponseEntity<Forum> createForum(@RequestBody Forum forum) {
        Forum createdForum = forumService.saverOrUpdateForum(forum);
        return new ResponseEntity<>(createdForum, HttpStatus.CREATED);
    }

    @PutMapping("/forum/{forum_id}")
    public ResponseEntity<Forum> updateForum(@PathVariable Long forum_id, @RequestBody Forum forumDetails) {
        Optional<Forum> forum = forumService.getForumById(forum_id);
        if (forum.isPresent()) {
            Forum updatedForum = forumService.saverOrUpdateForum(forumDetails);
            return new ResponseEntity<>(updatedForum, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/forum/{forum_id}")
    public ResponseEntity<Void> deleteForum(@PathVariable Long forum_id) {
        forumService.deleteForum(forum_id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
