package com.tg.coreservice.service;

import com.tg.coreservice.domain.User;
import com.tg.coreservice.dto.KakaoUserInformation;
import com.tg.coreservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final OAuthService oAuthService;

    public Long login(String authorizationCode) {
        KakaoUserInformation kakaoUserInformation = oAuthService.requestUserInformation(authorizationCode);
        User user = getOrCreateUser(kakaoUserInformation);
        return user.getId();
    }

    private User getOrCreateUser(KakaoUserInformation kakaoUserInformation) {
        System.out.println("kakaoUserInformation.getProviderId() = " + kakaoUserInformation.getProviderId());
        return userRepository.findByProviderId(kakaoUserInformation.getProviderId())
                .orElseGet(() -> userRepository.save(
                                User.builder()
                                        .providerId(kakaoUserInformation.getProviderId())
                                        .build()
                        )
                );
    }
}
