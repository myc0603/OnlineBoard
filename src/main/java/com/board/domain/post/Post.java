package com.board.domain.post;

import com.board.domain.user.User;
import com.board.dto.PostUpdateDto;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @NotBlank
    private String title;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @NotBlank
    private String contents;

    private boolean deleted = false;

    @NotNull
    private LocalDateTime createdDateTime;

    @NotNull
    private LocalDateTime lastModifiedDateTime;

    public static Post of(String title, String contents) {
        Post post = new Post();
        post.title = title;
        post.contents = contents;
        post.createdDateTime = LocalDateTime.now();
        post.lastModifiedDateTime = post.createdDateTime;
        return post;
    }

    public void update(PostUpdateDto updateDto) {
        title = updateDto.getTitle();
        contents = updateDto.getContents();
        lastModifiedDateTime = LocalDateTime.now();
    }

    public void delete() {
        deleted = true;
    }

    public void restore() {
        deleted = false;
    }
}
