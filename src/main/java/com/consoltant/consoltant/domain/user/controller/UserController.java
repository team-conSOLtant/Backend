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
    private final UserMapper userMapper;

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

    @PostMapping("/{id}/create/account")
    public BaseSuccessResponse<CreateAccountResponseDto> createAccount(@PathVariable Long id, @RequestBody CreateAccountRequestDto createAccountRequestDto) {
        log.info("계좌 생성 API -> {} {}", id, createAccountRequestDto.getAccountTypeUniqueNo());
        return new BaseSuccessResponse<>(userService.createAccount(id, createAccountRequestDto.getAccountTypeUniqueNo()));
    }

    @PostMapping
    public BaseSuccessResponse<UserResponseDto> createUser(@RequestBody CreateUserRequestDto createUserRequestDto) {
        log.info("사용자 생성 API -> {}", createUserRequestDto);
        return new BaseSuccessResponse<>(userService.createUser(userMapper.toUser(createUserRequestDto)));
    }

    @PostMapping("/{id}/academy")
    public BaseSuccessResponse<UserResponseDto> createUserAcademy(@PathVariable Long id, @RequestBody CreateUserAcademyRequestDto createUserAcademyRequestDto) {
        log.info("사용자 학력 추가 API {}", id);
        return new BaseSuccessResponse<>(userService.createUserAcademy(id, userMapper.toUser(createUserAcademyRequestDto)));
    }

    @PostMapping("/{id}/account")
    public BaseSuccessResponse<UserResponseDto> createUserAccount(@PathVariable Long id, @RequestBody CreateUserAccountRequestDto createUserAccountRequestDto) {
        log.info("사용자 계좌 추가 API {}", id);
        return new BaseSuccessResponse<>(userService.createUserAccount(id, createUserAccountRequestDto));
    }

    @DeleteMapping("/{id}")
    public BaseSuccessResponse<Long> deleteUser(@PathVariable Long id) {
        log.info("사용자 삭제 API {}", id);
        return new BaseSuccessResponse<>(userService.deleteUser(id));
    }

    @PostMapping("/{id}/issue/account")
    public BaseSuccessResponse<IssueAccountResponseDto> issueAccount(@PathVariable Long id, @RequestBody IssueAccountRequestDto issueAccountRequestDto){
        log.info("1원 송금 API ");

        return new BaseSuccessResponse<>(userService.issueAccount(id,issueAccountRequestDto.getAccountNo()));
    }

    @PostMapping("/{id}/check/account")
    public BaseSuccessResponse<CheckAccountResponseDto> checkAccount(@PathVariable Long id, @RequestBody CheckAccountRequestDto checkAccountRequestDto){
        log.info("1원 송금 확인 API");
        String accountNo = checkAccountRequestDto.getAccountNo();
        String authText = checkAccountRequestDto.getAuthText();
        String authCode = checkAccountRequestDto.getAuthCode();
        return new BaseSuccessResponse<>(userService.checkAccount(id, accountNo, authText, authCode));
    }

    @PostMapping("/{id}/check/message")
    public BaseSuccessResponse<CheckTransactionMessageResponseDto> checkTransactionMessage(@PathVariable Long id, @RequestBody CheckTransactionMessageRequestDto checkTransactionMessageRequestDto){
        log.info("1원 송금 메세지 확인 API");
        String accountNo = checkTransactionMessageRequestDto.getAccountNo();
        Long transactionUniqueNo = checkTransactionMessageRequestDto.getTransactionUniqueNo();

        return new BaseSuccessResponse<>(userService.checkMessage(id, accountNo, transactionUniqueNo));
    }

}
