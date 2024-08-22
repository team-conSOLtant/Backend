package com.consoltant.consoltant.domain.user.service;

import com.consoltant.consoltant.domain.auth.dto.OpenAccountAuthResponseDto;
import com.consoltant.consoltant.domain.university.entity.University;
import com.consoltant.consoltant.domain.university.repository.UniversityRepository;
import com.consoltant.consoltant.domain.user.dto.*;
import com.consoltant.consoltant.domain.user.entity.User;
import com.consoltant.consoltant.domain.user.mapper.UserMapper;
import com.consoltant.consoltant.domain.user.repository.UserModuleRepository;
import com.consoltant.consoltant.domain.user.repository.UserRepository;
import com.consoltant.consoltant.global.exception.BadRequestException;
import com.consoltant.consoltant.util.api.RestTemplateUtil;
import com.consoltant.consoltant.util.api.global.response.RECResponse;
import com.consoltant.consoltant.util.api.dto.createdemanddepositaccount.CreateDemandDepositAccountResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public UserResponseDto getUser(Long id)  {
        return userMapper.toUserResponseDto(
                userModuleRepository.findById(id)
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
        user.addUserKey(restTemplateUtil.createMember(user.getEmail()));

        log.info("사용자 Entity -> {}",user);
        userRepository.save(user);

        return userMapper.toUserResponseDto(user);
    }

    //사용자 학력 정보 업데이트
    @Transactional
    public UserResponseDto createUserAcademy(Long id, User user){
        //TODO 사용자 학력 정보 업데이트 + CSV
        User entity = userModuleRepository.findById(id)
                .orElseThrow(()->new BadRequestException("존재하지 않는 사용자입니다.")) ;

        entity.addAcademyInfo(user);

        return userMapper.toUserResponseDto(entity);
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
    public UserResponseDto createUserAccount(Long id, User user){
        //TODO 사용자 계좌 인증 작업 진행
        User entity = userModuleRepository.findById(id)
                .orElseThrow(()->new BadRequestException("존재하지 않는 사용자입니다.")) ;

        return userMapper.toUserResponseDto(entity);
    }

    //사용자 제거
    public Long deleteUser(Long id){
        userModuleRepository.deleteById(id);

        return id;
    }

    //1원 송금
    public IssueAccountResponseDto issueAccount(Long id, String accountNo){
        User entity = userModuleRepository.findById(id).orElseThrow(()->new BadRequestException("존재하지 않는 사용자입니다."));

        String userKey = entity.getUserKey();

        return userMapper.toIssueResponseDto(restTemplateUtil.openAccountAuth(userKey,accountNo));
    }

    //1원 송금 인증
    public CheckAccountResponseDto checkAccount(Long id, String accountNo, String authText, String authCode){
        User entity = userModuleRepository.findById(id).orElseThrow(()->new BadRequestException("존재하지 않는 사용자입니다."));

        String userKey = entity.getUserKey();

        return userMapper.toCheckAccountResponseDto(restTemplateUtil.checkAuthCode(userKey,accountNo, authText,authCode));
    }

    //1원 송금 메세지 확인
    public CheckTransactionMessageResponseDto checkMessage(Long id, String accountNo, Long transactionUniqueNo){
        User entity = userModuleRepository.findById(id).orElseThrow(()->new BadRequestException("존재하지 않는 사용자입니다."));

        String userKey = entity.getUserKey();

        return userMapper.toCheckTransactionResponseDto(restTemplateUtil.inquireTransactionHistory(userKey,accountNo, transactionUniqueNo));
    }
}
