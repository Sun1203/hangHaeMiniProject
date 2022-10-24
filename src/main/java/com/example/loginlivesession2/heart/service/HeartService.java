package com.example.loginlivesession2.heart.service;

import com.example.loginlivesession2.heart.dto.ResponseDto;
import com.example.loginlivesession2.account.entity.Account;
import com.example.loginlivesession2.exception.CustomException;
import com.example.loginlivesession2.exception.ErrorCode;
import com.example.loginlivesession2.heart.entity.Heart;
import com.example.loginlivesession2.heart.repository.HeartRepository;
import com.example.loginlivesession2.post.entity.Post;
import com.example.loginlivesession2.post.repository.PostRepository;
import com.example.loginlivesession2.security.user.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class HeartService {

    private final HeartRepository heartRepository;
    private final PostRepository postRepository;

    @Transactional
    public ResponseDto createHeart(UserDetailsImpl userDetails, Long postId) {

        Account account = userDetails.getAccount();
        // postId에 해당하는 게시글 조회
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new CustomException(ErrorCode.NOT_FOUND_POST)
        );

        // 좋아요 중복 조회
        if (heartRepository.existsByAccountAndPost(account, post))
            throw new CustomException(ErrorCode.NOT_OVERLAP_HEART);

        Heart heart = new Heart(account, post);
        // 좋아요 저장
        heartRepository.save(heart);

        return ResponseDto.success("좋아요 성공");

    }
    @Transactional
    public ResponseDto deleteHeart(UserDetailsImpl userDetails, Long postId) {

        // 회원 정보
        Account account = userDetails.getAccount();
        // postId에 해당하는 게시글 조회
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new CustomException(ErrorCode.NOT_FOUND_POST)
        );
        // 좋아요 취소
        heartRepository.deleteByAccountAndPost(account, post);

        return ResponseDto.success("좋아요 취소 성공");
    }
}
