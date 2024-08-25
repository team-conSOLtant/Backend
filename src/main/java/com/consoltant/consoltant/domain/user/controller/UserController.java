package com.consoltant.consoltant.domain.user.controller;

import com.consoltant.consoltant.domain.user.dto.*;
import com.consoltant.consoltant.domain.user.mapper.UserMapper;
import com.consoltant.consoltant.domain.user.service.UserService;
import com.consoltant.consoltant.util.base.BaseSuccessResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @GetMapping("/test")
    public ResponseEntity<?> getUser(){
        String userId = SecurityContextHolder.getContext().getAuthentication().getName();
        System.out.println(userId);
        return ResponseEntity.ok("USER 입니다.");
    }

    @GetMapping
    public BaseSuccessResponse<UserResponseDto> getUserById() {
        log.info("사용자 조회 API");
        Long id = userService.getUserId(SecurityContextHolder.getContext().getAuthentication().getName());

        log.info("사용자 조회 API {}", id);
        log.info("사용자 id -> {}", id);
        return new BaseSuccessResponse<>(userService.getUser(id));
    }

    @PostMapping("/{email}/create/account")
    public BaseSuccessResponse<CreateAccountResponseDto> createAccount(
            @RequestBody CreateAccountRequestDto createAccountRequestDto,
            @PathVariable("email") String email
    ) {
        Long id = userService.getUserId(email);
        log.info("계좌 생성 API -> {} {}", id, createAccountRequestDto.getAccountTypeUniqueNo());

        return new BaseSuccessResponse<>(userService.createAccount(id, createAccountRequestDto.getAccountTypeUniqueNo()));
    }

    @PostMapping("/{email}/academy")
    public BaseSuccessResponse<UserResponseDto> createUserAcademy(
            @RequestPart("subject") MultipartFile subject,
            @RequestPart("data") CreateUserAcademyRequestDto createUserAcademyRequestDto,
            @PathVariable("email") String email
    ) {
        Long id = userService.getUserId(email);
        log.info("사용자 학력 추가 API {}", id);
        return new BaseSuccessResponse<>(userService.createUserAcademy(id, createUserAcademyRequestDto, subject));
    }

    @PostMapping("/{email}/account")
    public BaseSuccessResponse<UserResponseDto> createUserAccount(
            @RequestBody CreateUserAccountRequestDto createUserAccountRequestDto,
            @PathVariable("email") String email) {
        Long id = userService.getUserId(email);
        log.info("사용자 계좌 추가 API {}", id);
        return new BaseSuccessResponse<>(userService.createUserAccount(id, createUserAccountRequestDto));
    }

    @DeleteMapping
    public BaseSuccessResponse<Long> deleteUser() {
        Long id = userService.getUserId(SecurityContextHolder.getContext().getAuthentication().getName());
        log.info("사용자 삭제 API {}", id);
        return new BaseSuccessResponse<>(userService.deleteUser(id));
    }

    // 학력 정보 입력 확인 API
    @GetMapping("/check/academy")
    public BaseSuccessResponse<Long> checkAcademy() {
        Long id = userService.getUserId(SecurityContextHolder.getContext().getAuthentication().getName());
        log.info("학력 정보 입력 확인 API {}", id);
        return new BaseSuccessResponse<>(userService.deleteUser(id));
    }

    // 계좌 정보 입력 확인 API
    @GetMapping("/check/account")
    public BaseSuccessResponse<Long> checkAccount() {
        Long id = userService.getUserId(SecurityContextHolder.getContext().getAuthentication().getName());
        log.info("계좌 정보 입력 확인 API {}", id);
        return new BaseSuccessResponse<>(userService.deleteUser(id));
    }
}
