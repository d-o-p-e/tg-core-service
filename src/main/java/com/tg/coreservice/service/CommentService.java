package com.tg.coreservice.service;

import com.tg.coreservice.domain.Comment;
import com.tg.coreservice.domain.Post;
import com.tg.coreservice.domain.User;
import com.tg.coreservice.dto.CommentResponseDto;
import com.tg.coreservice.dto.CreateCommentRequestDto;
import com.tg.coreservice.repository.CommentRepository;
import com.tg.coreservice.repository.PostRepository;
import com.tg.coreservice.repository.PostRepositoryCustom;
import com.tg.coreservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

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

    public List<CommentResponseDto> getCommentsList(Long postId) {
        List<Comment> commentList = commentRepository.findAllByPostIdFetch(postId);
        return commentList.stream().map(comment -> CommentResponseDto.builder()
                .commentId(comment.getId())
                .content(comment.getContent())
                .userId(comment.getUser().getId())
                .nickName(comment.getUser().getNickname())
                .profileImageUrl(comment.getUser().getProfileImageUrl())
                .build()
        ).collect(Collectors.toList());
    }

    @Transactional
    public void delete(Long userId, Long commentId) {
        int result = commentRepository.deleteByIdAndUserId(commentId, userId);
        if (result == 0) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
}
