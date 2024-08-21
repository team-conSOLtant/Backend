package com.consoltant.consoltant.domain.user.service;

import com.consoltant.consoltant.domain.auth.dto.RegisterRequestDto;
import com.consoltant.consoltant.domain.university.entity.University;
import com.consoltant.consoltant.domain.university.repository.UniversityRepository;
import com.consoltant.consoltant.domain.user.dto.UserResponseDto;
import com.consoltant.consoltant.domain.user.entity.User;
import com.consoltant.consoltant.domain.user.mapper.UserMapper;
import com.consoltant.consoltant.domain.user.repository.UserRepository;
import com.consoltant.consoltant.global.exception.BadRequestException;
import com.consoltant.consoltant.util.api.RestTemplateUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Slf4j
public class UserService{
    private final UserRepository userRepository;
    private final UniversityRepository universityRepository;
    private final UserMapper userMapper;
    private final RestTemplateUtil restTemplateUtil;

    public UserResponseDto getUser(Long id)  {
        return userMapper.toUserResponseDto(
                userRepository.findById(id)
                .orElseThrow(()->new BadRequestException("존재하지 않는 사용자입니다."))
        );
    }

    //사용자 생성
    public UserResponseDto createUser(User user){
        log.info("사용자 생성 Service -> {}",user.getEmail());

        //제휴 대학 연결
        University university = universityRepository.findById(1L).orElseThrow(()->new BadRequestException("SQL Error"));
        user.addUniversity(university);

        //userKey 생성하기
//        user.addUserKey(restTemplateUtil.createMember(user.getEmail()));

        log.info("사용자 Entity -> {}",user);
        userRepository.save(user);

        return userMapper.toUserResponseDto(user);
    }

    //사용자 학력 정보 업데이트
    @Transactional
    public UserResponseDto createUserAcademy(Long id, User user){
        //TODO 사용자 학력 정보 업데이트 + CSV
        User entity = userRepository.findById(id)
                .orElseThrow(()->new BadRequestException("존재하지 않는 사용자입니다.")) ;

        entity.addAcademyInfo(user);

        return userMapper.toUserResponseDto(entity);
    }

    //사용자 계좌 정보 업데이트
    @Transactional
    public UserResponseDto createUserAccount(Long id, User user){
        //TODO 사용자 계좌 인증 작업 진행
        User entity = userRepository.findById(id)
                .orElseThrow(()->new BadRequestException("존재하지 않는 사용자입니다.")) ;

        entity.addAccountInfo();

        return userMapper.toUserResponseDto(entity);
    }

    public Long deleteUser(Long id){
        userRepository.deleteById(id);

        return id;
    }
}
