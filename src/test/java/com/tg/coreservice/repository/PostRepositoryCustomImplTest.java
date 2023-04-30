package com.tg.coreservice.repository;

import com.tg.coreservice.domain.Post;
import com.tg.coreservice.domain.User;
import com.tg.coreservice.dto.FeedResponseDto;
import com.tg.coreservice.service.PostService;
import com.tg.coreservice.specification.PostCategory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

@SpringBootTest
class PostRepositoryCustomImplTest {
    @Autowired PostRepository postRepository;
    @Autowired
    PostService postService;
    @Autowired UserRepository userRepository;

    @Test
    @Rollback(value = false)
    void getFeed() {
        // generate 1 dummy user
        User user = userRepository.save(
                User.builder()
                        .nickname("hi")
                        .profileImageUrl("hi")
                        .providerId("hi")
                        .build());
        User user2 = userRepository.save(
                User.builder()
                        .nickname("hi")
                        .profileImageUrl("hi")
                        .providerId("hi2")
                        .build());

        // generate 20 dummy posts
        for (int i = 0; i < 20; i++) {
            postRepository.save(
                    Post.builder()
                            .user(userRepository.getReferenceById(user2.getId()))
                            .content("content" + i)
                            .category(PostCategory.ALGORITHM)
                            .imageUrl("imageUrl" + i)
                            .build()
            );
        }

        List<FeedResponseDto> feed = postService.getFeed(user.getId(), 0L, 10);
        System.out.println("feed.size() = " + feed.size());
        for (FeedResponseDto feedResponseDto : feed) {
            System.out.println(feedResponseDto.toString());
        }
    }
}