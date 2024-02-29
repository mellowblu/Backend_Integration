package com.backend.integration.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.backend.integration.Entity.ForumComment;

public interface ForumCommentRepository extends JpaRepository<ForumComment,Long> {
    @Query("SELECT fc FROM ForumComment fc WHERE fc.forum.forum_id = :forumId")
    List<ForumComment> findByForumId(Long forumId);
}
