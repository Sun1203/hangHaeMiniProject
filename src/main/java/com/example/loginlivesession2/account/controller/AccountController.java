package com.example.loginlivesession2.account.controller;

import com.example.loginlivesession2.account.dto.AccountRequestDto;
import com.example.loginlivesession2.account.dto.LoginRequestDto;
import com.example.loginlivesession2.account.dto.OverlapRequestDto;
import com.example.loginlivesession2.account.dto.ResponseDto;
import com.example.loginlivesession2.account.entity.Account;
import com.example.loginlivesession2.account.service.AccountService;
import com.example.loginlivesession2.global.dto.GlobalResDto;
import com.example.loginlivesession2.jwt.util.JwtUtil;
import com.example.loginlivesession2.security.user.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@RestController

@RequiredArgsConstructor
public class AccountController {

    private final JwtUtil jwtUtil;
    private final AccountService accountService;

    @PostMapping("/signup")
    public ResponseDto signup(@RequestBody @Valid AccountRequestDto accountReqDto) {
        System.out.println("AccountController.signup");
        return accountService.signup(accountReqDto);
    }

    @PostMapping("/login")
    public ResponseDto login(@RequestBody @Valid LoginRequestDto loginRequestDto, HttpServletResponse response) {
        return accountService.login(loginRequestDto, response);
    }

    @GetMapping("/account")
    public ResponseEntity<List<Account>> getAccount() {
        return accountService.getAccount();
    }

    @PostMapping("/signup/overlap")
    public ResponseDto idOverlap(@RequestBody OverlapRequestDto loginId) {
        return accountService.idOverlap(loginId);
    }

    @GetMapping("/api/issue/token")
    public GlobalResDto issuedToken(@AuthenticationPrincipal UserDetailsImpl userDetails, HttpServletResponse response){
        response.addHeader(JwtUtil.ACCESS_TOKEN, jwtUtil.createToken(userDetails.getAccount().getUsername(), "Access"));
        return new GlobalResDto("Success IssuedToken", HttpStatus.OK.value());
    }

}
