package com.tg.coreservice.controller;

import com.tg.coreservice.auth.Auth;
import com.tg.coreservice.auth.UserContext;
import com.tg.coreservice.dto.CreatePostRequestDto;
import com.tg.coreservice.dto.FeedResponseDto;
import com.tg.coreservice.service.PostService;
import com.tg.coreservice.specification.FeedOption;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "POST", description = "게시물 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/post")
public class PostController {

    private final PostService postService;

    @Operation(summary = "게시물 조회", description = "페이징 방식으로 피드를 조회합니다.")
    @GetMapping("/feed")
    public ResponseEntity<List<FeedResponseDto>> getFeed(
            @RequestParam int size,
            @RequestParam(required = false) Long lastPostId,
            @RequestParam(required = false) Long targetUserId
    ) {
        Long userId = UserContext.getContext();
        FeedOption feedOption = new FeedOption(size, lastPostId, targetUserId);
        return ResponseEntity.ok().body(postService.getFeed(userId, feedOption));
    }

    @Operation(summary = "게시물 등록", description = "새로운 게시글을 작성합니다.")
    @Auth
    @PostMapping
    public ResponseEntity<Void> createPost(CreatePostRequestDto createPostRequestDto) {
        Long userId = UserContext.getContext();
        postService.create(userId, createPostRequestDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "게시글 삭제", description = "게시글을 삭제합니다.")
    @Auth
    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> deletePost(@PathVariable Long postId) {
        Long userId = UserContext.getContext();
        postService.delete(userId, postId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "좋아요", description = "게시글에 좋아요를 추가합니다.")
    @Auth
    @PostMapping("/{postId}/like")
    public ResponseEntity<Void> likePost(@PathVariable Long postId) {
        Long userId = UserContext.getContext();
        postService.like(userId, postId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "좋아요 취소", description = "게시글 좋아요를 취소합니다.")
    @Auth
    @DeleteMapping("/{postId}/like")
    public ResponseEntity<Void> unlikePost(@PathVariable Long postId) {
        Long userId = UserContext.getContext();
        postService.unlike(userId, postId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
