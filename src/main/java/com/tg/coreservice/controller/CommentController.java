package com.tg.coreservice.controller;

import com.tg.coreservice.auth.Auth;
import com.tg.coreservice.auth.UserContext;
import com.tg.coreservice.dto.CreateCommentRequestDto;
import com.tg.coreservice.service.CommentService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "COMMENT", description = "댓글 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/post/{postId}/comment")
public class CommentController {

    private final CommentService commentService;

    @Auth
    @PostMapping
    public ResponseEntity<Void> createComment(@PathVariable Long postId, CreateCommentRequestDto createCommentRequestDto) {
        Long userId = UserContext.getContext();
        commentService.create(userId, postId, createCommentRequestDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
