package com.consoltant.consoltant.domain.bestroadmap.service;

import com.consoltant.consoltant.domain.bestroadmap.entity.BestRoadmap;
import com.consoltant.consoltant.domain.bestroadmap.repository.BestRoadmapRepository;
import com.consoltant.consoltant.domain.journey.dto.JourneyResponseDto;
import com.consoltant.consoltant.domain.journey.entity.Journey;
import com.consoltant.consoltant.domain.journey.service.JourneyModuleService;
import com.consoltant.consoltant.domain.portfolio.entity.Portfolio;
import com.consoltant.consoltant.domain.portfolio.service.PortfolioModuleService;
import com.consoltant.consoltant.domain.roadmap.entity.Roadmap;
import com.consoltant.consoltant.domain.user.entity.User;
import com.consoltant.consoltant.domain.user.repository.UserRepository;
import com.consoltant.consoltant.global.exception.BadRequestException;
import com.consoltant.consoltant.util.constant.FinanceKeyword;
import com.consoltant.consoltant.util.constant.JourneyType;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class BestRoadmapService {
    private final BestRoadmapRepository bestRoadmapRepository;
    private final UserRepository userRepository;
    private final PortfolioModuleService portfolioModuleService;
    private final JourneyModuleService journeyModuleService;

    //베스트 로드맵 생성
    public void generateBestRoadmap(){

        @AllArgsConstructor
        class Standard{
            FinanceKeyword financeKeyword;
            Integer salary;
            Long startAsset;
        }

        List<User> userList = userRepository.findAll();

        PriorityQueue<Pair<Long, Pair<Standard,Integer>>> smallPQ = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Pair<Long, Pair<Standard,Integer>>> middlePQ = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Pair<Long, Pair<Standard,Integer>>> bigPQ = new PriorityQueue<>(Collections.reverseOrder());

        Map<Standard, Long> roadmapStandard = new HashMap<>();

        for(User user : userList){

            //기준
            //연봉
            Integer salary = user.getSalary();

            //초기 자산
            Long startAsset = 0L;
            
            List<Journey> journeyList = journeyModuleService.findAllByUserId(user.getId())
                    .stream()
                    .sorted(Comparator.comparing(Journey::getAge))
                    .toList();
            JourneyType startJourneyType = journeyList.get(0).getJourneyType();
            for(Journey journey : journeyList){
                if(journey.getJourneyType() != startJourneyType){
                    break;
                }
                startAsset += journeyList.get(0).getBalance();
            }

            //금융 키워드
            Portfolio portfolio = portfolioModuleService.findByUserId(user.getId()).orElseThrow(()->new BadRequestException("존재하지 앟는 포트폴리오입니다."));
            FinanceKeyword financeKeyword = portfolio.getFinanceKeyword();

            // 시간복잡도 생각말고 정리해서 편하게 계산하자.

            Long smallAssetValue = 0L;
            Integer smallAge = 0;

            Long middleAssetValue = 0L;
            Integer middleAge = 0;

            Long bigAssetValue = 0L;
            Integer bigAge = 0;


            //해당 여정들만 정리
            for(JourneyType journeyType:JourneyType.values()){
                List<Journey> list = journeyList.stream()
                        .filter(s->s.getJourneyType()==journeyType)
                        .toList();

                switch (financeKeyword){
                    case BIG_HAPPINESS:
                    case MIDDLE_HAPPINESS:
                        log.info("asdf");
                        break;
                    case SMALL_HAPPINESS:


                }
            }

            //나이 별 자산
            Map<Integer,Long> assetMap = new HashMap<>();

            //소중대 자산 비교
            for (Journey journey : journeyList) {
                Integer age = journey.getAge();
                assetMap.putIfAbsent(age, 0L);
                assetMap.put(age, assetMap.get(age) + journey.getBalance());
            }

            for(Integer age: assetMap.keySet()){

                //소중대 비교
                if(assetMap.get(age) > smallAssetValue){
                    smallAssetValue = assetMap.get(age);
                    smallAge = age;
                }

                if(assetMap.get(age-4) != null){
                    Long middleAsset = 0L;
                    for(int i = age-4;i<=age;i++){
                        middleAsset+=assetMap.get(i);
                    }

                    if(middleAsset > middleAssetValue){
                        middleAssetValue = middleAsset;
                        middleAge = age;
                    }
                }


                if(assetMap.get(age-9) != null){
                    Long bigAsset = 0L;
                    for(int i = age-9;i<=age;i++){
                        bigAsset+=assetMap.get(i);
                    }

                    if(bigAsset > bigAssetValue){
                        bigAssetValue = bigAsset;
                        bigAge = age;
                    }
                }
            }

            Standard small = new Standard(FinanceKeyword.SMALL_HAPPINESS,salary,startAsset);
            Standard middle = new Standard(FinanceKeyword.MIDDLE_HAPPINESS,salary,startAsset);
            Standard big = new Standard(FinanceKeyword.BIG_HAPPINESS,salary,startAsset);

            smallPQ.add(Pair.of(smallAssetValue,Pair.of(small,smallAge)));
            middlePQ.add(Pair.of(middleAssetValue,Pair.of(middle,middleAge)));
            bigPQ.add(Pair.of(bigAssetValue,Pair.of(big,bigAge)));

        }


    }

    // 베스트 로드맵 찾기
    public Long findBestRoadmap(Long id) {

        User user = userRepository.findById(id).orElseThrow(()->new BadRequestException("존재하지 앟는 사용자입니다."));
        Portfolio portfolio = portfolioModuleService.findByUserId(id).orElseThrow(()->new BadRequestException("존재하지 앟는 포트폴리오입니다."));

        List<Journey> journeyList = journeyModuleService.findAllByUserId(id);

        Long startAsset = 0L;

        JourneyType startJourneyType = journeyList.get(0).getJourneyType();
        for(Journey journey : journeyList){
            if(journey.getJourneyType() != startJourneyType){
                break;
            }
            startAsset += journeyList.get(0).getBalance();
        }

        List<BestRoadmap> roadmapList = bestRoadmapRepository.findAllByFinanceKeywordAndJourneyType(portfolio.getFinanceKeyword(),user.getCurrentJourneyType()).orElseThrow(()->new BadRequestException("베스트 로드맵이 존재하지 않습니다."));

        Long finalStartAsset = startAsset;
        List<BestRoadmap> bestRoadmapList  = roadmapList.stream()
                .filter(s-> finalStartAsset - 200 <= s.getStartAsset() && s.getStartAsset() <= finalStartAsset + 200)
                .filter(s->user.getSalary() - 50 <= s.getSalary() &&  s.getSalary() <= user.getSalary() + 20)
                .toList();

        return bestRoadmapList.get(0).getId();
    }
}
