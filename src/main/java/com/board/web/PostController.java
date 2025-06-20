package com.board.web;

import com.board.domain.Post;
import com.board.domain.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class PostController {

    private final PostRepository postRepository;

    @GetMapping("/posts")
    public String showAllPosts(Model model) {
        List<Post> posts = postRepository.findAll();
        model.addAttribute("posts", posts);
        return "posts/postList";
    }

    @GetMapping("/posts/{postId}")
    public String post(@PathVariable Long postId, Model model) {
        Post post = postRepository.findById(postId);
        model.addAttribute("post", post);
        return "posts/post";
    }

    @GetMapping("/posts/write")
    public String writeForm(Model model) {
        model.addAttribute("form", PostWriteDto.emptyOf());
        return "posts/writeForm";
    }

    @PostMapping("/posts/write")
    public String write(@ModelAttribute PostWriteDto postWriteDto) {
        postRepository.save(postWriteDto.convertToPostEntity());
        return "redirect:/posts";
    }
}
