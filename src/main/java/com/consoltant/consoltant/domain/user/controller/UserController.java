package com.consoltant.consoltant.domain.user.controller;

import com.consoltant.consoltant.domain.user.dto.CreateUserRequestDto;
import com.consoltant.consoltant.domain.user.dto.CreateUserAcademyRequestDto;
import com.consoltant.consoltant.domain.user.dto.CreateUserAccountRequestDto;
import com.consoltant.consoltant.domain.user.mapper.UserMapper;
import com.consoltant.consoltant.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.context.SecurityContextHolder;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping("/test")
    public ResponseEntity<?> getUsesr(){
        String userId = SecurityContextHolder.getContext().getAuthentication().getName();
        System.out.println(userId);
        return ResponseEntity.ok("USER 입니다.");
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        log.info("사용자 조회 API {}", id);
        return ResponseEntity.ok(userService.getUser(id));
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody CreateUserRequestDto createUserRequestDto) {
        log.info("사용자 생성 API -> {}", createUserRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(userMapper.toUser(createUserRequestDto)));
    }

    @PostMapping("/{id}/academy")
    public ResponseEntity<?> createUserAcademy(@PathVariable Long id, @RequestBody CreateUserAcademyRequestDto createUserAcademyRequestDto) {
        log.info("사용자 학력 추가 API {}", id);
        return ResponseEntity.ok(userService.createUserAcademy(id, userMapper.toUser(createUserAcademyRequestDto)));
    }

    @PostMapping("/{id}/account")
    public ResponseEntity<?> createUserAccount(@PathVariable Long id, @RequestBody CreateUserAccountRequestDto createUserAccountRequestDto) {
        log.info("사용자 계좌 추가 API {}", id);
        return ResponseEntity.ok(userService.createUserAccount(id, userMapper.toUser(createUserAccountRequestDto)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        log.info("사용자 삭제 API {}", id);
        return ResponseEntity.ok(userService.deleteUser(id));
    }
}
