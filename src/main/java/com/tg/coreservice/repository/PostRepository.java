package com.tg.coreservice.repository;

import com.tg.coreservice.domain.Post;
import com.tg.coreservice.specification.PostCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long>, PostRepositoryCustom {

    int countByUserIdAndCategory(Long userId, PostCategory workout);
}
