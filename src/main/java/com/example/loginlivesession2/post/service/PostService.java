package com.example.loginlivesession2.post.service;

import com.example.loginlivesession2.account.entity.Account;
import com.example.loginlivesession2.account.repository.AccountRepository;
import com.example.loginlivesession2.exception.CustomException;
import com.example.loginlivesession2.exception.ErrorCode;
import com.example.loginlivesession2.heart.entity.Heart;
import com.example.loginlivesession2.heart.repository.HeartRepository;
import com.example.loginlivesession2.post.dto.CategoryPostResponseDto;
import com.example.loginlivesession2.post.dto.PostRequestDto;
import com.example.loginlivesession2.post.dto.PostResponseDto;
import com.example.loginlivesession2.post.dto.ResponseDto;
import com.example.loginlivesession2.post.entity.Post;
import com.example.loginlivesession2.post.repository.PostRepository;
import com.example.loginlivesession2.security.user.UserDetailsImpl;
import jdk.jfr.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;


@RequiredArgsConstructor
@Service
public class PostService {
    private final PostRepository postRepository;
    private final AccountRepository accountRepository;
    private final HeartRepository heartRepository;


    public List<CategoryPostResponseDto> getCategoryPost(String category) {
        // 로그인을 하지 않으면 조회못함.
         class PostComparator implements Comparator<Post> {
            @Override
            public int compare(Post a,Post b){
                if(a.getHeart().size()<b.getHeart().size()) return 1;
                if(a.getHeart().size()>b.getHeart().size()) return -1;
                return 0;
            }
        }
        var posts = postRepository.findAllByCategory(category);
        Collections.sort(posts, new PostComparator());
        var categoryPostResponseDtos = new ArrayList<CategoryPostResponseDto>();


        for (Post post : posts) {
            Long heart = heartRepository.countByPost(post);
            categoryPostResponseDtos.add(new CategoryPostResponseDto(post, heart));
        }
        return categoryPostResponseDtos;
    }

    public PostResponseDto getOnePost(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_POST));
        Long heart = heartRepository.countByPost(post);
        return new PostResponseDto(post, heart);
    }

    public PostResponseDto post(PostRequestDto postRequestDto, UserDetailsImpl userDetails) {
        Account account = userDetails.getAccount();
        Post post = new Post(postRequestDto, account);
        postRepository.save(post);
        Long heart = heartRepository.countByPost(post);
        return new PostResponseDto(post, heart);
    }


    @Transactional
    public PostResponseDto updatePost(Long postId, PostRequestDto postRequestDto, UserDetailsImpl userDetails) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new IllegalArgumentException("해당 게시글을 찾을 수 없습니다."));
        Long userId = userDetails.getAccount().getAccountId();
        Long heart = heartRepository.countByPost(post);
        if (post.getAccount().getAccountId().equals(userId)) {
            post.update(postRequestDto);
            return new PostResponseDto(post, heart);
        } else {
            throw new RuntimeException("작성자만 수정 가능합니다.");
        }
    }

    public String deletePost(Long postId, UserDetailsImpl userDetails) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new IllegalArgumentException("해당 게시글을 찾을 수 없습니다."));
        Long userId = userDetails.getAccount().getAccountId();
        if (post.getAccount().getAccountId().equals(userId)) {
            postRepository.deleteById(userId);
            return "삭제 완료";
        } else {
            throw new RuntimeException("작성자만 삭제 가능합니다.");
        }
    }
}

