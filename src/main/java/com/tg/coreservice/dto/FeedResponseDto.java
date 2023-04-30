package com.tg.coreservice.dto;

import com.querydsl.core.annotations.QueryProjection;
import com.tg.coreservice.specification.PostCategory;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class FeedResponseDto {
    private Long postId;
    private String content;
    private String imageUrl;
    private PostCategory category;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Long userId;
    private String userNickname;
    private String userProfileImageUrl;
    private int likeCount;
    private int commentCount;
    private Boolean isLiked;
    private Boolean isMyPost;

    @QueryProjection
    public FeedResponseDto(Long postId, String content, String imageUrl, PostCategory category, LocalDateTime createdAt, LocalDateTime updatedAt, Long userId, String userNickname, String userProfileImageUrl, int likeCount, int commentCount, Boolean isLiked, Boolean isMyPost) {
        this.postId = postId;
        this.content = content;
        this.imageUrl = imageUrl;
        this.category = category;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.userId = userId;
        this.userNickname = userNickname;
        this.userProfileImageUrl = userProfileImageUrl;
        this.likeCount = likeCount;
        this.commentCount = commentCount;
        this.isLiked = isLiked;
        this.isMyPost = isMyPost;
    }
}
