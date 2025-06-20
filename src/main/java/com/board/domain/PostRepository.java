package com.board.domain;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostRepository {

    private final EntityManager em;

    @Transactional
    public Long save(Post post) {
        em.persist(post);
        return post.getId();
    }

    public Post findById(Long id) {
        return em.find(Post.class, id);
    }

    // todo 임시
    public List<Post> findByTitle(String title) {
        return em.createQuery("select p from Post p where p.title like :title", Post.class)
                .setParameter("title", title)
                .getResultList();
    }

    public List<Post> findAll() {
        return em.createQuery("select p from Post p where p.deleted = false", Post.class)
                .getResultList();
    }

    public List<Post> findDeletedPosts() {
        return em.createQuery("select p from Post p where p.deleted = true", Post.class)
                .getResultList();
    }

    @Transactional
    public void delete(Long id) {
        Post post = em.find(Post.class, id);
        em.remove(post);
    }
}
