package com.tg.coreservice.controller;

import com.tg.coreservice.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/oauth/kakao")
    public ResponseEntity<Void> login(@RequestParam String code, HttpSession session) {
        Long userId = userService.login(code);
        session.setAttribute("userId", userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}

