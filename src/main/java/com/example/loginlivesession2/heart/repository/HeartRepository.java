package com.example.loginlivesession2.heart.repository;

import com.example.loginlivesession2.account.entity.Account;
import com.example.loginlivesession2.heart.entity.Heart;
import com.example.loginlivesession2.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HeartRepository extends JpaRepository<Heart, Long> {
    Long countByPost(Post post);
    Boolean existsByAccountAndPost(Account account, Post post);
    Integer deleteByAccountAndPost(Account account, Post post);

}
