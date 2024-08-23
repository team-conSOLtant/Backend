package com.consoltant.consoltant.domain.user.controller;

import com.consoltant.consoltant.domain.user.dto.*;
import com.consoltant.consoltant.domain.user.mapper.UserMapper;
import com.consoltant.consoltant.domain.user.service.UserService;
import com.consoltant.consoltant.util.base.BaseSuccessResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.context.SecurityContextHolder;
<<<<<<< HEAD
=======
import org.springframework.web.multipart.MultipartFile;

>>>>>>> 1ba4729826b890b04b844195b8388dab3e250483

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

<<<<<<< HEAD
    @GetMapping("/test")
    public ResponseEntity<?> getUsesr(){
        String userId = SecurityContextHolder.getContext().getAuthentication().getName();
        System.out.println(userId);
        return ResponseEntity.ok("USER 입니다.");
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
=======
    @GetMapping("")
    public BaseSuccessResponse<UserResponseDto> getUserById() {
        Long id = userService.getUserId(SecurityContextHolder.getContext().getAuthentication().getName());

>>>>>>> 1ba4729826b890b04b844195b8388dab3e250483
        log.info("사용자 조회 API {}", id);
        return new BaseSuccessResponse<>(userService.getUser(id));
    }

    @PostMapping("/{id}/create/account")
    public BaseSuccessResponse<CreateAccountResponseDto> createAccount(@PathVariable Long id, @RequestBody CreateAccountRequestDto createAccountRequestDto) {
        log.info("계좌 생성 API -> {} {}", id, createAccountRequestDto.getAccountTypeUniqueNo());
        return new BaseSuccessResponse<>(userService.createAccount(id, createAccountRequestDto.getAccountTypeUniqueNo()));
    }

    @PostMapping("/{id}/academy")
    public BaseSuccessResponse<UserResponseDto> createUserAcademy(@RequestPart("subject") MultipartFile subject){
        return null;
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
