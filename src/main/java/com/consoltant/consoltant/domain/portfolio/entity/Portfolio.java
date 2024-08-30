package com.consoltant.consoltant.domain.portfolio.entity;

import com.consoltant.consoltant.domain.portfolio.dto.PortfolioRequestDto;
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
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@SQLDelete(sql = "UPDATE portfolio SET is_deleted = true WHERE id = ?")
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

    private String email;

    @Column(length = 1000)
    private String imageUrl;

    @Column(length = 100)
    private String description;

    @Column(length = 100)
    private String backgroundColor;

    @Column(nullable = false)
    @Builder.Default
    private Boolean isDeleted = false;

    public void setUser(User user){
        this.user = user;
    }

    public void update(PortfolioRequestDto portfolioRequestDto){
        this.totalGpa = portfolioRequestDto.getTotalGpa();
        this.majorGpa = portfolioRequestDto.getMajorGpa();
        this.email = portfolioRequestDto.getEmail();
        this.financeKeyword = portfolioRequestDto.getFinanceKeyword();
        this.myKeyword = portfolioRequestDto.getMyKeyword();
        this.job = portfolioRequestDto.getJob();
        this.imageUrl = portfolioRequestDto.getImageUrl();
        this.description = portfolioRequestDto.getDescription();
        this.backgroundColor = portfolioRequestDto.getBackgroundColor();
    }

    public void setGpa(Double totalGpa, Double majorGpa){
        this.totalGpa = totalGpa;
        this.majorGpa = majorGpa;
    }

    public void setImageUrl(String imageUrl){
        this.imageUrl = imageUrl;
    }
}
