package com.tg.coreservice.controller;

import com.tg.coreservice.auth.Auth;
import com.tg.coreservice.auth.UserContext;
import com.tg.coreservice.dto.CreatePostRequestDto;
import com.tg.coreservice.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostController {

    private final PostService postService;

    @Auth
    @PostMapping
    public ResponseEntity<Void> createPost(CreatePostRequestDto createPostRequestDto) {
        Long userId = UserContext.getContext();
        postService.create(userId, createPostRequestDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
