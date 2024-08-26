package com.consoltant.consoltant.domain.user.controller;

import com.consoltant.consoltant.domain.product.dto.ProductSaveRequestDto;
import com.consoltant.consoltant.domain.product.service.ProductService;
import com.consoltant.consoltant.domain.user.dto.CreateAccountRequestDto;
import com.consoltant.consoltant.domain.user.dto.CreateAccountResponseDto;
import com.consoltant.consoltant.domain.user.dto.CreateUserAcademyRequestDto;
import com.consoltant.consoltant.domain.user.dto.CreateUserAccountRequestDto;
import com.consoltant.consoltant.domain.user.dto.UserResponseDto;
import com.consoltant.consoltant.domain.user.service.UserService;
import com.consoltant.consoltant.util.base.BaseSuccessResponse;
import com.consoltant.consoltant.util.constant.ProductType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final ProductService productService;

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

        //계좌 생성 및 등록
        CreateAccountResponseDto createAccountResponseDto = userService.createAccount(id, createAccountRequestDto.getAccountTypeUniqueNo());
        ProductSaveRequestDto productSaveRequestDto = new ProductSaveRequestDto();
        productSaveRequestDto.setAccountNo(createAccountResponseDto.getAccountNo());
        productSaveRequestDto.setProductType(ProductType.DEMAND_DEPOSIT);

        productService.save(id,productSaveRequestDto);

        return new BaseSuccessResponse<>(createAccountResponseDto);
    }

    @PostMapping("/{email}/academy")
    public BaseSuccessResponse<UserResponseDto> createUserAcademy(
            @RequestPart("subject") MultipartFile subject,
            @RequestPart("data") CreateUserAcademyRequestDto createUserAcademyRequestDto,
            @PathVariable("email") String email
    ) {
        Long id = userService.getUserId(email);
        log.info("사용자 학력 추가 API {}", id);
        try {
            return new BaseSuccessResponse<>(userService.createUserAcademy(id, createUserAcademyRequestDto, subject));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
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
    public BaseSuccessResponse<Boolean> checkAcademy() {
        Long id = userService.getUserId(SecurityContextHolder.getContext().getAuthentication().getName());
        log.info("학력 정보 입력 확인 API {}", id);
        return new BaseSuccessResponse<>(userService.checkAcademy(id));
    }

    // 계좌 정보 입력 확인 API
    @GetMapping("/check/account")
    public BaseSuccessResponse<Boolean> checkAccount() {
        Long id = userService.getUserId(SecurityContextHolder.getContext().getAuthentication().getName());
        log.info("계좌 정보 입력 확인 API {}", id);
        return new BaseSuccessResponse<>(userService.checkAccount(id));
    }
}
