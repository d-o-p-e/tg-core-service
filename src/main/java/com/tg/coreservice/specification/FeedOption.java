package com.tg.coreservice.specification;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FeedOption {
    private int size;
    private Long lastPostId;
    private Long targetUserId;

    @Builder
    public FeedOption(int size, Long lastPostId, Long targetUserId) {
        this.size = size;
        this.lastPostId = lastPostId;
        this.targetUserId = targetUserId;
    }
}
