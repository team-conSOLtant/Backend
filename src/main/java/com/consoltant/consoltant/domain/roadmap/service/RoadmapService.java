package com.consoltant.consoltant.domain.roadmap.service;

import com.consoltant.consoltant.domain.bestroadmap.dto.BestRoadmapResponseDto;
import com.consoltant.consoltant.domain.bestroadmap.service.BestRoadmapModuleService;
import com.consoltant.consoltant.domain.journey.entity.Journey;
import com.consoltant.consoltant.domain.journey.repository.JourneyRepository;
import com.consoltant.consoltant.domain.journey.service.JourneyModuleService;
import com.consoltant.consoltant.domain.portfolio.entity.Portfolio;
import com.consoltant.consoltant.domain.portfolio.service.PortfolioModuleService;
import com.consoltant.consoltant.domain.product.service.ProductService;
import com.consoltant.consoltant.domain.recommend.dto.RecommendResponseDto;
import com.consoltant.consoltant.domain.recommend.service.RecommendModuleService;
import com.consoltant.consoltant.domain.recommend.service.RecommendService;
import com.consoltant.consoltant.domain.roadmap.dto.RoadmapGraphData;
import com.consoltant.consoltant.domain.roadmap.dto.RoadmapGraphResponseDto;
import com.consoltant.consoltant.domain.roadmap.dto.UserInfo;
import com.consoltant.consoltant.domain.roadmap.entity.Roadmap;
import com.consoltant.consoltant.domain.roadmap.repository.RoadmapRepository;
import com.consoltant.consoltant.domain.user.entity.User;
import com.consoltant.consoltant.domain.user.repository.UserRepository;
import com.consoltant.consoltant.global.exception.BadRequestException;
import com.consoltant.consoltant.util.constant.JourneyType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class RoadmapService {
    private final RoadmapRepository roadmapRepository;
    private final RoadmapModuleService roadmapModuleService;
    private final UserRepository userRepository;
    private final JourneyRepository journeyRepository;
    private final ProductService productService;
    private final BestRoadmapModuleService bestRoadmapModuleService;
    private final PortfolioModuleService portfolioModuleService;
    private final JourneyModuleService journeyModuleService;
    private final RecommendModuleService recommendModuleService;
    private final RecommendService recommendService;

    //모범 로드맵 매칭
    public Long matchingRoadmap(Long userId){

        User user = userRepository.findById(userId).orElse(null);

        List<Journey> jourenyList = journeyModuleService.findAllByUserId(userId);
        Long startAsset = jourenyList.get(0).getBalance();
        Long presentAsset = jourenyList.get(jourenyList.size()-1).getBalance();
        Portfolio portfolio = portfolioModuleService.findById(userId);

        //기존 로드맵 매칭 현황
        List<Roadmap>roadmapList =  roadmapRepository.findAllByUserId(userId).orElse(null);

        //Best 로드맵 매칭
        List<BestRoadmapResponseDto> bestRoadmapResponseDtoList = bestRoadmapModuleService.findAll();

        //월급 - 50 + 20
        List<BestRoadmapResponseDto> bestRoadmapList = bestRoadmapResponseDtoList.stream()
                .filter(s->s.getFinanceKeyword()==portfolio.getFinanceKeyword())
                .filter(s->startAsset - 200 <= s.getStartAsset() && s.getStartAsset() <= startAsset + 200)
                .filter(s->user.getSalary() - 50 <= s.getSalary() &&  s.getSalary() <= user.getSalary() + 20)
                .toList();

//        Random rand = new Random();
//
//        rand.nextInt();

        return null;
    }

    //로드맵 그래프 생성
    public RoadmapGraphResponseDto makeRoadmap(Long userId){

        User user = userRepository.findById(userId).orElseThrow(()->new BadRequestException("존재하지 않는 사용자입니다."));

        List<Journey> jourenyList = journeyModuleService.findAllByUserId(userId);

        Long startAsset = jourenyList.get(0).getBalance();
        Long presentAsset = jourenyList.get(jourenyList.size()-1).getBalance();
        Portfolio portfolio = portfolioModuleService.findByUserId(userId).orElseThrow(()->new BadRequestException("존재하지 않는 포트폴리오입니다."));

        List<Journey> journeyList = journeyRepository.findAllByUserId(userId);

        Map<Integer, Pair<List<Long>, JourneyType>> assetList = new HashMap<>();

        RoadmapGraphResponseDto roadmapGraphResponseDto = new RoadmapGraphResponseDto();

        for(Journey journey: journeyList){
            Integer age = journey.getAge();
            List<Long> assets = Arrays.asList(0L,0L,0L,0L);
            assetList.putIfAbsent(age, Pair.of(assets,journey.getJourneyType()));

            //총합 저장
            assetList.get(age).getFirst().set(0,assetList.get(age).getFirst().get(0) + journey.getBalance());

           switch (journey.getProductType()){
               case DEPOSIT -> {assetList.get(age).getFirst().set(1,assetList.get(age).getFirst().get(1) + journey.getBalance());}
               case SAVING -> {assetList.get(age).getFirst().set(2,assetList.get(age).getFirst().get(2) + journey.getBalance());}
               case LOAN -> {assetList.get(age).getFirst().set(3,assetList.get(age).getFirst().get(3) + journey.getBalance());}
           }
        }

        List<Integer> keySet = new ArrayList<>(assetList.keySet());

        //정렬
        Collections.sort(keySet);

        //여정
        for(Integer age : keySet){
            roadmapGraphResponseDto.getData().add(new RoadmapGraphData(age,assetList.get(age).getSecond(),assetList.get(age).getFirst().get(0),assetList.get(age).getFirst().get(1),assetList.get(age).getFirst().get(2),assetList.get(age).getFirst().get(3)));
        }
        
        //상품
        roadmapGraphResponseDto.setProduct(productService.findAllByUserId(userId, user.getUserKey()));

        //나이
        roadmapGraphResponseDto.setAge(user.getAge());

        //사용자 정보
        UserInfo userInfo = new UserInfo();

        userInfo.setSalary(user.getSalary());
        switch(portfolio.getFinanceKeyword()){
            case BIG_HAPPINESS -> {
                userInfo.setFinanceKeyword(portfolio.getFinanceKeyword().getValue());
                userInfo.setPeriod(10);
            }
            case SMALL_HAPPINESS ->{
                userInfo.setFinanceKeyword(portfolio.getFinanceKeyword().getValue());
                userInfo.setPeriod(5);
            }
            case MIDDLE_HAPPINESS ->{
                userInfo.setFinanceKeyword(portfolio.getFinanceKeyword().getValue());
                userInfo.setPeriod(1);
            }
        }

        userInfo.setStartAsset(startAsset);
        userInfo.setPresentAsset(presentAsset);
        userInfo.setName(user.getName());
        userInfo.setBirthDate(user.getBirthDate());
        userInfo.setCorporateName(user.getCorporateName());
        userInfo.setUniversityName(user.getUniversity().getName());

        roadmapGraphResponseDto.setInfo(userInfo);

        roadmapGraphResponseDto.setRecommend(recommendService.findAllByUserId(userId));

        return roadmapGraphResponseDto;
    }

    // 예상 로드맵 생성
    public RoadmapGraphResponseDto makeExpectedRoadmap(Long userId, List<RecommendResponseDto> list){
        //TODO list가 null이면 사용자가 가진 자산으로만 그래프 제공

        return null;
    }
}
