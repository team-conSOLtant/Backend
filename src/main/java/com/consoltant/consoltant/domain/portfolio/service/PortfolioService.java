package com.consoltant.consoltant.domain.portfolio.service;

import com.consoltant.consoltant.domain.activity.dto.ActivityRequestDto;
import com.consoltant.consoltant.domain.activity.entity.Activity;
import com.consoltant.consoltant.domain.activity.mapper.ActivityMapper;
import com.consoltant.consoltant.domain.activity.service.ActivityModuleService;
import com.consoltant.consoltant.domain.award.dto.AwardRequestDto;
import com.consoltant.consoltant.domain.award.entity.Award;
import com.consoltant.consoltant.domain.award.mapper.AwardMapper;
import com.consoltant.consoltant.domain.award.service.AwardModuleService;
import com.consoltant.consoltant.domain.career.dto.CareerRequestDto;
import com.consoltant.consoltant.domain.career.entity.Career;
import com.consoltant.consoltant.domain.career.mapper.CareerMapper;
import com.consoltant.consoltant.domain.career.service.CareerModuleService;
import com.consoltant.consoltant.domain.certification.dto.CertificationRequestDto;
import com.consoltant.consoltant.domain.certification.entity.Certification;
import com.consoltant.consoltant.domain.certification.mapper.CertificationMapper;
import com.consoltant.consoltant.domain.certification.service.CertificationModuleService;
import com.consoltant.consoltant.domain.course.dto.CourseRequestDto;
import com.consoltant.consoltant.domain.course.entity.Course;
import com.consoltant.consoltant.domain.course.mapper.CourseMapper;
import com.consoltant.consoltant.domain.course.service.CourseModuleService;
import com.consoltant.consoltant.domain.matching.entity.Matching;
import com.consoltant.consoltant.domain.matching.service.MatchingModuleService;
import com.consoltant.consoltant.domain.notification.entity.Notification;
import com.consoltant.consoltant.domain.notification.service.NotificationModuleService;
import com.consoltant.consoltant.domain.portfolio.dto.PortfolioRequestDto;
import com.consoltant.consoltant.domain.portfolio.dto.PortfolioResponseDto;
import com.consoltant.consoltant.domain.portfolio.dto.PortfolioSaveAllRequestDto;
import com.consoltant.consoltant.domain.portfolio.entity.Portfolio;
import com.consoltant.consoltant.domain.portfolio.entity.PortfolioDocument;
import com.consoltant.consoltant.domain.portfolio.mapper.PortfolioMapper;
import com.consoltant.consoltant.domain.portfolio.repository.PortfolioElasticRepository;
import com.consoltant.consoltant.domain.portfolio.repository.PortfolioRepository;
import com.consoltant.consoltant.domain.project.dto.ProjectRequestDto;
import com.consoltant.consoltant.domain.project.entity.Project;
import com.consoltant.consoltant.domain.project.mapper.ProjectMapper;
import com.consoltant.consoltant.domain.project.service.ProjectModuleService;
import com.consoltant.consoltant.domain.projectuser.dto.ProjectUserRequestDto;
import com.consoltant.consoltant.domain.projectuser.entity.ProjectUser;
import com.consoltant.consoltant.domain.projectuser.mapper.ProjectUserMapper;
import com.consoltant.consoltant.domain.projectuser.service.ProjectUserModuleService;
import com.consoltant.consoltant.domain.projectuser.service.ProjectUserService;
import com.consoltant.consoltant.domain.user.entity.User;
import com.consoltant.consoltant.domain.user.repository.UserRepository;
import com.consoltant.consoltant.global.exception.BadRequestException;
import com.consoltant.consoltant.util.constant.NotificationType;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class PortfolioService {

    private final PortfolioModuleService portfolioModuleService;
    private final MatchingModuleService matchingModuleService;
    private final CourseModuleService courseModuleService;
    private final NotificationModuleService notificationModuleService;
    private final ActivityModuleService activityModuleService;
    private final AwardModuleService awardModuleService;
    private final CertificationModuleService certificationModuleService;
    private final CareerModuleService careerModuleService;
    private final ProjectModuleService projectModuleService;
    private final ProjectUserService projectUserService;
    private final ProjectUserModuleService projectUserModuleService;

    private final PortfolioMapper portfolioMapper;
    private final ActivityMapper activityMapper;
    private final AwardMapper awardMapper;
    private final CertificationMapper certificationMapper;
    private final CareerMapper careerMapper;
    private final ProjectMapper projectMapper;
    private final ProjectUserMapper projectUserMapper;
    private final CourseMapper courseMapper;

    private final PortfolioElasticRepository portfolioElasticRepository;

    private final UserRepository userRepository;

    public PortfolioResponseDto findById(Long id) {
        return portfolioMapper.toPortfolioResponseDto(portfolioModuleService.findById(id));
    }

    public PortfolioResponseDto findByUserId(Long userId) {
        Portfolio portfolio = portfolioModuleService.findByUserId(userId).orElse(null);
        return portfolio==null ? null : portfolioMapper.toPortfolioResponseDto(portfolio);
    }

    public PortfolioResponseDto save(PortfolioRequestDto portfolioRequestDto) {
        Portfolio portfolio
                = portfolioMapper.toPortfolio(portfolioRequestDto);
        User user = userRepository.findById(portfolioRequestDto.getUserId()).orElseThrow();
        portfolio.setUser(user);
        return portfolioMapper.toPortfolioResponseDto(portfolioModuleService.save(portfolio));
    }

    public PortfolioResponseDto update(Long id, PortfolioRequestDto portfolioRequestDto){
        Portfolio portfolio = portfolioModuleService.findById(id);
        portfolio.update(portfolioRequestDto);
        portfolioModuleService.save(portfolio);
        return portfolioMapper.toPortfolioResponseDto(portfolio);
    }

    //포트폴리오 전체 저장
    @Transactional
    public void saveAll(PortfolioSaveAllRequestDto portfolioSaveAllRequestDto){
        Long portfolioId = null;
        Long userId = portfolioSaveAllRequestDto.getUserId();
        if(portfolioSaveAllRequestDto.getPortfolioId() == null){
            //새로 만드는 경우 최초 저장일 때
            if(portfolioModuleService.findByUserId(userId).isEmpty()) {
                Portfolio portfolio = new Portfolio();
                portfolio.setUser(userRepository.findById(userId).orElseThrow());
                portfolioId = portfolioModuleService.save(portfolio).getId();
            }else {
                //새로 만들지만 최초 저장 아닐 때
                portfolioId = portfolioModuleService.findByUserId(userId).orElseThrow().getId();
            }
        }
        else{
            portfolioId = portfolioSaveAllRequestDto.getPortfolioId();
        }
        Portfolio portfolio = portfolioModuleService.findById(portfolioId);
        update(portfolioId, portfolioSaveAllRequestDto.getPortfolioRequestDto());

        //활동 내역 저장
        for (ActivityRequestDto activityRequestDto : portfolioSaveAllRequestDto.getActivities()) {
            if(activityRequestDto.getActivityId() == null){
                Activity activity = activityMapper.toActivity(activityRequestDto);
                activity.setPortfolio(portfolio);
                activityModuleService.save(activity);
                continue;
            }
            Activity activity = activityModuleService.findById(activityRequestDto.getActivityId());
            activity.update(activityRequestDto);
            activityModuleService.save(activity);
        }

        //수상 내역 저장
        for (AwardRequestDto awardRequestDto : portfolioSaveAllRequestDto.getAwards()) {
            System.out.println(awardRequestDto.toString());
            if(awardRequestDto.getAwardId() == null){
                Award award = awardMapper.toAward(awardRequestDto);
                award.setPortfolio(portfolio);
                awardModuleService.save(award);
                continue;
            }
            Award award = awardModuleService.findById(awardRequestDto.getAwardId());
            award.update(awardRequestDto);
            awardModuleService.save(award);
        }

        //자격증 내역 저장
        for (CertificationRequestDto certificationRequestDto : portfolioSaveAllRequestDto.getCertifications()) {
            System.out.println(certificationRequestDto.toString());
            if(certificationRequestDto.getCertificationId() == null){
                Certification certification = certificationMapper.toCertification(certificationRequestDto);
                certification.setPortfolio(portfolio);
                certificationModuleService.save(certification);
                continue;
            }
            Certification certification = certificationModuleService.findById(certificationRequestDto.getCertificationId());
            certification.update(certificationRequestDto);
            certificationModuleService.save(certification);
        }
        //경력 내역 저장
        for (CareerRequestDto careerRequestDto : portfolioSaveAllRequestDto.getCareers()) {
            System.out.println(careerRequestDto.toString());
            if(careerRequestDto.getCareerId() == null){
                Career career = careerMapper.toCareer(careerRequestDto);
                career.setPortfolio(portfolio);
                careerModuleService.save(career);
                continue;
            }
            Career career = careerModuleService.findById(careerRequestDto.getCareerId());
            career.update(careerRequestDto);
            careerModuleService.save(career);
        }
        // 프로젝트 내역 저장
        for (ProjectRequestDto projectRequestDto : portfolioSaveAllRequestDto.getProjects()) {
            System.out.println(projectRequestDto.toString());
            if(projectRequestDto.getProjectId() == null){
                Project project = projectMapper.toProject(projectRequestDto);
                project.setPortfolio(portfolio);
                Project savedProject = projectModuleService.save(project);
                Long generatedKey = savedProject.getId();
                for(ProjectUserRequestDto projectUserRequestDto : projectRequestDto.getProjectUsers()){
                    projectUserRequestDto.setProjectId(generatedKey);
                    projectUserService.save(projectUserRequestDto);
                }
                project.setProjectUsers(projectUserModuleService.findAllByProjectId(project.getId()));
                projectModuleService.save(project);
                continue;
            }
            Project project = projectModuleService.findById(projectRequestDto.getProjectId());
            project.update(projectRequestDto);
            List<ProjectUser> projectUserList = new ArrayList<>();
            projectUserModuleService.deleteAllByProjectId(project.getId());
            for(ProjectUserRequestDto projectUserRequestDto : projectRequestDto.getProjectUsers()) {
                ProjectUser projectUser = projectUserMapper.toProjectUser(projectUserRequestDto);
                projectUser.setProject(project);
                projectUser.setUser(userRepository.findById(projectUserRequestDto.getUserId()).orElseThrow());
                projectUserList.add(projectUser);
            }
            projectUserModuleService.saveAll(projectUserList);
            project.setProjectUsers(projectUserList);
            projectModuleService.save(project);
        }

        // 선택한 수강 과목 내역 저장
        User user = userRepository.findById(userId).orElseThrow();

        // 기존에 선택된 과목들의 선택 상태를 해제
        List<Course> selectedCourse = courseModuleService.findAllByUserIdAndIsSelectedTrue(userId);
        if (selectedCourse != null) {
            for (Course course : selectedCourse) {
                course.setIsSelected(false);
                courseModuleService.save(course);
            }
        }

        // 새로운 선택 과목 처리
        for (CourseRequestDto courseRequestDto : portfolioSaveAllRequestDto.getCourses()) {
            // 이미 존재하는 과목인지 확인
            Optional<Course> existingCourseOpt = courseModuleService.findByUserIdAndSubjectId(
                userId, courseRequestDto.getSubjectId());

            Course course;
            if (existingCourseOpt.isPresent()) {
                // 기존 과목이 존재하면 해당 과목을 가져와서 isSelected를 true로 설정
                course = existingCourseOpt.get();
                course.setIsSelected(true);
            } else {
                // 존재하지 않으면 새로운 과목 생성
                course = courseMapper.toCourse(courseRequestDto);
                course.setUser(user); // User 설정
                course.setIsSelected(true);
            }
            courseModuleService.save(course);
        }

        // es portfolio 수정
        PortfolioDocument portfolioDocument = portfolioElasticRepository.findByPortfolioId(portfolio.getId())
                .orElseThrow(()-> new BadRequestException("존재하지 않는 포트폴리오 도큐먼트 입니다."));

        portfolioDocument.updatePortfolioDocument(portfolioSaveAllRequestDto.getAllContent(), user.getIsEmployed(),
                user.getTotalGpa(), user.getMaxGpa());

        portfolioElasticRepository.save(portfolioDocument);
    }

    public void delete(Long id){
        portfolioModuleService.delete(id);
    }

    @Transactional
    public PortfolioResponseDto getMatchingSeniorPortfolio(Long userId){

        User user = userRepository.findById(userId).orElseThrow();
        Long universityId = user.getUniversity().getId();

        List<Portfolio> seniorPortfolios = portfolioModuleService.findAllByUniversityIdAndIsEmployedTrue(universityId); //취직한 선배들의 포트폴리오 전부 조회
        Set<Long> alreadyMatchingPortfolioIdList = matchingModuleService.findAllByUserId(userId); //이미 매칭됐었던 포트폴리오 아이디 조회
        List<Course> userCourses = courseModuleService.findAllByUserId(userId);  //후배의 수강 리스트

        Portfolio bestPortfolio = null;
        int maxScore = 0;

        for(Portfolio seniorPortfolio : seniorPortfolios){
            //이미 추천한적이 있다면 건너뜀
            if(alreadyMatchingPortfolioIdList.contains(seniorPortfolio.getId())){
                continue;
            }

            int score = 0;
            // 1. 전공이 일치여부 확인 (score 10점 부여)
            if(user.getMajor().equals(seniorPortfolio.getUser().getMajor())){
                score += 10;
            }

            // 2. 수강 과목 겹치는 개수 세기
            List<Course> seniorCourses = courseModuleService.findAllByUserId(seniorPortfolio.getUser().getId());
            long matchingCourseCount = userCourses.stream()
                .filter(userCourse -> seniorCourses.stream()
                    .anyMatch(seniorCourse -> seniorCourse.getSubject().getId().equals(userCourse.getSubject().getId())))
                .count();

            score += matchingCourseCount * 2;   //일치하는 과목 하나당 2점씩

            // 3. 학점 유사도 확인
            double gpaDifference = Math.abs(user.getTotalGpa() - seniorPortfolio.getTotalGpa());
            if (gpaDifference <= 0.5) {
                score += 3;
            } else if (gpaDifference <= 1) {
                score += 1;
            }

            if (score > maxScore) {
                maxScore = score;
                bestPortfolio = seniorPortfolio;
            }
        }

        //새로운 추천이 없을 경우 기존 추천에 있는 것 다시 추천해주기
        if(bestPortfolio == null){
            for(Long id : alreadyMatchingPortfolioIdList){
                return portfolioMapper.toPortfolioResponseDto(portfolioModuleService.findById(id));
            }
        }

        //매칭 기록 저장
        matchingModuleService.save(Matching.builder()
            .user(user)
            .portfolio(bestPortfolio)
            .build());

        //알림 저장
        notificationModuleService.save(Notification.builder()
            .user(user)
            .notificationType(NotificationType.PORTFOLIO_MATCHING)
            .content(bestPortfolio.getUser().getCorporateName() + "," + bestPortfolio.getId())
            .build());

        return portfolioMapper.toPortfolioResponseDto(bestPortfolio);
    }

    @Scheduled(cron = "0 0 0 * * ?", zone = "Asia/Seoul")
    public void processUsersAtMidnight() {
        userRepository.findAll().forEach(user -> {
            try {
                getMatchingSeniorPortfolio(user.getId());
            } catch (Exception e) {
                // 예외가 발생하면 해당 사용자 작업스킵 로그 남기기
                log.info("Error getMatchingSeniorPortfolio User ID: " + user.getId());
            }
        });
    }

}