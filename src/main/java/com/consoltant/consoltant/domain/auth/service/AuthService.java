package com.consoltant.consoltant.domain.auth.service;

import com.consoltant.consoltant.domain.auth.dto.RegisterRequestDto;
import com.consoltant.consoltant.domain.auth.dto.RegisterResponseDto;
import com.consoltant.consoltant.domain.auth.mapper.AuthMapper;
import com.consoltant.consoltant.domain.journey.entity.Journey;
import com.consoltant.consoltant.domain.journey.service.JourneyModuleService;
import com.consoltant.consoltant.domain.portfolio.entity.Portfolio;
import com.consoltant.consoltant.domain.portfolio.entity.PortfolioDocument;
import com.consoltant.consoltant.domain.portfolio.repository.PortfolioElasticRepository;
import com.consoltant.consoltant.domain.portfolio.service.PortfolioModuleService;
import com.consoltant.consoltant.domain.university.entity.University;
import com.consoltant.consoltant.domain.university.repository.UniversityRepository;
import com.consoltant.consoltant.domain.user.dto.CheckAccountResponseDto;
import com.consoltant.consoltant.domain.user.dto.CheckTransactionMessageResponseDto;
import com.consoltant.consoltant.domain.user.dto.IssueAccountResponseDto;
import com.consoltant.consoltant.domain.user.entity.User;
import com.consoltant.consoltant.domain.user.mapper.UserMapper;
import com.consoltant.consoltant.domain.user.repository.UserModuleRepository;
import com.consoltant.consoltant.domain.user.repository.UserRepository;
import com.consoltant.consoltant.global.exception.BadRequestException;
import com.consoltant.consoltant.util.api.RestTemplateUtil;
import com.consoltant.consoltant.util.constant.JourneyType;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.parameters.P;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthService {
    private final RestTemplateUtil restTemplateUtil;

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;
    private final UniversityRepository universityRepository;

    private final PortfolioModuleService portfolioModuleService;
    private final UserModuleRepository userModuleRepository;
    private final UserMapper userMapper;
    private final AuthMapper authMapper;

    private final PortfolioElasticRepository portfolioElasticRepository;
    private final JourneyModuleService journeyModuleService;

    public RegisterResponseDto registerUser(RegisterRequestDto request) {
        userRepository.findByEmail(request.getEmail())
                .ifPresent(user -> {
                    throw new BadRequestException("이미 존재하는 이메일 입니다.");
                });

        String encodePassword = passwordEncoder.encode(request.getPassword());
        String userKey = restTemplateUtil.createMember(request.getEmail());
        University university =universityRepository.findById(1L).orElseThrow(()->new BadRequestException("SQL Error"));

        User user = userRepository.save(request.createUser(encodePassword, userKey, university));
        Portfolio portfolio = Portfolio.builder().user(user).build();
        portfolioModuleService.save(portfolio);

        // portfolioDocument 최초 생성
        PortfolioDocument portfolioDocument = PortfolioDocument.builder()
                .portfolioId(portfolio.getId())
                .userId(user.getId())
                .userEmail(user.getEmail())
                .build();

        portfolioElasticRepository.save(portfolioDocument);

        return authMapper.toRegisterResponseDto(user);
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