package com.consoltant.consoltant.domain.user.service;

import com.consoltant.consoltant.domain.university.entity.University;
import com.consoltant.consoltant.domain.university.repository.UniversityRepository;
import com.consoltant.consoltant.domain.user.dto.*;
import com.consoltant.consoltant.domain.user.entity.User;
import com.consoltant.consoltant.domain.user.mapper.UserMapper;
import com.consoltant.consoltant.domain.user.repository.UserModuleRepository;
import com.consoltant.consoltant.domain.user.repository.UserRepository;
import com.consoltant.consoltant.global.exception.BadRequestException;
import com.consoltant.consoltant.util.api.RestTemplateUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@Service
@Slf4j
public class UserService{
    private final UserRepository userRepository;
    private final UserModuleRepository userModuleRepository;
    private final UniversityRepository universityRepository;
    private final UserMapper userMapper;
    private final RestTemplateUtil restTemplateUtil;

    public Long getUserId(String email){
        return userRepository.findByEmail(email).orElseThrow(()->new BadRequestException(("존재하지 않는 사용자입니다."))).getId();
    }

    public String getUserKey(Long userId){
        return userRepository.findById(userId).orElseThrow(()->new BadRequestException(("존재하지 않는 사용자입니다."))).getUserKey();
    }

    public UserResponseDto getUser(Long id)  {
        return userMapper.toUserResponseDto(
                userModuleRepository.findById(id)
                .orElseThrow(()->new BadRequestException("존재하지 않는 사용자입니다."))
        );
    }

    //사용자 학력 정보 업데이트
    @Transactional
    public UserResponseDto createUserAcademy(Long id, CreateUserAcademyRequestDto createUserAcademyRequestDto, MultipartFile subject){
        //TODO CSV
        User user = userModuleRepository.findById(id)
                .orElseThrow(()->new BadRequestException("존재하지 않는 사용자입니다.")) ;

        University university = universityRepository.findByName(createUserAcademyRequestDto.getUniversity());
        Double totalGpa = 4.1;
        Double majorGpa = 4.2;
        Integer totalSumGpa = 140;
        user.addAcademyInfo(university,createUserAcademyRequestDto, totalGpa, majorGpa, totalSumGpa);

        return userMapper.toUserResponseDto(user);
    }

    //사용자 계좌 정보 추가
    @Transactional
    public CreateAccountResponseDto createAccount(Long id, String accountTypeUniqueNo){
        User entity = userModuleRepository.findById(id)
                .orElseThrow(()->new BadRequestException("존재하지 않는 사용자입니다.")) ;

        String userKey = entity.getUserKey();
        
        //계좌 정보는 인증 후에 저장
        return userMapper.toCreateAccountResponseDto(restTemplateUtil.createDemandDepositAccount(userKey, accountTypeUniqueNo));
    }

    //사용자 계좌 정보 업데이트
    @Transactional
    public UserResponseDto createUserAccount(Long id, CreateUserAccountRequestDto createUserAccountRequestDto){
        User entity = userModuleRepository.findById(id)
                .orElseThrow(()->new BadRequestException("존재하지 않는 사용자입니다.")) ;

        entity.addAccountInfo(createUserAccountRequestDto);
        User saveEntity = userModuleRepository.save(entity);

        return userMapper.toUserResponseDto(saveEntity);
    }

    //사용자 제거
    public Long deleteUser(Long id){
        userModuleRepository.deleteById(id);

        return id;
    }

    //학력 정보 확인
    public Boolean checkAcademy(Long id){
        User user = userRepository.findById(id).orElseThrow(()->new BadRequestException("존재하지 않는 사용자입니다."));

        return user.getUniversity() != null;
    }

    //계좌 정보 확인
    public Boolean checkAccount(Long id){
        User user = userRepository.findById(id).orElseThrow(()->new BadRequestException("존재하지 않는 사용자입니다."));

        return user.getAccountNo() != null;
    }
}
