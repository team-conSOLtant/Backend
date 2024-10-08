package com.consoltant.consoltant.domain.user.service;

import com.consoltant.consoltant.domain.course.entity.Course;
import com.consoltant.consoltant.domain.course.service.CourseModuleService;
import com.consoltant.consoltant.domain.journey.service.JourneyModuleService;
import com.consoltant.consoltant.domain.portfolio.service.PortfolioModuleService;
import com.consoltant.consoltant.domain.subject.entity.Subject;
import com.consoltant.consoltant.domain.subject.service.SubjectModuleService;
import com.consoltant.consoltant.domain.university.entity.University;
import com.consoltant.consoltant.domain.university.repository.UniversityRepository;
import com.consoltant.consoltant.domain.user.dto.CreateAccountResponseDto;
import com.consoltant.consoltant.domain.user.dto.CreateUserAcademyRequestDto;
import com.consoltant.consoltant.domain.user.dto.CreateUserAccountRequestDto;
import com.consoltant.consoltant.domain.user.dto.UpdateUserRequestDto;
import com.consoltant.consoltant.domain.user.dto.UserResponseDto;
import com.consoltant.consoltant.domain.user.entity.User;
import com.consoltant.consoltant.domain.user.mapper.UserMapper;
import com.consoltant.consoltant.domain.user.repository.UserModuleRepository;
import com.consoltant.consoltant.domain.user.repository.UserRepository;
import com.consoltant.consoltant.global.exception.BadRequestException;
import com.consoltant.consoltant.util.api.RestTemplateUtil;
import com.consoltant.consoltant.util.constant.JourneyType;
import com.consoltant.consoltant.util.csv.CsvParserUtil;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@Service
@Slf4j
public class UserService{
    private final UserRepository userRepository;
    private final UserModuleRepository userModuleRepository;
    private final UniversityRepository universityRepository;
    private final UserMapper userMapper;
    private final RestTemplateUtil restTemplateUtil;
    private final CourseModuleService courseModuleService;
    private final SubjectModuleService subjectModuleService;
    private final PortfolioModuleService portfolioModuleService;
    private final CsvParserUtil csvParserUtil;
    private final JourneyModuleService journeyModuleService;

    public Long getUserId(String email){
        return userRepository.findByEmail(email).orElseThrow(()->new BadRequestException(("존재하지 않는 사용자입니다."))).getId();
    }

    public String getUserKey(Long userId){
        return userRepository.findById(userId).orElseThrow(()->new BadRequestException(("존재하지 않는 사용자입니다."))).getUserKey();
    }

    public UserResponseDto getUser(Long id)  {
        return userMapper.toUserResponseDto(
                userModuleRepository.findById(id)
                .orElseThrow(()->new BadRequestException("존재하지 않는 사용자입니다."))
        );
    }

    public void updateUser(Long id, UpdateUserRequestDto updateUserRequestDto){
        User user = userRepository.findById(id).orElseThrow();
        user.updateUserInfo(updateUserRequestDto);
    }

