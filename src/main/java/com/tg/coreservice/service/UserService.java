package com.tg.coreservice.service;

import com.tg.coreservice.domain.Mileage;
import com.tg.coreservice.domain.User;
import com.tg.coreservice.dto.KakaoUserInformation;
import com.tg.coreservice.dto.UserInformationResponseDto;
import com.tg.coreservice.repository.MileageRepository;
import com.tg.coreservice.repository.PostRepository;
import com.tg.coreservice.repository.UserRepository;
import com.tg.coreservice.specification.PostCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final OAuthService oAuthService;
    private final PostRepository postRepository;
    private final MileageRepository mileageRepository;

    public Long login(String code) {
        KakaoUserInformation kakaoUserInformation = oAuthService.requestUserInformation(code);
        User user = getOrCreateUser(kakaoUserInformation);
        return user.getId();
    }

    @Transactional
    public User getOrCreateUser(KakaoUserInformation kakaoUserInformation) {
        Optional<User> optionalUser = userRepository.findByProviderId(kakaoUserInformation.getProviderId());
        if(optionalUser.isPresent()) {
            User user = optionalUser.get();
            return user;
        } else {
            User user = userRepository.save(User.builder()
                    .providerId(kakaoUserInformation.getProviderId())
                    .build());
            mileageRepository.save(new Mileage(user.getId(), 0));
            return user;
        }

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
                .mileage(mileageRepository.findById(userId).get().getMileage())
                .build();
    }
}
