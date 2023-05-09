package com.tg.coreservice.controller;

import com.tg.coreservice.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "AUTH", description = "인증/인가 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1")
public class AuthController {

    private final UserService userService;

    @Operation(summary = "카카오 로그인", description = "카카오 OAuth2.0 클백 URL입니다.")
    @GetMapping("/oauth/kakao")
    public ResponseEntity<Void> login(@RequestParam String code, HttpSession session) {
        Long userId = userService.login(code);
        session.setAttribute("userId", userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

