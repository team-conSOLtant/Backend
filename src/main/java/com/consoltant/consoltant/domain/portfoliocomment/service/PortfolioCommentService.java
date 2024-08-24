package com.consoltant.consoltant.domain.portfoliocomment.service;

import com.consoltant.consoltant.domain.notification.entity.Notification;
import com.consoltant.consoltant.domain.notification.service.NotificationModuleService;
import com.consoltant.consoltant.domain.portfolio.entity.Portfolio;
import com.consoltant.consoltant.domain.portfolio.service.PortfolioModuleService;
import com.consoltant.consoltant.domain.portfoliocomment.dto.PortfolioCommentRequestDto;
import com.consoltant.consoltant.domain.portfoliocomment.dto.PortfolioCommentResponseDto;
import com.consoltant.consoltant.domain.portfoliocomment.entity.PortfolioComment;
import com.consoltant.consoltant.domain.portfoliocomment.mapper.PortfolioCommentMapper;
import com.consoltant.consoltant.domain.user.entity.User;
import com.consoltant.consoltant.domain.user.repository.UserRepository;
import com.consoltant.consoltant.util.constant.NotificationType;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PortfolioCommentService {

    private final PortfolioCommentModuleService portfolioCommentModuleService;
    private final PortfolioModuleService portfolioModuleService;
    private final UserRepository userRepository;
    private final NotificationModuleService notificationModuleService;
    private final PortfolioCommentMapper portfolioCommentMapper;

    // 단일 조회
    public PortfolioCommentResponseDto findById(Long id) {
        return portfolioCommentMapper.toPortfolioCommentResponseDto(portfolioCommentModuleService.findById(id));
    }

    // 포트폴리오 ID로 코멘트 리스트 조회
    public List<PortfolioCommentResponseDto> findAllByPortfolioId(Long portfolioId){
        return portfolioCommentModuleService.findAllByPortfolioId(portfolioId).stream()
            .map(portfolioCommentMapper::toPortfolioCommentResponseDto)
            .toList();
    }

    // 등록
    @Transactional
    public PortfolioCommentResponseDto save(PortfolioCommentRequestDto portfolioCommentRequestDto) {
        PortfolioComment portfolioComment = portfolioCommentMapper.toPortfolioComment(portfolioCommentRequestDto);
        Portfolio portfolio = portfolioModuleService.findById(portfolioCommentRequestDto.getPortfolioId());
        User user = userRepository.findById(portfolioCommentRequestDto.getUserId()).orElseThrow();
        portfolioComment.setUser(user);
        portfolioComment.setPortfolio(portfolio);
        //사용자 알림
        notificationModuleService.save(Notification.builder()
            .user(portfolio.getUser())
            .notificationType(NotificationType.PORTFOLIO_COMMENT)
            .content("포트폴리오에 댓글이 달렸습니다.")
            .build());
        return portfolioCommentMapper.toPortfolioCommentResponseDto(portfolioCommentModuleService.save(portfolioComment));
    }

    // 수정
    public PortfolioCommentResponseDto update(Long id, PortfolioCommentRequestDto portfolioCommentRequestDto){
        PortfolioComment portfolioComment = portfolioCommentModuleService.findById(id);
        portfolioComment.update(portfolioCommentRequestDto);
        portfolioCommentModuleService.save(portfolioComment);
        return portfolioCommentMapper.toPortfolioCommentResponseDto(portfolioComment);
    }

    // 삭제
    public void delete(Long id){
        portfolioCommentModuleService.delete(id);
    }
}
