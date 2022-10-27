package com.example.loginlivesession2.post.service;

import com.example.loginlivesession2.account.entity.Account;
import com.example.loginlivesession2.account.repository.AccountRepository;
import com.example.loginlivesession2.exception.CustomException;
import com.example.loginlivesession2.exception.ErrorCode;
import com.example.loginlivesession2.post.dto.CategoryPostResponseDto;
import com.example.loginlivesession2.post.dto.PostRequestDto;
import com.example.loginlivesession2.post.dto.PostResponseDto;
import com.example.loginlivesession2.post.entity.Post;
import com.example.loginlivesession2.post.repository.PostRepository;
import com.example.loginlivesession2.security.user.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


@RequiredArgsConstructor
@Service
public class PostService {
    private final PostRepository postRepository;



    public List<CategoryPostResponseDto> getCategoryPost(String category) {
        var posts = postRepository.findAllByCategory(category);
        var categoryPostResponseDtos = new ArrayList<CategoryPostResponseDto>();
        for (Post post : posts) {
            categoryPostResponseDtos.add(new CategoryPostResponseDto(post));
        }
            return categoryPostResponseDtos;
    }

    public PostResponseDto getOnePost(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_ONEPOST));
        return new PostResponseDto(post);
    }

    public PostResponseDto post(PostRequestDto postRequestDto, UserDetailsImpl userDetails) {
        Account account = userDetails.getAccount();
        Post post = new Post(postRequestDto, account);
        postRepository.save(post);
        return new PostResponseDto(post);
    }


    @Transactional
    public PostResponseDto updatePost(Long postId, PostRequestDto postRequestDto, UserDetailsImpl userDetails) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_ONEPOST));
        Long userId = userDetails.getAccount().getAccountId();
        if (post.getAccount().getAccountId().equals(userId)) {
            post.update(postRequestDto);
            return new PostResponseDto(post);
        } else {
            throw new CustomException(ErrorCode.NOT_MATCHED_WRITER);
        }
    }

    @Transactional
    public String deletePost(Long postId, UserDetailsImpl userDetails) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_ONEPOST));
        Long userId = userDetails.getAccount().getAccountId();
        if (post.getAccount().getAccountId().equals(userId)) {
            postRepository.deleteById(postId);
            return "";
        } else {
            throw new CustomException(ErrorCode.NOT_MATCHED_WRITER);
        }
    }
}








