package com.consoltant.consoltant.domain.career.entity;

import com.consoltant.consoltant.domain.portfolio.entity.Portfolio;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;
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
@SQLDelete(sql = "UPDATE career SET is_deleted = true WHERE id = ?")
@SQLRestriction("is_deleted = false")
@Table(name = "career")
public class Career {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "portfolio_id", nullable = false)
    private Portfolio portfolio;

    @Column(nullable = false, length = 100)
    private String company;

    @Column(nullable = false, length = 100)
    private String positionLevel;

    @Column(nullable = false)
    private LocalDate startDate;

    @Column(nullable = false)
    private LocalDate endDate;

    @Column(nullable = false)
    @Builder.Default
    private Boolean isDeleted = false;

    public void delete(){
        this.isDeleted = true;
    }

    public void setPortfolio(Portfolio portfolio){
        this.portfolio = portfolio;
    }

}