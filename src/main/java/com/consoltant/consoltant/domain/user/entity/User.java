package com.consoltant.consoltant.domain.user.entity;

import com.consoltant.consoltant.domain.university.entity.University;
import com.consoltant.consoltant.domain.user.dto.CreateUserAcademyRequestDto;
import com.consoltant.consoltant.domain.user.dto.CreateUserAccountRequestDto;
import com.consoltant.consoltant.domain.user.dto.CreateUserRequestDto;
import com.consoltant.consoltant.util.constant.JourneyType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@SQLDelete(sql = "UPDATE user SET is_deleted = true WHERE id = ?")
@SQLRestriction("is_deleted = false")
@Table(name = "user")
@ToString
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "university_id")
    private University university;

    @Column(nullable = false, length = 100)
    private String email;

    @Column(nullable = false, length = 255)
    private String password;

    @Column(nullable = false, length = 100)
    private String userKey;

    @Column(nullable = false, length = 100)
    private String name;

    private Integer age;

    @Column(length = 100)
    private String phoneNumber;

    @Column(length = 100)
    private String birthDate;

    @Column(length = 100)
    private String major;

    private Double totalGpa;

    private Double majorGpa;

    private Integer totalSumGpa;

    @Column(nullable = false)
    @Builder.Default
    private Boolean isEmployed = false;

    private String corporateName;

    @Column(length = 100)
    private String accountNo;

    private Integer salary;

    @Enumerated(EnumType.STRING)
    private JourneyType currentJourneyType; // Custom enum for 여정중 현재인 것 조회용

    private String role;

    @Column(nullable = false)
    @Builder.Default
    private Boolean isDeleted = false;

    public User(String username, String password, String role) {
        this.password = password;
        this.email = username;
        this.role = role;
    }

    //사용자 계좌 정보 추가
    public void addAccountInfo(CreateUserAccountRequestDto createUserAccountRequestDto){
        this.accountNo = createUserAccountRequestDto.getAccountNo();
        this.isEmployed = createUserAccountRequestDto.getIsEmployed();
        this.salary = createUserAccountRequestDto.getSalary();
        this.corporateName = createUserAccountRequestDto.getCorporateName();
    }

    public void addAcademyInfo(University university,CreateUserAcademyRequestDto createUserAcademyRequestDto, Double totalGpa, Double majorGpa, Integer totalSumGpa){
        this.university = university;
        this.major = createUserAcademyRequestDto.getMajor();
        this.totalGpa = totalGpa;
        this.majorGpa = majorGpa;
        this.totalSumGpa = totalSumGpa;
    }

    //사용자 키 추가
//    public void addUserKey(String userKeZ y){
//        this.userKey = userKey;
//    }

    //제휴 대학 추가
    public void addUniversity(University university){
        this.university = university;
    }
}