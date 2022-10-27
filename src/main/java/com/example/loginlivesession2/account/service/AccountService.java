package com.example.loginlivesession2.account.service;

import com.example.loginlivesession2.account.dto.AccountRequestDto;
import com.example.loginlivesession2.account.dto.LoginRequestDto;
import com.example.loginlivesession2.account.dto.OverlapRequestDto;
import com.example.loginlivesession2.account.dto.ResponseDto;
import com.example.loginlivesession2.account.entity.Account;
import com.example.loginlivesession2.account.entity.RefreshToken;
import com.example.loginlivesession2.account.repository.AccountRepository;
import com.example.loginlivesession2.account.repository.RefreshTokenRepository;
import com.example.loginlivesession2.exception.CustomException;
import com.example.loginlivesession2.exception.ErrorCode;
import com.example.loginlivesession2.jwt.dto.TokenDto;
import com.example.loginlivesession2.jwt.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
    private final AccountRepository accountRepository;
    private final RefreshTokenRepository refreshTokenRepository;

    @Transactional
    public ResponseDto signup(AccountRequestDto accountRequestDto) {

        // ID 중복 검사
        if(accountRepository.findByLoginId(accountRequestDto.getLoginId()).isPresent()){
            throw new CustomException(ErrorCode.OVERLAP_LOGINID);
        }

        // 비밀번호 암호화
        accountRequestDto.setEncodePwd(passwordEncoder.encode(accountRequestDto.getPassword()));
        Account account = new Account(accountRequestDto);

        accountRepository.save(account);
        return ResponseDto.success("회원가입 성공");
    }

    @Transactional
    public ResponseDto login(LoginRequestDto loginRequestDto, HttpServletResponse response) {

        // 아이디 체크
        Account account = accountRepository.findByLoginId(loginRequestDto.getLoginId()).orElseThrow(
                () -> new CustomException(ErrorCode.NOT_MATCHED_LOGINID)
        );

        // 비밀번호 체크
        if(!passwordEncoder.matches(loginRequestDto.getPassword(), account.getPassword())) {
            throw new CustomException(ErrorCode.NOT_MATCHED_PASSWORD);
        }

        // 아이디를 이용해서  Acess_Token 토큰만들기.
        TokenDto tokenDto = jwtUtil.createAllToken(loginRequestDto.getLoginId());

        // 아이디를 이용해서 Refresh_Token 토큰만들기
        Optional<RefreshToken> refreshToken = refreshTokenRepository.findByAccountLoginId(loginRequestDto.getLoginId());

        // 리프레쉬 토큰 저장
        if(refreshToken.isPresent()) {
            refreshTokenRepository.save(refreshToken.get().updateToken(tokenDto.getRefreshToken()));
        }else {
            RefreshToken newToken = new RefreshToken(tokenDto.getRefreshToken(), loginRequestDto.getLoginId());
            refreshTokenRepository.save(newToken);
        }

        // 헤더에 토큰 저장
        setHeader(response, tokenDto);

        return ResponseDto.success("로그인 성공");
    }

    public ResponseEntity<List<Account>> getAccount() {
        List<Account> account = accountRepository.findAll();
        return ResponseEntity.ok(account);
    }


    @Transactional
    public ResponseDto idOverlap(OverlapRequestDto loginId) {
        // 아이디 체크
        Account account = accountRepository.findByLoginId(loginId.getLoginId()).orElse(null);
        if (account == null)
            return ResponseDto.success("사용 가능한 아이디");

        throw new CustomException(ErrorCode.OVERLAP_LOGINID);
    }

    private void setHeader(HttpServletResponse response, TokenDto tokenDto) {
        response.addHeader(JwtUtil.ACCESS_TOKEN, tokenDto.getAccessToken());
        response.addHeader(JwtUtil.REFRESH_TOKEN, tokenDto.getRefreshToken());
    }

}
