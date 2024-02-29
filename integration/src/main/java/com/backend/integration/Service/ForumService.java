package com.backend.integration.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.integration.Entity.Forum;
import com.backend.integration.Repo.ForumRepository;

@Service
public class ForumService {
     @Autowired
    private ForumRepository forumRepository;

    public List<Forum> getAllForums() {
        return forumRepository.findAll();
    }

    public Optional<Forum> getForumById(Long forum_id) {
        return forumRepository.findById(forum_id);
    }

    public Forum saverOrUpdateForum(Forum forum) {
        return forumRepository.save(forum);
    }

    public void deleteForum(Long forum_id) {
        forumRepository.deleteById(forum_id);
    }
}
