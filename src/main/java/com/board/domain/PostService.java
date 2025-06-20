package com.board.domain;

import com.board.dto.PostUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class PostService {

    private final PostRepository postRepository;

    public void edit(PostUpdateDto updateDto) {
        Post post = postRepository.findById(updateDto.getId());
        post.update(updateDto);
    }

    public void delete(Long id) {
        Post post = postRepository.findById(id);
        post.delete();
    }

    public void restore(Long id) {
        Post post = postRepository.findById(id);
        post.restore();
    }

    public void fullDelete(Long id) {
        postRepository.delete(id);
    }
}
