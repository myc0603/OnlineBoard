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
    public void save(Post post) {
        em.persist(post);
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
        return em.createQuery("select p from Post p", Post.class)
                .getResultList();
    }
}
