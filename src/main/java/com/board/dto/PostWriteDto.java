package com.board.dto;

import com.board.domain.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PostWriteDto {

    private String title;
    private String contents;

    public static PostWriteDto emptyOf() {
        return new PostWriteDto(null, null);
    }

    public Post convertToPostEntity() {
        return Post.of(title, contents);
    }
}
