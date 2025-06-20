package com.board.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Post {

    @Id
    @GeneratedValue
    @Column(name = "post_id")
    private Long id;

    @NotBlank
    private String title;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @NotBlank
    private String contents;

    @NotNull
    private LocalDateTime createdDateTime;

    public static Post of(String title, String contents) {
        Post post = new Post();
        post.title = title;
        post.contents = contents;
        post.createdDateTime = LocalDateTime.now();
        return post;
    }
}
