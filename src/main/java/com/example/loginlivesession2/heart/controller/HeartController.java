package com.example.loginlivesession2.heart.controller;


import com.example.loginlivesession2.heart.dto.ResponseDto;
import com.example.loginlivesession2.heart.service.HeartService;
import com.example.loginlivesession2.security.user.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class HeartController {

    private final HeartService heartService;

    @PostMapping("/heart/{postId}")
    public ResponseDto createHeart(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                      @PathVariable Long postId) {
        return heartService.createHeart(userDetails, postId);
    }

    @DeleteMapping("/heart/{postId}")
    public ResponseDto deleteHeart(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                   @PathVariable Long postId) {
        return heartService.deleteHeart(userDetails, postId);
    }


}
