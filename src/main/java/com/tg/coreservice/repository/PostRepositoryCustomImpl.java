package com.tg.coreservice.repository;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.tg.coreservice.domain.User;
import com.tg.coreservice.dto.FeedResponseDto;
import com.tg.coreservice.dto.QFeedResponseDto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.tg.coreservice.domain.QPost.post;
import static com.tg.coreservice.domain.QUser.user;

@Repository
public class PostRepositoryCustomImpl implements PostRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;
    private final JPAQueryFactory queryFactory;

    public PostRepositoryCustomImpl() {
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public List<FeedResponseDto> getFeed(User me, Long lastPostId, int size) {
        return queryFactory
                .select(new QFeedResponseDto(
                        post.id,
                        post.content,
                        post.imageUrl,
                        post.category,
                        user.id,
                        user.nickname,
                        user.profileImageUrl,
                        post.likeList.size(),
                        post.commentList.size(),
                        Expressions.asBoolean(false), //isLiked
                        me == null ? Expressions.asBoolean(false) : post.user.eq(me) //isMyPost
                ))
                .from(post)
                .join(user)
                .where(
                        cursorPagination(lastPostId)
                )
                .limit(size)
                .fetch();
    }

    private Predicate cursorPagination(Long lastPostId) {
        if (lastPostId == null) {
            return null;
        }
        return post.id.lt(lastPostId);
    }
}
