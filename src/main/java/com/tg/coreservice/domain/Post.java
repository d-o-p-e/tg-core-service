package com.tg.coreservice.domain;

import com.tg.coreservice.specification.PostCategory;
import jakarta.persistence.Column;
import jakarta.persistence.ConstraintMode;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;

@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="POST_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private User user;

    private String content;

    private String imageUrl;

    @Enumerated
    private PostCategory category;

    @Builder
    public Post(User user, String content, String imageUrl, PostCategory category) {
        this.user = user;
        this.content = content;
        this.imageUrl = imageUrl;
        this.category = category;
    }
}
