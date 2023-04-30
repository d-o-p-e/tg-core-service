package com.tg.coreservice.repository;

import com.tg.coreservice.dto.FeedResponseDto;
import com.tg.coreservice.specification.FeedOption;
import com.tg.coreservice.specification.PostCategory;

import java.util.List;

public interface PostRepositoryCustom {
    List<FeedResponseDto> getFeed(FeedOption feedOption);
}
