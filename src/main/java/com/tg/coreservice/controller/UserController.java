package com.tg.coreservice.controller;

import com.tg.coreservice.auth.Auth;
import com.tg.coreservice.auth.UserContext;
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

@Tag(name = "USER", description = "유저 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Auth
    @GetMapping("/user")
    public ResponseEntity<Void> testAuthUser() {
        Long userId = UserContext.getContext();
        System.out.println("로그인한 사용자만 접근 가능한 컨트롤러");
        System.out.println("userId = " + userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/guest")
    public ResponseEntity<Void> testNoAuthUser() {
        System.out.println("Auth 어노테이션이 붙지 않은 컨트롤러는 비로그인한 사용자도 접근 가능");
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

