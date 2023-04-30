package com.tg.coreservice.controller;

import com.tg.coreservice.auth.Auth;
import com.tg.coreservice.auth.UserContext;
import com.tg.coreservice.dto.UserInformationResponseDto;
import com.tg.coreservice.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "USER", description = "유저 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Operation(summary = "유저 정보 조회", description = "특정 유저의 정보를 조회합니다. (빈 값이면 현재 로그인한 유저의 정보를 조회합니다.)")
    @GetMapping("/{targetUserId}")
    public ResponseEntity<UserInformationResponseDto> login(@PathVariable(required = false) Long targetUserId) {
        if (targetUserId == null) {
            targetUserId = UserContext.getContext();
            if(targetUserId == null) return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        return ResponseEntity.ok().body(userService.getUserInformation(targetUserId));
    }

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

