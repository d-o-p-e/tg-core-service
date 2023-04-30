package com.tg.coreservice.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class CommentResponseDto {
    private Long commentId;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Long userId;
    private String nickName;
    private String profileImageUrl;

    @Builder
    public CommentResponseDto(Long commentId, String content, LocalDateTime createdAt, LocalDateTime updatedAt, Long userId, String nickName, String profileImageUrl) {
        this.commentId = commentId;
        this.content = content;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.userId = userId;
        this.nickName = nickName;
        this.profileImageUrl = profileImageUrl;
    }
}
