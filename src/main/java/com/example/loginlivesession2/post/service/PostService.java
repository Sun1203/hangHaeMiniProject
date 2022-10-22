package com.example.loginlivesession2.post.service;

import com.example.loginlivesession2.post.dto.PostRequestDto;
import com.example.loginlivesession2.post.entity.Post;
import com.example.loginlivesession2.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public Post save(PostRequestDto requestDto) {
        Post post = new Post(requestDto);
        return postRepository.save(post);
    }
}
