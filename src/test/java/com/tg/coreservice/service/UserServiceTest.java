package com.tg.coreservice.service;

import com.tg.coreservice.domain.Post;
import com.tg.coreservice.domain.User;
import com.tg.coreservice.dto.UserInformationResponseDto;
import com.tg.coreservice.repository.PostRepository;
import com.tg.coreservice.repository.UserRepository;
import com.tg.coreservice.specification.PostCategory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserServiceTest {

    @Autowired
    PostRepository postRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired UserService userService;

    @Test
    void getUserInformation() {
        // save 1 dummy user
        User user = userRepository.save(
                User.builder()
                        .providerId("1234")
                        .nickname("test")
                        .profileImageUrl("test")
                        .build()
        );
        for (int i = 0; i < 1; i++) {
            postRepository.save(
                Post.builder()
                        .user(user)
                        .category(PostCategory.WORKOUT)
                        .build()
            );
        }
        for (int i = 0; i < 2; i++) {
            postRepository.save(
                Post.builder()
                        .user(user)
                        .category(PostCategory.ALGORITHM)
                        .build()
            );
        }
        for (int i = 0; i < 3; i++) {
            postRepository.save(
                Post.builder()
                        .user(user)
                        .category(PostCategory.EARLY_BIRD)
                        .build()
            );
        }

        UserInformationResponseDto userInformation = userService.getUserInformation(user.getId());
        System.out.println("userInformation = " + userInformation.toString());
    }
}