    //사용자 학력 정보 업데이트
    @Transactional
    public UserResponseDto createUserAcademy(Long id, CreateUserAcademyRequestDto createUserAcademyRequestDto, MultipartFile subjectFile)
        throws Exception {
        User user = userModuleRepository.findById(id)
            .orElseThrow(() -> new BadRequestException("존재하지 않는 사용자입니다."));

        University university = universityRepository.findByName(createUserAcademyRequestDto.getUniversity());

        if(subjectFile != null){
            List<CSVRecord> records = CsvParserUtil.parseCsv(subjectFile);
            double totalGpa = 0.0;
            double majorGpa = 0.0;
            int totalCredit = 0;
            int passNonPassCredit = 0;
            int majorTotalGpa = 0;

            for (CSVRecord record : records) {
                String subjectNumber = record.get("학수번호");
                String title = record.get("교과목명");
                String courseType = record.get("이수구분");
                int credit = Integer.parseInt(record.get("학점"));
                String grade = record.get("등급");
                double gpa = Double.parseDouble(record.get("평점"));

                boolean isMajor = courseType.startsWith("전");

                // Subject 중복 등록 방지
                Subject subject = subjectModuleService.findBySubjectNumber(subjectNumber)
                        .orElseGet(() -> subjectModuleService.save(Subject.builder()
                                .subjectNumber(subjectNumber)
                                .title(title)
                                .credit(credit)
                                .isMajor(isMajor)
                                .build()));

                // 중복된 Course가 있는지 확인
                if (!courseModuleService.existsByUserAndSubject(user, subject)) {
                    // Course 저장
                    courseModuleService.save(Course.builder()
                            .user(user)
                            .subject(subject)
                            .grade(grade)
                            .subjectName(title)
                            .build());

                    // 학점 및 GPA 계산
                    if ("P".equals(grade)) {
                        passNonPassCredit += credit;
                        continue;
                    }
                    totalGpa += gpa * credit;
                    totalCredit += credit;
                    if (isMajor) {
                        majorTotalGpa += credit;
                        majorGpa += gpa * credit;
                    }
                }
            }

            double calculatedTotalGpa = totalCredit != 0 ? Math.round((totalGpa / totalCredit) * 100.0) / 100.0 : 0.0;
            double calculatedMajorGpa = majorTotalGpa != 0 ? Math.round((majorGpa / majorTotalGpa) * 100.0) / 100.0 : 0.0;
            totalCredit+=passNonPassCredit; //패논패 학점은 나중에 더해주기

            user.addAcademyInfo(university, createUserAcademyRequestDto, calculatedTotalGpa, calculatedMajorGpa, totalCredit);

            portfolioModuleService.findByUserId(user.getId()).orElseThrow().setGpa(calculatedTotalGpa,calculatedMajorGpa); //포폴에도 학점 동기화
        }
        else{
            user.addAcademyInfo(university, createUserAcademyRequestDto, 0.0, 0.0, 0);
            portfolioModuleService.findByUserId(user.getId()).orElseThrow().setGpa(0.0,0.0);
        }

        //User
        switch (createUserAcademyRequestDto.getStatus()){
            case "1"->{user.changeJourney(JourneyType.FRESHMAN);}
            case "2"->{user.changeJourney(JourneyType.SOPHOMORE);}
            case "3"->{user.changeJourney(JourneyType.JUNIOR);}
            case "4"->{user.changeJourney(JourneyType.SENIOR);}
            case "5"->{
                Integer age = user.getAge();
                if(age<40){
                    user.changeJourney(JourneyType.THIRTIES);
                }
                else if(age<50){
                    user.changeJourney(JourneyType.FORTIES);
                }
                else if(age<60){
                    user.changeJourney(JourneyType.FIFTIES);
                }
                else{
                    user.changeJourney(JourneyType.RETIRED);
                }
            }
        }

        // User Jounrney 추가

        journeyModuleService.defaultSave(user);

        return userMapper.toUserResponseDto(user);
    }

    //사용자 계좌 정보 추가
    @Transactional
    public CreateAccountResponseDto createAccount(Long id, String accountTypeUniqueNo){
        User entity = userModuleRepository.findById(id)
                .orElseThrow(()->new BadRequestException("존재하지 않는 사용자입니다.")) ;

        String userKey = entity.getUserKey();
        
        //계좌 정보는 인증 후에 저장
        return userMapper.toCreateAccountResponseDto(restTemplateUtil.createDemandDepositAccount(userKey, accountTypeUniqueNo));
    }

    //사용자 계좌 정보 업데이트
    @Transactional
    public UserResponseDto createUserAccount(Long id, CreateUserAccountRequestDto createUserAccountRequestDto){
        User entity = userModuleRepository.findById(id)
                .orElseThrow(()->new BadRequestException("존재하지 않는 사용자입니다.")) ;

        entity.addAccountInfo(createUserAccountRequestDto);
        User saveEntity = userModuleRepository.save(entity);

        return userMapper.toUserResponseDto(saveEntity);
    }

    //사용자 제거
    public Long deleteUser(Long id){
        userModuleRepository.deleteById(id);

        return id;
    }

    //학력 정보 확인
    public Boolean checkAcademy(Long id){
        User user = userRepository.findById(id).orElseThrow(()->new BadRequestException("존재하지 않는 사용자입니다."));

        return user.getUniversity() != null;
    }

    //계좌 정보 확인
    public Boolean checkAccount(Long id){
        User user = userRepository.findById(id).orElseThrow(()->new BadRequestException("존재하지 않는 사용자입니다."));

        return user.getAccountNo() != null;
    }

    public List<UserResponseDto> findAllByEmail(String email) {
        return userRepository.findByEmailLike(email).stream()
            .map(userMapper::toUserResponseDto)
            .toList();
    }

    public Boolean isCompanyUser(Long id){
        return userRepository.isRoleCompany(id);
    }
}
