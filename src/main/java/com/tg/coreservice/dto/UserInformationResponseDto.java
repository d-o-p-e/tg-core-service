package com.tg.coreservice.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserInformationResponseDto {
    private Long userId;
    private String nickname;
    private String profileImage;

    @Builder
    public UserInformationResponseDto(Long userId, String nickname, String profileImage) {
        this.userId = userId;
        this.nickname = nickname;
        this.profileImage = profileImage;
    }
}
