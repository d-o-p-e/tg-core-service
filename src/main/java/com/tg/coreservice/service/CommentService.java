package com.tg.coreservice.service;

import com.tg.coreservice.domain.Comment;
import com.tg.coreservice.domain.Post;
import com.tg.coreservice.domain.User;
import com.tg.coreservice.dto.CreateCommentRequestDto;
import com.tg.coreservice.repository.CommentRepository;
import com.tg.coreservice.repository.PostRepository;
import com.tg.coreservice.repository.PostRepositoryCustom;
import com.tg.coreservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    public void create(Long userId, Long postId, CreateCommentRequestDto createCommentRequestDto) {
        User user = userRepository.getReferenceById(userId);
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        commentRepository.save(
                Comment.builder()
                        .user(user)
                        .post(post)
                        .content(createCommentRequestDto.getContent())
                        .build()
        );
    }
}
