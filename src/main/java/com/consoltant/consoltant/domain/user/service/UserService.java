package com.consoltant.consoltant.domain.user.service;

import com.consoltant.consoltant.domain.user.dto.CreateUserResponseDto;
import com.consoltant.consoltant.domain.user.dto.UpdateUserResponseDto;
import com.consoltant.consoltant.domain.user.dto.UserResponseDto;
import com.consoltant.consoltant.domain.user.entity.User;
import com.consoltant.consoltant.domain.user.mapper.UserMapper;
import com.consoltant.consoltant.domain.user.repository.UserRepository;
import com.consoltant.consoltant.global.exception.BadRequestException;
import com.consoltant.consoltant.util.api.RestTemplateUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService{
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private RestTemplateUtil restTemplate;

    public UserResponseDto getUser(Long id)  {
        return userMapper.toUserResponseDto(
                userRepository.findById(id)
                .orElseThrow(()->new BadRequestException("존재하지 않는 사용자입니다."))
        );
    }

    public UserResponseDto createUser(User user){
        //User Key 생성하기
        //TODO userKey 저장
        restTemplate.createMember(user.getEmail());

        userRepository.save(user);

        return userMapper.toUserResponseDto(user);
    }

    @Transactional
    public UserResponseDto updateUser(Long id, User user){
        //TODO 사용자 학력 정보 업데이트 + CSV
        //TODO 사용자 계좌 인증 작업 진행
        User entity = userRepository.findById(id)
                .orElseThrow(()->new BadRequestException("존재하지 않는 사용자입니다.")) ;

        entity.changeOf(user);

        return userMapper.toUserResponseDto(entity);
    }

    public Long deleteUser(Long id){
        userRepository.deleteById(id);

        return id;
    }

}
