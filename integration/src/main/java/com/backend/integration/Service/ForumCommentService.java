package com.backend.integration.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.integration.Entity.Forum;
import com.backend.integration.Entity.ForumComment;
import com.backend.integration.Repo.ForumCommentRepository;
import com.backend.integration.Repo.ForumRepository;

@Service
public class ForumCommentService {
    @Autowired
    private ForumCommentRepository forumCommentRepository;

    public ForumCommentService(ForumCommentRepository forumCommentRepository) {
        this.forumCommentRepository = forumCommentRepository;
    }
    
    public List<ForumComment> getCommentsByForumId(Long forumId) {
        return forumCommentRepository.findByForumId(forumId);
    }

    public List<ForumComment> getAllForumComments() {
        return forumCommentRepository.findAll();
    }

    public Optional<ForumComment> getForumCommentById(Long fcomment_id) {
        return forumCommentRepository.findById(fcomment_id);
    }

    public ForumComment saverOrUpdateForumComment(ForumComment forumComment) {
        return forumCommentRepository.save(forumComment);
    }

    public void deleteForumComment(Long fcomment_id) {
        forumCommentRepository.deleteById(fcomment_id);
    }

}
