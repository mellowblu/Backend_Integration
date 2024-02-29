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

import com.backend.integration.Entity.ForumComment;
import com.backend.integration.Service.ForumCommentService;


@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin("http://localhost:5173")
public class ForumCommentController {

    @Autowired
    private ForumCommentService forumCommentService;

    public ForumCommentController(ForumCommentService forumCommentService) {
        this.forumCommentService = forumCommentService;
    }

    @GetMapping("/comment-by-forum/{forumId}")
    public List<ForumComment> getCommentsByForumId(@PathVariable Long forumId) {
        return forumCommentService.getCommentsByForumId(forumId);
    }

    @GetMapping("/forumcomments")
    public List<ForumComment> getAllForumComments() {
        return forumCommentService.getAllForumComments();
    }

    @GetMapping("/forumcomment/{fcomment_id}")
    public ResponseEntity<ForumComment> getForumCommentById(@PathVariable Long fcomment_id) {
        Optional<ForumComment> comment = forumCommentService.getForumCommentById(fcomment_id);
        return comment.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/forumcomment")
    public ResponseEntity<ForumComment> createForumComment(@RequestBody ForumComment forumComment) {
        ForumComment createdForumComment = forumCommentService.saverOrUpdateForumComment(forumComment);
        return new ResponseEntity<>(createdForumComment, HttpStatus.CREATED);
    }

    @PutMapping("/forumcomment/{fcomment_id}")
    public ResponseEntity<ForumComment> updateForumComment(@PathVariable Long fcomment_id, @RequestBody ForumComment forumCommentDetails) {
        Optional<ForumComment> forumComment = forumCommentService.getForumCommentById(fcomment_id);
        if (forumComment.isPresent()) {
            ForumComment updatedForumComment = forumCommentService.saverOrUpdateForumComment(forumCommentDetails);
            return new ResponseEntity<>(updatedForumComment, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/forumcomment/{fcomment_id}")
    public ResponseEntity<Void> deleteForumComment(@PathVariable Long fcomment_id) {
        forumCommentService.deleteForumComment(fcomment_id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
