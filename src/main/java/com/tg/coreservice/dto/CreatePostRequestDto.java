package com.tg.coreservice.dto;

import com.tg.coreservice.specification.PostCategory;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Getter
public class CreatePostRequestDto {
    private String content;
    private PostCategory category;
    private MultipartFile image;
}
