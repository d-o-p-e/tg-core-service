package com.tg.coreservice.controller;

import com.tg.coreservice.auth.Auth;
import com.tg.coreservice.auth.UserContext;
import com.tg.coreservice.dto.CreatePostRequestDto;
import com.tg.coreservice.dto.FeedResponseDto;
import com.tg.coreservice.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostController {

    private final PostService postService;

    @GetMapping("/feed")
    public ResponseEntity<List<FeedResponseDto>> getFeed(@RequestParam Long lastPostId, @RequestParam int size) {
        Long userId = UserContext.getContext();
        return new ResponseEntity<>(postService.getFeed(userId, lastPostId, size), HttpStatus.OK);
    }

    @Auth
    @PostMapping
    public ResponseEntity<Void> createPost(CreatePostRequestDto createPostRequestDto) {
        Long userId = UserContext.getContext();
        postService.create(userId, createPostRequestDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
