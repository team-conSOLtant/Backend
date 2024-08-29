package com.consoltant.consoltant.domain.user.service;

import com.consoltant.consoltant.domain.user.dto.UserResponseDto;
import com.consoltant.consoltant.domain.user.mapper.UserMapper;
import com.consoltant.consoltant.domain.user.repository.UserRepository;
import com.consoltant.consoltant.global.exception.BadRequestException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Slf4j
public class UserModuleService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public Long getUserId(String email){
        return userRepository.findByEmail(email).orElseThrow(()->new BadRequestException(("존재하지 않는 사용자입니다."))).getId();
    }

    public String getUserKey(Long userId){
        return userRepository.findById(userId).orElseThrow(()->new BadRequestException(("존재하지 않는 사용자입니다."))).getUserKey();
    }

    public UserResponseDto getUser(Long id)  {
        return userMapper.toUserResponseDto(
                userRepository.findById(id)
                        .orElseThrow(()->new BadRequestException("존재하지 않는 사용자입니다."))
        );
    }
}
