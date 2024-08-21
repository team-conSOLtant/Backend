package com.consoltant.consoltant.domain.portfoliocomment.service;

import com.consoltant.consoltant.domain.portfolio.entity.Portfolio;
import com.consoltant.consoltant.domain.portfolio.service.PortfolioModuleService;
import com.consoltant.consoltant.domain.portfoliocomment.dto.PortfolioCommentRequestDto;
import com.consoltant.consoltant.domain.portfoliocomment.dto.PortfolioCommentResponseDto;
import com.consoltant.consoltant.domain.portfoliocomment.entity.PortfolioComment;
import com.consoltant.consoltant.domain.portfoliocomment.mapper.PortfolioCommentMapper;
import com.consoltant.consoltant.domain.user.entity.User;
import com.consoltant.consoltant.domain.user.repository.UserRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PortfolioCommentService {

    private final PortfolioCommentModuleService portfolioCommentModuleService;
    private final PortfolioModuleService portfolioModuleService;
    private final UserRepository userRepository;
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
    public PortfolioCommentResponseDto save(PortfolioCommentRequestDto portfolioCommentRequestDto) {
        PortfolioComment portfolioComment = portfolioCommentMapper.toPortfolioComment(portfolioCommentRequestDto);
        Portfolio portfolio = portfolioModuleService.findById(portfolioCommentRequestDto.getPortfolioId());
        User user = userRepository.findById(portfolioCommentRequestDto.getUserId()).orElseThrow();
        portfolioComment.setUser(user);
        portfolioComment.setPortfolio(portfolio);
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
