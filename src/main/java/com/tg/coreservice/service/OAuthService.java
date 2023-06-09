package com.tg.coreservice.service;

import com.tg.coreservice.client.KakaoAccessTokenFeignClient;
import com.tg.coreservice.client.KakaoInformationFeignClient;
import com.tg.coreservice.dto.KakaoAccessTokenResponseDto;
import com.tg.coreservice.dto.KakaoUserInformation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OAuthService {

    private final KakaoAccessTokenFeignClient kakaoAccessTokenFeignClient ;
    private final KakaoInformationFeignClient kakaoInformationFeignClient;

    @Value("${oauth.kakao.client-id}")
    private String kakaoClientId;

    @Value("${oauth.kakao.redirect-uri}")
    private String redirectUri;

    public KakaoUserInformation requestUserInformation(String code) {
        KakaoAccessTokenResponseDto result = kakaoAccessTokenFeignClient.call(
                "application/x-www-form-urlencoded",
                "authorization_code",
                kakaoClientId,
                redirectUri,
                code
        );
        return kakaoInformationFeignClient.call(
                "application/x-www-form-urlencoded;charset=utf-8",
                "Bearer " + result.getAccessToken()
        );
    }
}
