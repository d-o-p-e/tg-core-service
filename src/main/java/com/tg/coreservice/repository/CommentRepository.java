package com.tg.coreservice.repository;

import com.tg.coreservice.domain.Comment;
import com.tg.coreservice.dto.CommentResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query("select c from Comment c join c.user where c.post.id = :postId")
    List<Comment> findAllByPostIdFetch(Long postId);
}