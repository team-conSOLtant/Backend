package com.consoltant.consoltant.domain.auth.controller;

import com.consoltant.consoltant.domain.auth.dto.LoginRequestDto;
//import com.consoltant.consoltant.domain.auth.mapper.AuthMapper;
//import com.consoltant.consoltant.domain.auth.service.AuthService;
import com.consoltant.consoltant.domain.auth.dto.RegisterRequestDto;
import com.consoltant.consoltant.domain.auth.service.AuthService;
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
    //private final AuthMapper authMapper;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequestDto registerRequestDto) {
        return ResponseEntity.ok(authService.registerUser(registerRequestDto));
    }

    //로그인 API
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDto loginRequestDto) {
        log.info("login API: {}", loginRequestDto);
        return ResponseEntity.ok(authService.login(loginRequestDto));
    }

    //로그아웃 API
    @PostMapping("/logout")
    public ResponseEntity<?> logout() {
        SecurityContextHolder.clearContext();

        return ResponseEntity.ok("logged out");
    }

//    //1원 송금 API
//    @PostMapping
//    public ResponseEntity<?> openAccountAuth(@RequestBody LoginRequestDto loginRequestDto) {
//
//        log.info("1원 송금 API: {}", loginRequestDto);
//
//        return null;
//    }

//    //1원 송금 인증 API
//    @PostMapping
//    public ResponseEntity<?> checkAuthCode(@RequestBody LoginRequestDto loginRequestDto) {
//
//        log.info("1원 송금 인증 API: {}", loginRequestDto);
//
//        return null;
//    }
}
