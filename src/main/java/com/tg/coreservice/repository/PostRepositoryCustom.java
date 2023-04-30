package com.tg.coreservice.repository;

import com.tg.coreservice.domain.User;
import com.tg.coreservice.dto.FeedResponseDto;

import java.util.List;

public interface PostRepositoryCustom {
    List<FeedResponseDto> getFeed(User me, Long lastPostId, int size);
}
