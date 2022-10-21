package com.example.loginlivesession2.account.service;

import com.example.loginlivesession2.account.dto.AccountRequestDto;
import com.example.loginlivesession2.account.dto.LoginRequestDto;
import com.example.loginlivesession2.account.entity.Account;
import com.example.loginlivesession2.account.entity.RefreshToken;
import com.example.loginlivesession2.account.repository.AccountRepository;
import com.example.loginlivesession2.account.repository.RefreshTokenRepository;
import com.example.loginlivesession2.global.dto.GlobalResDto;
import com.example.loginlivesession2.jwt.dto.TokenDto;
import com.example.loginlivesession2.jwt.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
    private final AccountRepository accountRepository;
    private final RefreshTokenRepository refreshTokenRepository;

    @Transactional
    public GlobalResDto signup(AccountRequestDto accountRequestDto) {
        // email 중복 검사
        if(accountRepository.findByLoginId(accountRequestDto.getLoginId()).isPresent()){
            throw new RuntimeException("Overlap Check");
        }

        accountRequestDto.setEncodePwd(passwordEncoder.encode(accountRequestDto.getPassword()));
        Account account = new Account(accountRequestDto);

        accountRepository.save(account);
        return new GlobalResDto("Success signup", HttpStatus.OK.value());
    }

    @Transactional
    public GlobalResDto login(LoginRequestDto loginRequestDto, HttpServletResponse response) {

        Account account = accountRepository.findByLoginId(loginRequestDto.getLoginId()).orElseThrow(
                () -> new RuntimeException("Not found Account")
        );

        if(!passwordEncoder.matches(loginRequestDto.getPassword(), account.getPassword())) {
            throw new RuntimeException("Not matches Password");
        }

        TokenDto tokenDto = jwtUtil.createAllToken(loginRequestDto.getLoginId());

        Optional<RefreshToken> refreshToken = refreshTokenRepository.findByAccountLoginId(loginRequestDto.getLoginId());

        if(refreshToken.isPresent()) {
            refreshTokenRepository.save(refreshToken.get().updateToken(tokenDto.getRefreshToken()));
        }else {
            RefreshToken newToken = new RefreshToken(tokenDto.getRefreshToken(), loginRequestDto.getLoginId());
            refreshTokenRepository.save(newToken);
        }

        setHeader(response, tokenDto);

        return new GlobalResDto("Success Login", HttpStatus.OK.value());

    }

    private void setHeader(HttpServletResponse response, TokenDto tokenDto) {
        response.addHeader(JwtUtil.ACCESS_TOKEN, tokenDto.getAccessToken());
        response.addHeader(JwtUtil.REFRESH_TOKEN, tokenDto.getRefreshToken());
    }
}
