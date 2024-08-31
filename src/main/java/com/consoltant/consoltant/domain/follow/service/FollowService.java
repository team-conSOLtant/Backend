package com.consoltant.consoltant.domain.follow.service;

import com.consoltant.consoltant.domain.award.service.AwardModuleService;
import com.consoltant.consoltant.domain.career.entity.Career;
import com.consoltant.consoltant.domain.career.service.CareerModuleService;
import com.consoltant.consoltant.domain.certification.service.CertificationModuleService;
import com.consoltant.consoltant.domain.follow.dto.FollowRequestDto;
import com.consoltant.consoltant.domain.follow.dto.FollowResponseDto;
import com.consoltant.consoltant.domain.follow.entity.Follow;
import com.consoltant.consoltant.domain.follow.mapper.FollowMapper;
import com.consoltant.consoltant.domain.notification.entity.Notification;
import com.consoltant.consoltant.domain.notification.service.NotificationModuleService;
import com.consoltant.consoltant.domain.portfolio.dto.PortfolioSearchResponseDto;
import com.consoltant.consoltant.domain.portfolio.entity.Portfolio;
import com.consoltant.consoltant.domain.portfolio.service.PortfolioModuleService;
import com.consoltant.consoltant.domain.project.service.ProjectModuleService;
import com.consoltant.consoltant.domain.user.entity.User;
import com.consoltant.consoltant.domain.user.repository.UserRepository;
import com.consoltant.consoltant.global.exception.BadRequestException;
import com.consoltant.consoltant.util.constant.NotificationType;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FollowService {

    private final FollowModuleService followModuleService;
    private final FollowMapper followMapper;
    private final PortfolioModuleService portfolioModuleService;
    private final AwardModuleService awardModuleService;
    private final CertificationModuleService certificationModuleService;
    private final ProjectModuleService projectModuleService;
    private final NotificationModuleService notificationModuleService;
    private final CareerModuleService careerModuleService;
    private final UserRepository userRepository;

    //팔로우 팔로잉 조회용
    public List<FollowResponseDto> findAllByUserIdToResponse(Long userId){
        return followModuleService.findAllByUserId(userId).stream()
            .map(followMapper::toFollowResponseDto)
            .toList();
    }

    // 유저 ID로 팔로잉 리스트 조회
    public List<PortfolioSearchResponseDto> findAllByUserId(Long userId){

        List<FollowResponseDto> followList = followModuleService.findAllByUserId(userId).stream()
            .map(followMapper::toFollowResponseDto)
            .toList();

        List<PortfolioSearchResponseDto> portfolioSerachList = new ArrayList<>();
        for(FollowResponseDto followResponseDto : followList){
            Long portfolioId = followResponseDto.getPortfolioId();
            Portfolio portfolio = portfolioModuleService.findById(portfolioId);

            List<Career> careerList = careerModuleService.findAllByPortfolioId(portfolioId).stream()
                .sorted(Comparator.comparing(Career::getStartDate).reversed())
                .toList();
            String careerTitle = careerList.isEmpty() ? null : careerList.get(0).getCompany();

            PortfolioSearchResponseDto responseDto = PortfolioSearchResponseDto.builder()
                .userName(portfolio.getUser().getName())
                .portfolioId(portfolioId)
                .universityName(portfolio.getUser().getUniversity().getName())
                .major(portfolio.getUser().getMajor())
                .totalGpa(portfolio.getTotalGpa())
                .majorGpa(portfolio.getMajorGpa())
                .job(portfolio.getJob())
                .myKeyword(portfolio.getMyKeyword())
                .careerTitle(careerTitle)
                .awardCount(awardModuleService.countAllByPortfolioId(portfolio.getId()))
                .certificationCount(certificationModuleService.countAllByPortfolioId(portfolio.getId()))
                .projectCount(projectModuleService.countAllByPortfolioId(portfolio.getId()))
                .build();
            portfolioSerachList.add(responseDto);
        }
        return portfolioSerachList;
    }

    //포폴 아이디로 팔로워 리스트 조회
    public List<PortfolioSearchResponseDto> findAllByPortfolioId(Long portfolioId){
        List<FollowResponseDto> followerList = followModuleService.findAllByPortfolioId(portfolioId)
            .stream()
            .map(followMapper::toFollowResponseDto)
            .toList();

        List<PortfolioSearchResponseDto> portfolioSerachList = new ArrayList<>();


        for(FollowResponseDto followResponseDto : followerList){
            Long userId = followResponseDto.getUserId();
            Portfolio portfolio = portfolioModuleService.findByUserId(userId).orElseThrow();

            List<Career> careerList = careerModuleService.findAllByPortfolioId(portfolio.getId()).stream()
                .sorted(Comparator.comparing(Career::getStartDate).reversed())
                .toList();
            String careerTitle = careerList.isEmpty() ? null : careerList.get(0).getCompany();

            PortfolioSearchResponseDto responseDto = PortfolioSearchResponseDto.builder()
                .userName(portfolio.getUser().getName())
                .portfolioId(portfolio.getId())
                .universityName(portfolio.getUser().getUniversity().getName())
                .major(portfolio.getUser().getMajor())
                .totalGpa(portfolio.getTotalGpa())
                .majorGpa(portfolio.getMajorGpa())
                .job(portfolio.getJob())
                .myKeyword(portfolio.getMyKeyword())
                .careerTitle(careerTitle)
                .awardCount(awardModuleService.countAllByPortfolioId(portfolio.getId()))
                .certificationCount(certificationModuleService.countAllByPortfolioId(portfolio.getId()))
                .projectCount(projectModuleService.countAllByPortfolioId(portfolio.getId()))
                .build();
            portfolioSerachList.add(responseDto);
        }
        return portfolioSerachList;
    }

    // 등록
    @Transactional
    public FollowResponseDto save(FollowRequestDto followRequestDto) {
        User user = userRepository.findById(followRequestDto.getUserId()).orElseThrow();
        Portfolio portfolio = portfolioModuleService.findById(followRequestDto.getPortfolioId());

        // 이미 팔로우 중인지 확인
        if (followModuleService.isAlreadyFollowing(user, portfolio)) {
            throw new BadRequestException("이미 해당 포트폴리오를 팔로우하고 있습니다.");
        }

        Follow follow = followMapper.toFollow(followRequestDto);
        follow.setPortfolio(portfolio);
        follow.setUser(user);
        //사용자 알림
        notificationModuleService.save(Notification.builder()
            .user(portfolio.getUser())
            .notificationType(NotificationType.PORTFOLIO_FOLLOW)
            .content(user.getName() + " 님이 당신을 팔로우 했습니다.")
            .build());
        return followMapper.toFollowResponseDto(followModuleService.save(follow));
    }

    // 삭제
    public void delete(Long id){
        followModuleService.delete(id);
    }
}