package com.consoltant.consoltant.domain.user.controller;

import com.consoltant.consoltant.domain.user.dto.CreateUserRequestDto;
import com.consoltant.consoltant.domain.user.dto.UpdateUserRequestDto;
import com.consoltant.consoltant.domain.user.mapper.UserMapper;
import com.consoltant.consoltant.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        log.info("사용자 조회 API {}", id);
        return ResponseEntity.ok(userService.getUser(id));
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody CreateUserRequestDto createUserRequestDto) {
        log.info("사용자 생성 API");
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(userMapper.toUser(createUserRequestDto)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> modifyUser(@PathVariable Long id, @RequestBody UpdateUserRequestDto updateUserRequestDto) {
        log.info("사용자 수정 API {}", id);
        return ResponseEntity.ok(userService.updateUser(id, userMapper.toUser(updateUserRequestDto)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        log.info("사용자 삭제 API {}", id);
        return ResponseEntity.ok(userService.deleteUser(id));
    }
}
