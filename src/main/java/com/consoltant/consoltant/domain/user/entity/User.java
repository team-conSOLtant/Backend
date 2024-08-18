package com.consoltant.consoltant.domain.user.entity;

import com.consoltant.consoltant.domain.university.entity.University;
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
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SQLDelete(sql = "UPDATE user SET is_deleted = true WHERE user_id = ?")
@SQLRestriction("is_deleted = false")
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "university_id", nullable = false)
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

    @Column(nullable = false, length = 100)
    private String major;

    private Double totalGpa;

    private Double majorGpa;

    private Integer totalSumGpa;

    @Column(nullable = false)
    private Boolean isEmployed;

    @Column(length = 100)
    private String accountNo;

    private Integer salary;

    @Enumerated(EnumType.STRING)
    private JourneyType currentJourneyType; // Custom enum for 여정중 현재인 것 조회용

    @Column(nullable = false)
    private Boolean isDeleted;

}