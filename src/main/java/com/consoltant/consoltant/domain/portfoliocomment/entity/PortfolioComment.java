package com.consoltant.consoltant.domain.portfoliocomment.entity;

import com.consoltant.consoltant.domain.portfolio.entity.Portfolio;
import com.consoltant.consoltant.domain.portfoliocomment.dto.PortfolioCommentRequestDto;
import com.consoltant.consoltant.domain.user.entity.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@SQLDelete(sql = "UPDATE portfolio_comment SET is_deleted = true WHERE id = ?")
@SQLRestriction("is_deleted = false")
@Table(name = "portfolio_comment")
public class PortfolioComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "portfolio_id", nullable = false)
    private Portfolio portfolio;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false, length = 1000)
    private String comment;

    @Column(nullable = false)
    @Builder.Default
    private Boolean isDeleted = false;

    public void setUser(User user) {
        this.user = user;
    }

    public void setPortfolio(Portfolio portfolio) {
        this.portfolio = portfolio;
    }

    public void update(PortfolioCommentRequestDto portfolioCommentRequestDto){
        this.comment = portfolioCommentRequestDto.getComment();
    }
}
