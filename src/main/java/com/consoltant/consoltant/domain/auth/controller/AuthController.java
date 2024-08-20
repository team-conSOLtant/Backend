package com.consoltant.consoltant.domain.auth.controller;

import com.consoltant.consoltant.domain.auth.dto.LoginRequestDto;
//import com.consoltant.consoltant.domain.auth.mapper.AuthMapper;
//import com.consoltant.consoltant.domain.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    //private final AuthService authService;
    //private final AuthMapper authMapper;

    //로그인 API
    @PostMapping
    public ResponseEntity<?> login(@RequestBody LoginRequestDto loginRequestDto) {

        log.info("login API: {}", loginRequestDto);

        return null;
    }

    //로그아웃 API
    @GetMapping
    public ResponseEntity<?> logout(@RequestBody LoginRequestDto loginRequestDto) {

        log.info("logout API: {}", loginRequestDto);

        return null;
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
