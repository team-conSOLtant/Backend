package com.consoltant.consoltant.domain.portfolio.service;

import com.consoltant.consoltant.domain.course.entity.Course;
import com.consoltant.consoltant.domain.course.service.CourseModuleService;
import com.consoltant.consoltant.domain.matching.entity.Matching;
import com.consoltant.consoltant.domain.matching.mapper.MatchingMapper;
import com.consoltant.consoltant.domain.matching.service.MatchingModuleService;
import com.consoltant.consoltant.domain.portfolio.dto.PortfolioRequestDto;
import com.consoltant.consoltant.domain.portfolio.dto.PortfolioResponseDto;
import com.consoltant.consoltant.domain.portfolio.entity.Portfolio;
import com.consoltant.consoltant.domain.portfolio.mapper.PortfolioMapper;
import com.consoltant.consoltant.domain.user.entity.User;
import com.consoltant.consoltant.domain.user.repository.UserRepository;
import java.util.List;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PortfolioService {

    private final PortfolioModuleService portfolioModuleService;
    private final MatchingModuleService matchingModuleService;
    private final CourseModuleService courseModuleService;
    private final PortfolioMapper portfolioMapper;
    private final MatchingMapper matchingMapper;
    private final UserRepository userRepository;

    public PortfolioResponseDto findById(Long id) {
        return portfolioMapper.toPortfolioResponseDto(portfolioModuleService.findById(id));
    }

    public PortfolioResponseDto save(PortfolioRequestDto portfolioRequestDto) {
        Portfolio portfolio
                = portfolioMapper.toPortfolio(portfolioRequestDto);
        User user = userRepository.findById(portfolioRequestDto.getUserId()).orElseThrow();
        portfolio.setUser(user);
        return portfolioMapper.toPortfolioResponseDto(portfolioModuleService.save(portfolio));
    }

    public PortfolioResponseDto update(Long id, PortfolioRequestDto portfolioRequestDto){
        Portfolio portfolio = portfolioModuleService.findById(id);
        portfolio.update(portfolioRequestDto);
        portfolioModuleService.save(portfolio);
        return portfolioMapper.toPortfolioResponseDto(portfolio);
    }

    public void delete(Long id){
        portfolioModuleService.delete(id);
    }

    @Transactional
    public PortfolioResponseDto getMatchingSeniorPortfolio(Long userId){

        User user = userRepository.findById(userId).orElseThrow();
        Long universityId = user.getUniversity().getId();

        List<Portfolio> seniorPortfolios = portfolioModuleService.findAllByUniversityIdAndIsEmployedTrue(universityId); //취직한 선배들의 포트폴리오 전부 조회
        Set<Long> alreadyMatchingPortfolioIdList = matchingModuleService.findAllByUserId(userId); //이미 매칭됐었던 포트폴리오 아이디 조회
        List<Course> userCourses = courseModuleService.findAllByUserId(userId);  //후배의 수강 리스트

        Portfolio bestPortfolio = null;
        int maxScore = 0;

        for(Portfolio seniorPortfolio : seniorPortfolios){
            //이미 추천한적이 있다면 건너뜀
            if(alreadyMatchingPortfolioIdList.contains(seniorPortfolio.getId())){
                continue;
            }

            int score = 0;
            // 1. 전공이 일치여부 확인 (score 10점 부여)
            if(user.getMajor().equals(seniorPortfolio.getUser().getMajor())){
                score += 10;
            }

            // 2. 수강 과목 겹치는 개수 세기
            List<Course> seniorCourses = courseModuleService.findAllByUserId(seniorPortfolio.getUser().getId());
            long matchingCourseCount = userCourses.stream()
                .filter(userCourse -> seniorCourses.stream()
                    .anyMatch(seniorCourse -> seniorCourse.getSubject().getId().equals(userCourse.getSubject().getId())))
                .count();

            score += matchingCourseCount * 2;   //일치하는 과목 하나당 2점씩

            // 3. 학점 유사도 확인
            double gpaDifference = Math.abs(user.getTotalGpa() - seniorPortfolio.getTotalGpa());
            if (gpaDifference <= 0.5) {
                score += 3;
            } else if (gpaDifference <= 1) {
                score += 1;
            }

            if (score > maxScore) {
                maxScore = score;
                bestPortfolio = seniorPortfolio;
            }
        }

        //새로운 추천이 없을 경우 기존 추천에 있는 것 다시 추천해주기
        if(bestPortfolio == null){
            for(Long id : alreadyMatchingPortfolioIdList){
                return portfolioMapper.toPortfolioResponseDto(portfolioModuleService.findById(id));
            }
        }
        Matching matching = new Matching();
        matching.setUser(user);
        matching.setPortfolio(bestPortfolio);

        matchingModuleService.save(matching);   //매칭 기록 저장
        return portfolioMapper.toPortfolioResponseDto(bestPortfolio);
    }
    
    
    
}