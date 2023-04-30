package com.tg.coreservice.repository;

import com.tg.coreservice.domain.Post;
import com.tg.coreservice.specification.PostCategory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PostRepositoryCustomImplTest {
    @Autowired PostRepository postRepository;
    @Autowired UserRepository userRepository;

    @Test
    void getFeed() {
        // generate 20 dummy posts
        for (int i = 0; i < 20; i++) {
            postRepository.save(
                    Post.builder()
                            .user(userRepository.getReferenceById(1L))
                            .content("content" + i)
                            .category(PostCategory.ALGORITHM)
                            .imageUrl("imageUrl" + i)
                            .build()
            );
        }

        postRepository.getFeed(userRepository.getReferenceById(1L), 0L, 10).forEach(System.out::println);
    }
}