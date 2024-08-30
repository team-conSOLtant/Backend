package com.consoltant.consoltant.domain.roadmap.service;

import com.consoltant.consoltant.domain.bestroadmap.dto.BestRoadmapResponseDto;
import com.consoltant.consoltant.domain.bestroadmap.service.BestRoadmapModuleService;
import com.consoltant.consoltant.domain.journey.dto.GraphData;
import com.consoltant.consoltant.domain.journey.entity.Journey;
import com.consoltant.consoltant.domain.journey.repository.JourneyRepository;
import com.consoltant.consoltant.domain.journey.service.JourneyModuleService;
import com.consoltant.consoltant.domain.portfolio.entity.Portfolio;
import com.consoltant.consoltant.domain.portfolio.service.PortfolioModuleService;
import com.consoltant.consoltant.domain.product.dto.ProductInfo;
import com.consoltant.consoltant.domain.product.service.ProductService;
import com.consoltant.consoltant.domain.recommend.dto.RecommendResponseDto;
import com.consoltant.consoltant.domain.recommend.entity.Recommend;
import com.consoltant.consoltant.domain.recommend.repository.RecommendRepository;
import com.consoltant.consoltant.domain.recommend.service.RecommendModuleService;
import com.consoltant.consoltant.domain.recommend.service.RecommendService;
import com.consoltant.consoltant.domain.roadmap.dto.*;
import com.consoltant.consoltant.domain.roadmap.entity.Roadmap;
import com.consoltant.consoltant.domain.roadmap.repository.RoadmapRepository;
import com.consoltant.consoltant.domain.user.entity.User;
import com.consoltant.consoltant.domain.user.repository.UserRepository;
import com.consoltant.consoltant.domain.user.service.UserService;
import com.consoltant.consoltant.global.exception.BadRequestException;
import com.consoltant.consoltant.util.api.RestTemplateUtil;
import com.consoltant.consoltant.util.api.dto.demanddeposit.inquiredemanddeposit.InquireDemandDepositResponseDto;
import com.consoltant.consoltant.util.api.dto.deposit.inquiredepositproducts.InquireDepositProductsResponseDto;
import com.consoltant.consoltant.util.api.dto.loan.inquireloanproduct.InquireLoanProductResponseDto;
import com.consoltant.consoltant.util.api.dto.saving.inquiresavingproducts.InquireSavingProductsResponseDto;
import com.consoltant.consoltant.util.constant.FinanceKeyword;
import com.consoltant.consoltant.util.constant.JourneyType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
    private final RecommendRepository recommendRepository;
    private final UserService userService;
    private final RestTemplateUtil restTemplateUtil;

    //모범 로드맵 매칭
    public void matchingRoadmap(Long userId){

        User user = userRepository.findById(userId).orElseThrow(()->new BadRequestException("존재하지 않는 사용자입니다."));

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

        for(BestRoadmapResponseDto responseDto : bestRoadmapResponseDtoList){
            if(Objects.equals(responseDto.getUser().getId(), user.getId()))continue;
            if(roadmapList!=null){
                if(roadmapList.stream().filter(s-> Objects.equals(s.getId(), responseDto.getId())).findAny().orElse(null)==null){
                    roadmapRepository.save(
                            Roadmap.builder()
                                    .roadmapUser(responseDto.getUser())
                                    .user(user)
                                    .build()
                    );
                    break;
                }
            }

            else{
                roadmapRepository.save(
                        Roadmap.builder()
                                .roadmapUser(responseDto.getUser())
                                .user(user)
                                .build()
                );
                break;
            }
        }
    }

    //로드맵 그래프 생성
    public RoadmapGraphResponseDto makeRoadmap(Long userId){

        User user = userRepository.findById(userId).orElseThrow(()->new BadRequestException("존재하지 않는 사용자입니다."));

        List<Journey> jourenyList = journeyModuleService.findAllByUserId(userId);

        if(jourenyList != null){

        }
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
    public ExpectRoadmapGraphResponseDto makeExpectedRoadmap(Long userId, List<PreRecommendDto> preRecommend){

        User user = userRepository.findById(userId).orElseThrow(()->new BadRequestException("존재하지 않는 사용자입니다."));
        FinanceKeyword financeKeyword = portfolioModuleService.findByUserId(userId).orElseThrow((()->new BadRequestException("존재하지 않는 사용자입니다."))).getFinanceKeyword();

        RoadmapGraphResponseDto graphResponseDto = makeRoadmap(userId);

        ExpectRoadmapGraphResponseDto roadmapGraphResponseDto = new ExpectRoadmapGraphResponseDto();

        //기존 유저 정보 제공
        roadmapGraphResponseDto.setInfo(graphResponseDto.getInfo());
        roadmapGraphResponseDto.setAge(graphResponseDto.getAge());
        roadmapGraphResponseDto.setProduct(graphResponseDto.getProduct());
        roadmapGraphResponseDto.setRecommend(graphResponseDto.getRecommend());

        //현재 나이 자본 데이터 저장
        roadmapGraphResponseDto.getData().add(graphResponseDto.getData().get(graphResponseDto.getData().size()-1));

        //TODO preRecommend null이거나 빈 배열이면 사용자가 가진 자산으로만 그래프 제공
        if(!(preRecommend == null || preRecommend.isEmpty())){
            roadmapGraphResponseDto.getPreRecommend().addAll(preRecommend);
        }

        //현재 연봉 기준 매년 증가하는 금액 체크
        //연봉 적금 예금 대출

        // 수시 입출금 += 연봉 - 대출 이자 1년치
        List<InquireDemandDepositResponseDto> demandDepositList = new ArrayList<>(
                roadmapGraphResponseDto.getProduct().getDemandDeposit().stream()
                        .filter(s -> Integer.parseInt(s.getEndDate().substring(0, 4)) >= LocalDate.now().getYear())
                        .sorted(Comparator.comparing(InquireDemandDepositResponseDto::getAge))
                        .sorted(Comparator.comparing(InquireDemandDepositResponseDto::getStartDate))
                        .toList()
        );


//                roadmapGraphResponseDto.getProduct().getDemandDeposit().stream()

//                .toList();

        //예금 += 예금 이자
        List<InquireDepositProductsResponseDto> depositProductsList = new ArrayList<>(
                roadmapGraphResponseDto.getProduct().getDeposit().stream()
                        .filter(s -> Integer.parseInt(s.getEndDate().substring(0, 4)) >= LocalDate.now().getYear())
                        .sorted(Comparator.comparing(InquireDepositProductsResponseDto::getAge))
                        .sorted(Comparator.comparing(InquireDepositProductsResponseDto::getStartDate))
                        .toList()
        );

//                roadmapGraphResponseDto.getProduct().getDeposit().stream()

//                .toList();

        
        //적금 += 적금 이자
        List<InquireSavingProductsResponseDto> savingProductsList = new ArrayList<>(
                roadmapGraphResponseDto.getProduct().getSaving().stream()
                        .filter(s -> Integer.parseInt(s.getEndDate().substring(0, 4)) >= LocalDate.now().getYear())
                        .sorted(Comparator.comparing(InquireSavingProductsResponseDto::getAge))
                        .sorted(Comparator.comparing(InquireSavingProductsResponseDto::getStartDate))
                        .toList()
        );

//        roadmapGraphResponseDto.getProduct().getSaving().stream()

//                .toList();
        
        //대출 -= 대출 상환액
        List<InquireLoanProductResponseDto> loanProductsList = new ArrayList<>(
                roadmapGraphResponseDto.getProduct().getLoan().stream()
                        .filter(s -> Integer.parseInt(s.getEndDate().substring(0, 4)) >= LocalDate.now().getYear())
                        .sorted(Comparator.comparing(InquireLoanProductResponseDto::getAge))
                        .sorted(Comparator.comparing(InquireLoanProductResponseDto::getStartDate))
                        .toList()
        );

//        roadmapGraphResponseDto.getProduct().getLoan().stream()

//                .toList();

        //TODO 예정 상품 계산
        //추천 상품 += 각자 개별 상품
        for(RecommendResponseDto recommend : roadmapGraphResponseDto.getRecommend()){
            switch (recommend.getProductType()){
                case DEPOSIT -> {}
                case SAVING -> {}
                case LOAN -> {}

            }
        }

        List<RecommendResponseDto> recommendList = new ArrayList<>(graphResponseDto.getRecommend());

        //추천 상품 List에 추가
        for(RecommendResponseDto responseDto: recommendList){
            switch (responseDto.getProductType()){
                case DEPOSIT -> {}
                case SAVING -> {}
                case LOAN -> {}
                case DEMAND_DEPOSIT -> {}
            }
        }


        int startYear = LocalDate.now().getYear() + 1;
        int endYear = startYear + 1;

        switch (financeKeyword){
            //1년
            case SMALL_HAPPINESS -> {endYear = startYear+1;}
            //5년
            case MIDDLE_HAPPINESS -> {endYear = startYear+5;}
            //10년
            case BIG_HAPPINESS -> {endYear = startYear+10;}
        }

        for(int year=startYear; year<=endYear;year++){

            final int finalYear = year;
            int age = user.getAge() + (year - startYear) + 1;

            long totalAssetValue = 0;
            long depositAssetValue = 0;
            long savingAssetValue = 0;
            long loanAssetValue = 0;

            // 수시 입출금 += 연봉 - 대출 이자 1년치
            //연봉 추가
            totalAssetValue += user.getSalary() * 12;

            //현재 연도에 해당하는 것들만 추가
            //예금 += 예금 이자
            for(InquireDepositProductsResponseDto inquireDepositProductsResponseDto:
                    depositProductsList.stream()
                            .filter(s->Integer.parseInt(s.getStartDate().substring(0,4)) <= finalYear && finalYear <= Integer.parseInt(s.getEndDate().substring(0,4)) )
                            .toList()
            ){

                //예금 이자 조회
                long depositAsset = inquireDepositProductsResponseDto.getBalance();
                depositAsset += (long) (depositAsset*(inquireDepositProductsResponseDto.getInterestRate()/100));

                depositAssetValue += depositAsset;

                inquireDepositProductsResponseDto.setBalance(depositAsset);
            }

            //적금
            for(InquireSavingProductsResponseDto inquireSavingProductsResponseDto:
                    savingProductsList.stream()
                            .filter(s->Integer.parseInt(s.getStartDate().substring(0,4)) <= finalYear && finalYear <= Integer.parseInt(s.getEndDate().substring(0,4)) )
                            .toList()
            ){
                long savingAsset = inquireSavingProductsResponseDto.getBalance();

                Long totalAmount = 0L;
                for (int i = 0; i < 12; i++) {
                    // 이자 계산: 매달 납입한 금액에 대해 이자가 붙음
                    totalAmount += savingAsset/12;
                    totalAmount += (long)(totalAmount * (inquireSavingProductsResponseDto.getInterestRate()/100 / 12));
                }

                savingAssetValue += totalAmount;
                inquireSavingProductsResponseDto.setBalance(totalAmount);
            }

            //대출 상환액 제거
            //대출은 시작해에만 자산에 추가
            for(InquireLoanProductResponseDto inquireLoanProductResponseDto:
                    loanProductsList.stream()
                            .filter(s->Integer.parseInt(s.getStartDate().substring(0,4)) <= finalYear && finalYear <= Integer.parseInt(s.getEndDate().substring(0,4)) )
                            .toList()
            ){

                // 현재 대출을 받은 연도인 경우
                if(Integer.parseInt(inquireLoanProductResponseDto.getStartDate().substring(0,4)) == finalYear){
                    totalAssetValue += inquireLoanProductResponseDto.getBalance();
                }

                //원금 이자 상환
                int month = Integer.parseInt(inquireLoanProductResponseDto.getEndDate().substring(0,4)) - Integer.parseInt(inquireLoanProductResponseDto.getStartDate().substring(0,4));
                long loanAsset = inquireLoanProductResponseDto.getBalance();
                long interest = (long) (loanAsset*inquireLoanProductResponseDto.getInterestRate()/100);
                
                //원금 상환
                loanAssetValue -= loanAsset/month;
                
                //원금 + 이자 상환
                totalAssetValue -= loanAsset/month + interest/month;

                inquireLoanProductResponseDto.setBalance(loanAssetValue);
            }

            if(preRecommend!=null){
                //담기 상품
                for(PreRecommendDto preRecommendDto:
                        preRecommend.stream()
                                .filter(s->Integer.parseInt(s.getStartDate().substring(0,4)) <= finalYear && finalYear <= Integer.parseInt(s.getEndDate().substring(0,4)) )
                                .toList()
                ){
                    switch (preRecommendDto.getAccountTypeCode()){
                        //예금
                        case "2" -> {
                            //예금 이자 조회
                            long depositAsset = preRecommendDto.getBalance();
                            depositAsset += (long) (depositAsset*(preRecommendDto.getInterest()/100));

                            depositAssetValue += depositAsset;

                            preRecommendDto.setBalance(depositAsset);
                        }
                        //적금
                        case "3"->{
                            long savingAsset = preRecommendDto.getBalance();

                            Long totalAmount = 0L;
                            for (int i = 0; i < 12; i++) {
                                // 이자 계산: 매달 납입한 금액에 대해 이자가 붙음
                                totalAmount += savingAsset/12;
                                totalAmount += (long)(totalAmount * (preRecommendDto.getInterest()/100 / 12));
                            }

                            savingAssetValue += totalAmount;
                            preRecommendDto.setBalance(savingAsset);
                        }
                        //대출
                        case "4" -> {
                            // 현재 대출을 받은 연도인 경우
                            if(Integer.parseInt(preRecommendDto.getStartDate().substring(0,4)) == finalYear){
                                totalAssetValue += preRecommendDto.getBalance();
                            }

                            //원금 이자 상환
                            int month = Integer.parseInt(preRecommendDto.getEndDate().substring(0,4)) - Integer.parseInt(preRecommendDto.getStartDate().substring(0,4));
                            long loanAsset = preRecommendDto.getBalance();
                            long interest = (long) (loanAsset*preRecommendDto.getInterest()/100);

                            //원금 상환
                            loanAssetValue -= loanAsset/month;

                            //원금 + 이자 상환
                            totalAssetValue -= loanAsset/month + interest/month;

                            preRecommendDto.setBalance(loanAssetValue);
                        }

                    }
                }
            }
            
            //추천 상품
            for (RecommendResponseDto recommendResponseDto:
                    recommendList.stream()
                            .filter(s->s.getStartDate().getYear() <= finalYear && finalYear <= s.getEndDate().getYear())
                            .toList()
            ){
                switch (recommendResponseDto.getProductType()){
                    //예금
                    case DEPOSIT -> {
                        //예금 이자 조회
                        long depositAsset = recommendResponseDto.getBalance();
                        depositAsset += (long) (depositAsset*(recommendResponseDto.getProductInfo().getInterest()/100));

                        depositAssetValue += depositAsset;

                        recommendResponseDto.setBalance(depositAsset);
                    }
                    //적금
                    case SAVING->{
                        long savingAsset = recommendResponseDto.getBalance();

                        Long totalAmount = 0L;
                        for (int i = 0; i < 12; i++) {
                            // 이자 계산: 매달 납입한 금액에 대해 이자가 붙음
                            totalAmount += savingAsset/12;
                            totalAmount += (long)(totalAmount * (recommendResponseDto.getProductInfo().getInterest()/100 / 12));
                        }

                        savingAssetValue += totalAmount;
                        recommendResponseDto.setBalance(savingAsset);
                    }
                    //대출
                    case LOAN -> {
                        // 현재 대출을 받은 연도인 경우
                        if(recommendResponseDto.getStartDate().getYear() == finalYear){
                            totalAssetValue += recommendResponseDto.getBalance();
                        }

                        //원금 이자 상환
                        int month = recommendResponseDto.getEndDate().getYear() - recommendResponseDto.getStartDate().getYear();
                        long loanAsset = recommendResponseDto.getBalance();
                        long interest = (long) (loanAsset*recommendResponseDto.getProductInfo().getInterest()/100);

                        //원금 상환
                        loanAssetValue -= loanAsset/month;

                        //원금 + 이자 상환
                        totalAssetValue -= loanAsset/month + interest/month;

                        recommendResponseDto.setBalance(loanAssetValue);
                    }

                }
            }

            RoadmapGraphData roadmapGraphData = new RoadmapGraphData(
                    age,
                    user.getCurrentJourneyType(),
                    totalAssetValue,
                    depositAssetValue,
                    savingAssetValue,
                    loanAssetValue
            );

            roadmapGraphResponseDto.getData().add(roadmapGraphData);
        }

        return roadmapGraphResponseDto;
    }


    public ChatbotFeedbackResponseDto feedback(RoadmapGraphResponseDto roadmapGraphResponseDto, String prompt){
        return ChatbotFeedbackResponseDto.builder()
                .feedback(restTemplateUtil.ChatbotFeedback(roadmapGraphResponseDto, prompt))
                .build();
    }
}
