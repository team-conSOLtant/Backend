package com.consoltant.consoltant.domain.journey.service;

import com.consoltant.consoltant.domain.journey.entity.Journey;
import com.consoltant.consoltant.domain.journey.repository.JourneyRepository;
import com.consoltant.consoltant.domain.user.entity.User;
import com.consoltant.consoltant.util.constant.JourneyType;

import java.time.LocalDate;
import java.util.List;

import com.consoltant.consoltant.util.constant.ProductType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JourneyModuleService {
    private final JourneyRepository journeyRepository;

    public List<Journey> findAll(){
        return journeyRepository.findAll();
    }

    // Journey ID로 단일 조회
    public Journey findById(Long id) {
        return journeyRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Invalid Journey ID"));
    }

    // 유저 ID로 모든 Journey 조회
    public List<Journey> findAllByUserId(Long userId) {
        return journeyRepository.findAllByUserId(userId);
    }

    //유저 Id와 Journey 타입으로 조회
    public Journey findByUserIdAndJourneyType(Long userId, JourneyType journeyType){
        return journeyRepository.findByUserIdAndJourneyType(userId, journeyType);
    }

    // Journey 저장
    public Journey save(Journey journey) {
        return journeyRepository.save(journey);
    }

    // Journey 저장
    public void defaultSave(User user) {


        LocalDate startDate = LocalDate.now();
        LocalDate endDate = LocalDate.of(LocalDate.now().getYear(),12,31);

        Journey demandDepositJourney = Journey.builder()
                .user(user)
                .age(user.getAge())
                .balance(0L)
                .startDate(startDate)
                .endDate(endDate)
                .journeyType(user.getCurrentJourneyType())
                .productType(ProductType.DEMAND_DEPOSIT)
                .build();

        Journey depositJourney = Journey.builder()
                .user(user)
                .age(user.getAge())
                .balance(0L)
                .startDate(startDate)
                .endDate(endDate)
                .journeyType(user.getCurrentJourneyType())
                .productType(ProductType.DEPOSIT)
                .build();

        Journey savingJourney = Journey.builder()
                .user(user)
                .age(user.getAge())
                .balance(0L)
                .startDate(startDate)
                .endDate(endDate)
                .journeyType(user.getCurrentJourneyType())
                .productType(ProductType.SAVING)
                .build();

        Journey loanJourney = Journey.builder()
                .user(user)
                .age(user.getAge())
                .balance(0L)
                .startDate(startDate)
                .endDate(endDate)
                .journeyType(user.getCurrentJourneyType())
                .productType(ProductType.LOAN)
                .build();

        journeyRepository.save(demandDepositJourney);
        journeyRepository.save(depositJourney);
        journeyRepository.save(savingJourney);
        journeyRepository.save(loanJourney);

    }

    // Journey 삭제
    public void delete(Long id) {
        journeyRepository.deleteById(id);
    }
}