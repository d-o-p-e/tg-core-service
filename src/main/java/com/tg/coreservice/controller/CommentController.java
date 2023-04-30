package com.tg.coreservice.controller;

import com.tg.coreservice.auth.Auth;
import com.tg.coreservice.auth.UserContext;
import com.tg.coreservice.dto.CommentResponseDto;
import com.tg.coreservice.dto.CreateCommentRequestDto;
import com.tg.coreservice.service.CommentService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "COMMENT", description = "댓글 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/post/{postId}/comment")
public class CommentController {

    private final CommentService commentService;

    @GetMapping
    public ResponseEntity<List<CommentResponseDto>> getComment(@PathVariable Long postId) {
        return ResponseEntity.ok().body(commentService.getCommentsList(postId));
    }

    @Auth
    @PostMapping
    public ResponseEntity<Void> createComment(@PathVariable Long postId, CreateCommentRequestDto createCommentRequestDto) {
        Long userId = UserContext.getContext();
        commentService.create(userId, postId, createCommentRequestDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long commentId, @PathVariable String postId) {
        Long userId = UserContext.getContext();
        commentService.delete(userId, commentId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
