package com.board.web;

import com.board.domain.Post;
import com.board.domain.PostRepository;
import com.board.domain.PostService;
import com.board.dto.PostUpdateDto;
import com.board.dto.PostWriteDto;
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
    private final PostService postService;

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
        Long savedId = postRepository.save(postWriteDto.convertToPostEntity());
        return "redirect:/posts/" + savedId;
    }

    @GetMapping("/posts/{postId}/edit")
    public String editForm(@PathVariable Long postId, Model model) {
        Post post = postRepository.findById(postId);
        model.addAttribute("form", PostUpdateDto.of(post));
        return "posts/editForm";
    }

    @PostMapping("/posts/{postId}/edit")
    public String edit(@ModelAttribute PostWriteDto writeDto, @PathVariable Long postId) {
        postService.edit(PostUpdateDto.of(postId, writeDto));
        return "redirect:/posts/" + postId;
    }

    @PostMapping("/posts/{postId}/delete")
    public String delete(@PathVariable Long postId) {
        postService.delete(postId);
        return "redirect:/posts";
    }

    @GetMapping("/posts/deleted")
    public String deletedList(Model model) {
        List<Post> deletedPosts = postRepository.findDeletedPosts();
        model.addAttribute("posts", deletedPosts);
        return "posts/deletedList";
    }

    @GetMapping("/posts/deleted/{postId}")
    public String deletedPost(@PathVariable Long postId, Model model) {
        Post deletedPost = postRepository.findById(postId);
        model.addAttribute("post", deletedPost);
        return "posts/deletedPost";
    }

    @PostMapping("/posts/deleted/{postId}/restore")
    public String restore(@PathVariable Long postId) {
        postService.restore(postId);
        return "redirect:/posts/" + postId;
    }

    @PostMapping("/posts/deleted/{postId}/full-delete")
    public String fullDelete(@PathVariable Long postId) {
        postService.fullDelete(postId);
        return "redirect:/posts/deleted";
    }
}
