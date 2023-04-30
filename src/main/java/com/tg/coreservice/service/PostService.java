package com.tg.coreservice.service;

import com.tg.coreservice.domain.Post;
import com.tg.coreservice.dto.CreatePostRequestDto;
import com.tg.coreservice.repository.PostRepository;
import com.tg.coreservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostService {

    private final AwsS3Service awsS3Service;
    private final PostRepository postRepository;

    @Value("${aws.s3.directory.image}")
    private String s3ImageDirectory;

    private final UserRepository userRepository;

    @Transactional
    public void create(Long userId, CreatePostRequestDto createPostRequestDto) {
        String fileName = awsS3Service.uploadFile(createPostRequestDto.getImage(), s3ImageDirectory);
        postRepository.save(
                Post.builder()
                    .user(userRepository.getReferenceById(userId))
                    .content(createPostRequestDto.getContent())
                    .category(createPostRequestDto.getCategory())
                    .imageUrl(fileName)
                    .build()
        );
    }
}
