package com.consoltant.consoltant.domain.journey.service;

import com.consoltant.consoltant.domain.journey.dto.*;
import com.consoltant.consoltant.domain.journey.entity.Journey;
import com.consoltant.consoltant.domain.journey.mapper.JourneyMapper;
import com.consoltant.consoltant.domain.journey.repository.JourneyRepository;
import com.consoltant.consoltant.domain.user.entity.User;
import com.consoltant.consoltant.domain.user.repository.UserRepository;
import com.consoltant.consoltant.domain.user.service.UserService;
import com.consoltant.consoltant.global.exception.BadRequestException;
import com.consoltant.consoltant.util.api.RestTemplateUtil;
import com.consoltant.consoltant.util.api.dto.demanddeposit.inquiredemanddepositaccount.InquireDemandDepositAccountResponseDto;
import com.consoltant.consoltant.util.api.dto.loan.inquireloanaccount.InquireLoanAccountResponseDto;
import com.consoltant.consoltant.util.api.dto.loan.inquiremycreditrating.InquireMyCreditRatingResponseDto;
import com.consoltant.consoltant.util.api.dto.saving.inquiresavinginfo.InquireSavingInfoResponseDto;
import com.consoltant.consoltant.util.constant.JourneyType;

import java.util.*;

import com.consoltant.consoltant.util.constant.ProductType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class JourneyService {

    private final JourneyModuleService journeyModuleService;
    private final UserRepository userRepository;
    private final JourneyMapper journeyMapper;
    private final RestTemplateUtil restTemplateUtil;
    private final JourneyRepository journeyRepository;
    private final UserService userService;

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
                .sorted(Comparator.comparing(Journey::getJourneyType))
                .sorted(Comparator.comparing(Journey::getAge))
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


    public  List<JourneyStatsResponseDto> findStatsByUserId(Long userId){

        User user= userRepository.findById(userId).orElseThrow(()->new BadRequestException("존재하지 않는 사용자입니다."));
        String userKey = user.getUserKey();
        String accountNo = user.getAccountNo();

        List<JourneyResponseDto> journeyResponseDtoList = journeyModuleService.findAllByUserId(userId).stream()
                .sorted(Comparator.comparing(Journey::getStartDate))
                .map(journeyMapper::toJourneyResponseDto)
                .toList();

        log.info("현재 여정 정보 -> {}",user.getCurrentJourneyType());
        
        //내 여정 리스트
        List<JourneyStatsResponseDto> productStatsList = new ArrayList<>();

        InquireDemandDepositAccountResponseDto inquireDemandDepositAccountResponseDto = restTemplateUtil.inquireDemandDepositAccount(userKey,accountNo);
        String accountName = inquireDemandDepositAccountResponseDto.getAccountName();
        String accountType = inquireDemandDepositAccountResponseDto.getAccountTypeName();

        boolean flag = false;
        for(JourneyType journeyType: JourneyType.values()){

            //현재 여정을 지난 후부터는 제공 X
            if(flag){
                if(user.getCurrentJourneyType() != journeyType){
                    break;
                }
            }

            if(user.getCurrentJourneyType() == journeyType){
                flag = true;
            }

            List<JourneyResponseDto> journeyList =
                    journeyResponseDtoList.stream()
                            .filter(s->s.getJourneyType()==journeyType)
                            .toList();


            Long demandDepositValue = journeyList.stream()
                            .filter(s->s.getProductType()== ProductType.DEMAND_DEPOSIT)
                            .mapToLong(JourneyResponseDto::getBalance)
                            .sum();
            Long depositValue = journeyList.stream()
                    .filter(s->s.getProductType()== ProductType.DEPOSIT)
                    .mapToLong(JourneyResponseDto::getBalance)
                    .sum();
            Long savingValue = journeyList.stream()
                    .filter(s->s.getProductType()== ProductType.SAVING)
                    .mapToLong(JourneyResponseDto::getBalance)
                    .sum();
            Long loanValue = journeyList.stream()
                    .filter(s->s.getProductType()== ProductType.LOAN)
                    .mapToLong(JourneyResponseDto::getBalance)
                    .sum();

            Long totalAssetValue = demandDepositValue + depositValue + savingValue + loanValue;

            Double demandDeposit = (totalAssetValue > 0L ? demandDepositValue.doubleValue()/totalAssetValue.doubleValue() : 0.0) * 100;
            Double saving = (totalAssetValue > 0L ? savingValue.doubleValue()/totalAssetValue.doubleValue() : 0.0) * 100;
            Double loan = (totalAssetValue > 0L ? depositValue.doubleValue()/totalAssetValue.doubleValue() : 0.0) * 100;
            Double deposit = (totalAssetValue > 0L ? loanValue.doubleValue()/totalAssetValue.doubleValue() : 0.0) * 100;


            String HEX;
            String RGBA = switch (journeyType) {
                case FRESHMAN -> {
                    HEX = "#005DF9";
                    yield "rgba(0, 93, 249, 0.3)";
                }
                case SOPHOMORE -> {
                    HEX = "#59ABE1";
                    yield "rgba(89, 171, 225, 0.3)";
                }
                case JUNIOR -> {
                    HEX = "#5AAEC4";
                    yield "rgba(90, 174, 196, 0.3)";
                }
                case SENIOR -> {
                    HEX = "#5AC4BD";
                    yield "rgba(90, 196, 189, 0.3)";
                }
                case THIRTIES -> {
                    HEX = "#34C759";
                    yield "rgba(52, 199, 89, 0.3)";
                }
                case FORTIES -> {
                    HEX = "#9ECB4F";
                    yield "rgba(158, 203, 79, 0.3)";
                }
                case FIFTIES -> {
                    HEX = "#F7CE46";
                    yield "rgba(247, 206, 70, 0.3)";
                }
                case RETIRED -> {
                    HEX = "#F19A37";
                    yield "rgba(241, 154, 55, 0.3)";
                }
            };

            JourneyStatsResponseDto journeyStatsResponseDto = JourneyStatsResponseDto.builder()
                    .accountType(accountType)
                    .accountName(accountName)
                    .journeyType(journeyType)
                    .journeyTypeName(journeyType.getValue())
                    .HEX(HEX)
                    .RGBA(RGBA)
                    .loan(loan)
                    .totalAssetValue(totalAssetValue)
                    .demandDeposit(demandDeposit)
                    .deposit(deposit)
                    .savings(saving)
                    .demandDepositValue(demandDepositValue)
                    .depositValue(depositValue)
                    .loanValue(loanValue)
                    .savingsValue(savingValue)
                    .totalAssetValue(totalAssetValue)
                    .build();

            productStatsList.add(journeyStatsResponseDto);
        }

        /*
        //사용자 현재 여정 저장
        InquireMyCreditRatingResponseDto inquireMyCreditRatingResponseDto = restTemplateUtil.inquireMyCreditRating(userKey);

        Long totalAssetValue = inquireMyCreditRatingResponseDto.getTotalAssetValue();
        Long demandDepositValue = inquireMyCreditRatingResponseDto.getDemandDepositAssetValue();

        Long savingValue = restTemplateUtil.inquireSavingInfoList(userKey).stream()
                .mapToLong(InquireSavingInfoResponseDto::getDepositBalance)
                .sum();

        Long depositValue = inquireMyCreditRatingResponseDto.getDepositSavingsAssetValue() - savingValue;

        Long loanValue = restTemplateUtil.inquireLoanAccountList(userKey).stream()
                .mapToLong(InquireLoanAccountResponseDto::getLoanBalance)
                .sum();


        Double demandDeposit = (totalAssetValue > 0L ? demandDepositValue.doubleValue()/totalAssetValue.doubleValue() : 0.0) * 100;
        Double saving = (totalAssetValue > 0L ? savingValue.doubleValue()/totalAssetValue.doubleValue() : 0.0) * 100;
        Double loan = (totalAssetValue > 0L ? depositValue.doubleValue()/totalAssetValue.doubleValue() : 0.0) * 100;
        Double deposit = (totalAssetValue > 0L ? loanValue.doubleValue()/totalAssetValue.doubleValue() : 0.0) * 100;


        String HEX;
        String RGBA = switch (user.getCurrentJourneyType()) {
            case FRESHMAN -> {
                HEX = "#005DF9";
                yield "rgba(0, 93, 249, 0.3)";
            }
            case SOPHOMORE -> {
                HEX = "#59ABE1";
                yield "rgba(89, 171, 225, 0.3)";
            }
            case JUNIOR -> {
                HEX = "#5AAEC4";
                yield "rgba(90, 174, 196, 0.3)";
            }
            case SENIOR -> {
                HEX = "#5AC4BD";
                yield "rgba(90, 196, 189, 0.3)";
            }
            case THIRTIES -> {
                HEX = "#34C759";
                yield "rgba(52, 199, 89, 0.3)";
            }
            case FORTIES -> {
                HEX = "#9ECB4F";
                yield "rgba(158, 203, 79, 0.3)";
            }
            case FIFTIES -> {
                HEX = "#F7CE46";
                yield "rgba(247, 206, 70, 0.3)";
            }
            case RETIRED -> {
                HEX = "#F19A37";
                yield "rgba(241, 154, 55, 0.3)";
            }
        };

        JourneyStatsResponseDto journeyStatsResponseDto = JourneyStatsResponseDto.builder()
                .accountType(accountType)
                .accountName(accountName)
                .journeyType(user.getCurrentJourneyType())
                .journeyTypeName(user.getCurrentJourneyType().getValue())
                .HEX(HEX)
                .RGBA(RGBA)
                .loan(loan)
                .totalAssetValue(totalAssetValue)
                .demandDeposit(demandDeposit)
                .deposit(deposit)
                .savings(saving)
                .demandDepositValue(demandDepositValue)
                .depositValue(depositValue)
                .loanValue(loanValue)
                .savingsValue(savingValue)
                .totalAssetValue(totalAssetValue)
                .build();

        productStatsList.add(journeyStatsResponseDto);
        */

        return productStatsList;
    }

    //자산 그래프 (자산 별 통계 그래프)
    public JourneyGraphResponseDto findGraphByUserId(Long userId) {

        User user = userRepository.findById(userId).orElseThrow(()->new BadRequestException("존재하지 않는 사용자입니다."));

        List<Journey> journeyList = journeyRepository.findAllByUserId(userId);
        Map<Integer, Long> assetList = new HashMap<>();
        Map<Integer, String> journeyValueMap = new HashMap<>();
        Map<Integer, JourneyType> journeyTypeMap = new HashMap<>();

        JourneyGraphResponseDto journeyGraphResponseDto = new JourneyGraphResponseDto();
        journeyGraphResponseDto.setAge(user.getAge());

        for(Journey journey: journeyList){
            Integer age = journey.getAge();
            assetList.putIfAbsent(age, 0L);
            assetList.put(age,assetList.get(age) + journey.getBalance());
            journeyTypeMap.put(age, journey.getJourneyType());
            journeyValueMap.put(age, journey.getJourneyType().getValue());
        }

        List<Integer> keySet = new ArrayList<>(assetList.keySet());

        //정렬
        Collections.sort(keySet);

        for(Integer age : keySet){
            journeyGraphResponseDto.getData().add(new GraphData(age,assetList.get(age), journeyTypeMap.get(age), journeyValueMap.get(age)));
        }

        return journeyGraphResponseDto;
    }
}