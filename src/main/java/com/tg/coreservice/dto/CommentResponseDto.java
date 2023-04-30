package com.tg.coreservice.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentResponseDto {
    private Long commentId;
    private String content;
    private Long userId;
    private String nickName;
    private String profileImageUrl;

    @Builder
    public CommentResponseDto(Long commentId, String content, Long userId, String nickName, String profileImageUrl) {
        this.commentId = commentId;
        this.content = content;
        this.userId = userId;
        this.nickName = nickName;
        this.profileImageUrl = profileImageUrl;
    }
}
