package com.tg.coreservice.repository;

import com.tg.coreservice.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
