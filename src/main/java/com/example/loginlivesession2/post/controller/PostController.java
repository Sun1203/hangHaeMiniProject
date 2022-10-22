package com.example.loginlivesession2.post.controller;


import com.example.loginlivesession2.post.dto.PostRequestDto;
import com.example.loginlivesession2.post.entity.Post;
import com.example.loginlivesession2.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/post")
public class PostController {

    private final PostService postService;

    @PostMapping("/")
    public Post createPost(@RequestBody PostRequestDto requestDto){
        return postService.save(requestDto);
    }
}
