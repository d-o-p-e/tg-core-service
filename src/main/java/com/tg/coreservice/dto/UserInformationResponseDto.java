package com.tg.coreservice.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class UserInformationResponseDto {
    private Long userId;
    private String nickname;
    private String profileImage;
    private int workoutCount;
    private int algorithmCount;
    private int earlyBirdCount;

    @Builder
    public UserInformationResponseDto(Long userId, String nickname, String profileImage, int workoutCount, int algorithmCount, int earlyBirdCount) {
        this.userId = userId;
        this.nickname = nickname;
        this.profileImage = profileImage;
        this.workoutCount = workoutCount;
        this.algorithmCount = algorithmCount;
        this.earlyBirdCount = earlyBirdCount;
    }
}
