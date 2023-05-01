package com.tg.coreservice.controller;

import com.tg.coreservice.dto.KakaoAccessTokenResponseDto;
import com.tg.coreservice.service.OAuthService;
import com.tg.coreservice.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "AUTH", description = "인증/인가 API")
@RestController
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final OAuthService oAuthService;

    @Operation(summary = "카카오 로그인", description = "카카오 OAuth2.0 클백 URL입니다.")
    @GetMapping("/oauth/kakao")
    public ResponseEntity<Void> login(@RequestParam String code, HttpSession session) {
        Long userId = userService.login(code);
        session.setAttribute("userId", userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "카카오 토큰 발급", description = "인가코드를 통해 엑세스 토큰을 받습니다.")
    @GetMapping("/oauth/kakao/code")
    public ResponseEntity<KakaoAccessTokenResponseDto> requestKakaoAccessCode(@RequestParam String code) {
        return ResponseEntity.ok().body(oAuthService.accessTokenRequestToken(code));
    }

    @Operation(summary = "카카오 최종 최종 최종 로그인", description = "인가코드를 통해 엑세스 토큰을 받습니다.")
    @GetMapping("/oauth/kakao/token")
    public ResponseEntity<KakaoAccessTokenResponseDto> loginWithToken(@RequestParam String token, HttpSession session) {
        Long userId = userService.login(token);
        session.setAttribute("userId", userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

