package com.consoltant.consoltant.domain.auth.controller;

import com.consoltant.consoltant.domain.auth.dto.RegisterRequestDto;
import com.consoltant.consoltant.domain.auth.dto.RegisterResponseDto;
import com.consoltant.consoltant.domain.auth.service.AuthService;
import com.consoltant.consoltant.domain.user.dto.*;
import com.consoltant.consoltant.domain.user.repository.UserRepository;
import com.consoltant.consoltant.domain.user.service.UserService;
import com.consoltant.consoltant.util.base.BaseSuccessResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final UserService userService;

    @PostMapping("/register")
    public BaseSuccessResponse<RegisterResponseDto> register(@RequestBody RegisterRequestDto registerRequestDto) {
        log.info("사용자 등록 API");
        return new BaseSuccessResponse<>(authService.registerUser(registerRequestDto));
    }

    @PostMapping("/issue/account")
    public BaseSuccessResponse<IssueAccountResponseDto> issueAccount(@RequestBody IssueAccountRequestDto issueAccountRequestDto){
        log.info("1원 송금 API ");

        Long id = userService.getUserId(SecurityContextHolder.getContext().getAuthentication().getName());
        return new BaseSuccessResponse<>(authService.issueAccount(id,issueAccountRequestDto.getAccountNo()));
    }

    @PostMapping("/check/account")
    public BaseSuccessResponse<CheckAccountResponseDto> checkAccount( @RequestBody CheckAccountRequestDto checkAccountRequestDto){
        log.info("1원 송금 확인 API");

        Long id = userService.getUserId(SecurityContextHolder.getContext().getAuthentication().getName());

        String accountNo = checkAccountRequestDto.getAccountNo();
        String authText = checkAccountRequestDto.getAuthText();
        String authCode = checkAccountRequestDto.getAuthCode();
        return new BaseSuccessResponse<>(authService.checkAccount(id, accountNo, authText, authCode));
    }

    @PostMapping("/check/message")
    public BaseSuccessResponse<CheckTransactionMessageResponseDto> checkTransactionMessage(@RequestBody CheckTransactionMessageRequestDto checkTransactionMessageRequestDto){
        log.info("1원 송금 메세지 확인 API");

        Long id = userService.getUserId(SecurityContextHolder.getContext().getAuthentication().getName());

        String accountNo = checkTransactionMessageRequestDto.getAccountNo();
        Long transactionUniqueNo = checkTransactionMessageRequestDto.getTransactionUniqueNo();

        return new BaseSuccessResponse<>(authService.checkMessage(id, accountNo, transactionUniqueNo));
    }
}
