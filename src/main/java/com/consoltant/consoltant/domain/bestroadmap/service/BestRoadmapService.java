package com.consoltant.consoltant.domain.bestroadmap.service;

import com.consoltant.consoltant.domain.bestroadmap.entity.BestRoadmap;
import com.consoltant.consoltant.domain.bestroadmap.repository.BestRoadmapRepository;
import com.consoltant.consoltant.domain.journey.entity.Journey;
import com.consoltant.consoltant.domain.journey.service.JourneyModuleService;
import com.consoltant.consoltant.domain.portfolio.entity.Portfolio;
import com.consoltant.consoltant.domain.portfolio.service.PortfolioModuleService;
import com.consoltant.consoltant.domain.roadmap.dto.RoadmapGraphData;
import com.consoltant.consoltant.domain.roadmap.service.RoadmapService;
import com.consoltant.consoltant.domain.user.entity.User;
import com.consoltant.consoltant.domain.user.repository.UserRepository;
import com.consoltant.consoltant.global.exception.BadRequestException;
import com.consoltant.consoltant.util.constant.FinanceKeyword;
import com.consoltant.consoltant.util.constant.JourneyType;
import lombok.*;
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
    private final RoadmapService roadmapService;

    //베스트 로드맵 생성
    public void generateBestRoadmap(){

        @Getter
        @Setter
        @AllArgsConstructor
        class Standard {
            FinanceKeyword financeKeyword;
            Integer salary;
            Long startAsset;
        }

        @Getter
        @Setter
        @AllArgsConstructor
        @ToString
        class Info implements Comparable<Info>{
            Long id;
            Long asset;
            Integer age;
            JourneyType journeyType;

            @Override
            public int compareTo(Info o) {
                return this.asset.compareTo(o.asset);
            }
        }

        List<User> userList = userRepository.findAll();

        Map<Standard, Long> roadmapStandard = new HashMap<>();

        Map<Standard,PriorityQueue<Info>> bestRoadmapStandard = new HashMap<>();

//        for(User user : userList){
//
//            //기준
//            //연봉
//            Integer salary = user.getSalary();
//
//            //초기 자산 취준 ~ 30대 기준
//            Long startAsset = 0L;
//
//            List<Journey> journeyList = journeyModuleService.findAllByUserId(user.getId())
//                    .stream()
//                    .sorted(Comparator.comparing(Journey::getAge))
//                    .toList();
//
//            for(Journey journey : journeyList){
//                if(journey.getJourneyType() == JourneyType.THIRTIES){
//                    startAsset += journeyList.get(0).getBalance();
//                }
//            }
//
//            //금융 키워드
//            Portfolio portfolio = portfolioModuleService.findByUserId(user.getId()).orElseThrow(()->new BadRequestException("존재하지 앟는 포트폴리오입니다."));
//            FinanceKeyword financeKeyword = portfolio.getFinanceKeyword();
//
//            //기준
//            Standard standard = new Standard(financeKeyword,salary,startAsset);
//
//            List<RoadmapGraphData> graph = roadmapService.makeRoadmap(user.getId()).getData();
//
//            PriorityQueue<Pair<Standard,Integer>> pq = new PriorityQueue<>(Collections.reverseOrder());
//
//            //자산 증가량
//            Long asset = 0L;
//            Integer age = 0;
//
//            if(financeKeyword == FinanceKeyword.SMALL_HAPPINESS){
//
//                for(int i = 1; i<graph.size();i++) {
//                    //소확행인 경우 대출 제외
//                    //차이
//                    Long temp = (graph.get(i).getTotalAssetValue() - graph.get(i).getLoanAssetValue())
//                            - (graph.get(i-1).getTotalAssetValue() - graph.get(i-1).getLoanAssetValue());
//
//                    if(temp>asset) {
//                        asset = temp;
//                        age = graph.get(i-1).getAge();
//                    }
//
//                    //새로 바뀌는 경우
//                    if(i==graph.size()-1 || graph.get(i+1).getJourneyType() != graph.get(i+1).getJourneyType()){
//                        bestRoadmapStandard.putIfAbsent(standard, new PriorityQueue<>());
//                        //기준 (키워드, 월급, 초기 자산)에 해당하는 자산 증가량과 당시의 나이를 PQ에 저장
//                        bestRoadmapStandard.get(standard).add(new Info(user.getId(),asset,age,graph.get(i).getJourneyType()));
//                    }
//                }
//
//            }
//            else if(financeKeyword==FinanceKeyword.MIDDLE_HAPPINESS){
//                for(int i = 5; i<graph.size();i++) {
//                    //차이
//                    Long temp = graph.get(i).getTotalAssetValue() - graph.get(i-5).getTotalAssetValue();
//
//                    if(temp>asset) {
//                        asset = temp;
//                        age = graph.get(i-5).getAge();
//                    }
//
//                    //새로 바뀌는 경우
//                    if(i==graph.size()-1 || graph.get(i+1).getJourneyType() != graph.get(i+1).getJourneyType()){
//                        bestRoadmapStandard.putIfAbsent(standard, new PriorityQueue<>());
//                        //기준 (키워드, 월급, 초기 자산)에 해당하는 자산 증가량과 당시의 나이를 PQ에 저장
//                        bestRoadmapStandard.get(standard).add(new Info(user.getId(),asset,age,graph.get(i).getJourneyType()));
//                    }
//                }
//
//            }
//            else {
//                for(int i = 10; i<graph.size();i++) {
//                    //차이
//                    Long temp = graph.get(i).getTotalAssetValue() - graph.get(i-10).getTotalAssetValue();
//
//                    if(temp>asset) {
//                        asset = temp;
//                        age = graph.get(i-10).getAge();
//                    }
//
//                    //새로 바뀌는 경우
//                    if(i==graph.size()-1 || graph.get(i+1).getJourneyType() != graph.get(i+1).getJourneyType()){
//                        bestRoadmapStandard.putIfAbsent(standard, new PriorityQueue<>());
//                        //기준 (키워드, 월급, 초기 자산)에 해당하는 자산 증가량과 당시의 나이를 PQ에 저장
//
//                        bestRoadmapStandard.get(standard).add(new Info(user.getId(),asset,age,graph.get(i).getJourneyType()));
//                    }
//                }
//            }
//
//        }

        for (int i = 0; i < userList.size(); i++) {
            User user = userList.get(i);

            try {
                // 기준
                // 연봉
                Integer salary = user.getSalary();

                // 초기 자산 취준 ~ 30대 기준
                Long startAsset = 0L;

                List<Journey> journeyList = journeyModuleService.findAllByUserId(user.getId())
                        .stream()
                        .sorted(Comparator.comparing(Journey::getAge))
                        .toList();

                for (Journey journey : journeyList) {
                    if (journey.getJourneyType() == JourneyType.THIRTIES) {
                        startAsset += journeyList.get(0).getBalance();
                    }
                }

                // 금융 키워드
                Portfolio portfolio = portfolioModuleService.findByUserId(user.getId())
                        .orElseThrow(() -> new BadRequestException("존재하지 않는 포트폴리오입니다."));
                FinanceKeyword financeKeyword = portfolio.getFinanceKeyword();

                // 기준
                Standard standard = new Standard(financeKeyword, salary, startAsset);

                List<RoadmapGraphData> graph = roadmapService.makeRoadmap(user.getId()).getData();

                PriorityQueue<Pair<Standard, Integer>> pq = new PriorityQueue<>(Collections.reverseOrder());

                // 자산 증가량
                Long asset = 0L;
                Integer age = 0;

                if (financeKeyword == FinanceKeyword.SMALL_HAPPINESS) {
                    for (int j = 1; j < graph.size(); j++) {
                        // 소확행인 경우 대출 제외
                        // 차이
                        Long temp = (graph.get(j).getTotalAssetValue() - graph.get(j).getLoanAssetValue())
                                - (graph.get(j - 1).getTotalAssetValue() - graph.get(j - 1).getLoanAssetValue());

                        if (temp > asset) {
                            asset = temp;
                            age = graph.get(j - 1).getAge();
                        }

                        // 새로 바뀌는 경우
                        if (j == graph.size() - 1 || graph.get(j + 1).getJourneyType() != graph.get(j + 1).getJourneyType()) {
                            bestRoadmapStandard.putIfAbsent(standard, new PriorityQueue<>());
                            // 기준 (키워드, 월급, 초기 자산)에 해당하는 자산 증가량과 당시의 나이를 PQ에 저장
                            bestRoadmapStandard.get(standard).add(new Info(user.getId(), asset, age, graph.get(j).getJourneyType()));
                        }
                    }

                } else if (financeKeyword == FinanceKeyword.MIDDLE_HAPPINESS) {
                    for (int j = 5; j < graph.size(); j++) {
                        // 차이
                        Long temp = graph.get(j).getTotalAssetValue() - graph.get(j - 5).getTotalAssetValue();

                        if (temp > asset) {
                            asset = temp;
                            age = graph.get(j - 5).getAge();
                        }

                        // 새로 바뀌는 경우
                        if (j == graph.size() - 1 || graph.get(j + 1).getJourneyType() != graph.get(j + 1).getJourneyType()) {
                            bestRoadmapStandard.putIfAbsent(standard, new PriorityQueue<>());
                            // 기준 (키워드, 월급, 초기 자산)에 해당하는 자산 증가량과 당시의 나이를 PQ에 저장
                            bestRoadmapStandard.get(standard).add(new Info(user.getId(), asset, age, graph.get(j).getJourneyType()));
                        }
                    }

                } else {
                    for (int j = 10; j < graph.size(); j++) {
                        // 차이
                        Long temp = graph.get(j).getTotalAssetValue() - graph.get(j - 10).getTotalAssetValue();

                        if (temp > asset) {
                            asset = temp;
                            age = graph.get(j - 10).getAge();
                        }

                        // 새로 바뀌는 경우
                        if (j == graph.size() - 1 || graph.get(j + 1).getJourneyType() != graph.get(j + 1).getJourneyType()) {
                            bestRoadmapStandard.putIfAbsent(standard, new PriorityQueue<>());
                            // 기준 (키워드, 월급, 초기 자산)에 해당하는 자산 증가량과 당시의 나이를 PQ에 저장
                            bestRoadmapStandard.get(standard).add(new Info(user.getId(), asset, age, graph.get(j).getJourneyType()));
                        }
                    }
                }

            } catch (Exception e) {
                // 예외가 발생해도 루프는 계속 진행됩니다.
                log.info("에러 사용자 아이디 -> {}",user.getId());

                //e.printStackTrace(); // 예외 로그 출력
            }
        }

        //TODO 저장 전에 기존 로드맵 삭제해야하는데 이건 어떡할까요?

        //베스트 로드맵에 저장
        //실제로는 정규 분포 적용하여 저장
        for(Standard standard : bestRoadmapStandard.keySet()){
            Info info = bestRoadmapStandard.get(standard).poll();
            if(info!=null){
                bestRoadmapRepository.save(
                        BestRoadmap.builder()
                                .age(info.getAge())
                                .financeKeyword(standard.getFinanceKeyword())
                                .journeyType(info.getJourneyType())
                                .startAsset(standard.startAsset)
                                .salary(standard.getSalary())
                                .user(userRepository.findById(info.id).orElseThrow(()-> new BadRequestException("존재하지 않는 사용자입니다.")))
                                .build()
                );
            }
        }
    }

    // 베스트 로드맵 찾기
    public Long findBestRoadmap(Long id) {

        log.info("베스트 로드맵 사용자 아이디 -> {}", id);
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
