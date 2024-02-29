package com.backend.integration.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.integration.Entity.Forum;

public interface ForumRepository extends JpaRepository<Forum, Long>{
    
}
