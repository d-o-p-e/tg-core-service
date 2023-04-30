package com.tg.coreservice.service;

import com.tg.coreservice.domain.User;
import com.tg.coreservice.dto.KakaoUserInformation;
import com.tg.coreservice.dto.UserInformationResponseDto;
import com.tg.coreservice.repository.PostRepository;
import com.tg.coreservice.repository.UserRepository;
import com.tg.coreservice.specification.PostCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final OAuthService oAuthService;
    private final PostRepository postRepository;

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

    public UserInformationResponseDto getUserInformation(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        int workoutCount = postRepository.countByUserIdAndCategory(userId, PostCategory.WORKOUT);
        int algorithmCount = postRepository.countByUserIdAndCategory(userId, PostCategory.ALGORITHM);
        int earlyBirdCount = postRepository.countByUserIdAndCategory(userId, PostCategory.EARLY_BIRD);
        return UserInformationResponseDto.builder()
                .userId(user.getId())
                .nickname(user.getNickname())
                .profileImage(user.getProfileImageUrl())
                .workoutCount(workoutCount)
                .algorithmCount(algorithmCount)
                .earlyBirdCount(earlyBirdCount)
                .build();
    }
}
