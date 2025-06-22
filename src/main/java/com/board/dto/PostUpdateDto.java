package com.board.dto;

import com.board.domain.post.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PostUpdateDto {
    private Long id;
    private String title;
    private String contents;

    public static PostUpdateDto of(Post post) {
        return new PostUpdateDto(post.getId(), post.getTitle(), post.getContents());
    }

    public static PostUpdateDto of(Long postId, PostWriteDto writeDto) {
        return new PostUpdateDto(postId, writeDto.getTitle(), writeDto.getContents());
    }

}
