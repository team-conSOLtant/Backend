package com.consoltant.consoltant.domain.portfolio.entity;

import com.consoltant.consoltant.domain.user.entity.User;
import com.consoltant.consoltant.util.constant.FinanceKeyword;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
@SQLDelete(sql = "UPDATE portfolio SET is_deleted = true WHERE portfolio_id = ?")
@SQLRestriction("is_deleted = false")
@Table(name = "portfolio")
public class Portfolio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private Double totalGpa;

    private Double majorGpa;

    @Enumerated(EnumType.STRING)
    private FinanceKeyword financeKeyword; //소,중,대 확행

    @Column(length = 100)
    private String myKeyword;

    @Column(length = 100)
    private String job;

    @Column(length = 1000)
    private String imageUrl;

    @Column(length = 100)
    private String description;

    @Column(length = 100)
    private String backgroundColor;

    @Column(nullable = false)
    private Boolean isDeleted;

}
