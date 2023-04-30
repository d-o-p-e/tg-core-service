package com.tg.coreservice.repository;

import com.tg.coreservice.dto.FeedResponseDto;

import java.util.List;

public interface PostRepositoryCustom {
    List<FeedResponseDto> getFeed(Long lastPostId, int size);
}
