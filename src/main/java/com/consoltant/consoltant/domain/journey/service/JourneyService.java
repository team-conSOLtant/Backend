package com.consoltant.consoltant.domain.journey.service;

import com.consoltant.consoltant.domain.journey.dto.JourneyRequestDto;
import com.consoltant.consoltant.domain.journey.dto.JourneyResponseDto;
import com.consoltant.consoltant.domain.journey.entity.Journey;
import com.consoltant.consoltant.domain.journey.mapper.JourneyMapper;
import com.consoltant.consoltant.domain.user.entity.User;
import com.consoltant.consoltant.domain.user.repository.UserRepository;
import com.consoltant.consoltant.util.constant.JourneyType;
import java.util.Comparator;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JourneyService {

    private final JourneyModuleService journeyModuleService;
    private final UserRepository userRepository;
    private final JourneyMapper journeyMapper;

    // 단일 조회
    public JourneyResponseDto findById(Long id) {
        return journeyMapper.toJourneyResponseDto(journeyModuleService.findById(id));
    }

    //유저 Id와 Journey 타입으로 조회
    public JourneyResponseDto findByUserIdAndJourneyType(Long userId, JourneyType journeyType) {
        return journeyMapper.toJourneyResponseDto(journeyModuleService.findByUserIdAndJourneyType(userId,journeyType));
    }

    // 유저 ID로 여정 목록 조회
    public List<JourneyResponseDto> findAllByUserId(Long userId){
        return journeyModuleService.findAllByUserId(userId).stream()
            .sorted(Comparator.comparing(Journey::getStartDate))
            .map(journeyMapper::toJourneyResponseDto)
            .toList();
    }

    // 등록
    public JourneyResponseDto save(JourneyRequestDto journeyRequestDto) {
        User user = userRepository.findById(journeyRequestDto.getUserId()).orElseThrow();
        Journey journey = journeyMapper.toJourney(journeyRequestDto);
        journey.setUser(user);
        return journeyMapper.toJourneyResponseDto(journeyModuleService.save(journey));
    }

    // 수정
    public JourneyResponseDto update(Long id, JourneyRequestDto journeyRequestDto){
        Journey journey = journeyModuleService.findById(id);
        journey.update(journeyRequestDto);
        journeyModuleService.save(journey);
        return journeyMapper.toJourneyResponseDto(journey);
    }

    // 삭제
    public void delete(Long id){
        journeyModuleService.delete(id);
    }
}