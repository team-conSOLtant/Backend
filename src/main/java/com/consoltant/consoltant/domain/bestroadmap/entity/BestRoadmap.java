package com.consoltant.consoltant.domain.bestroadmap.entity;

import com.consoltant.consoltant.domain.portfolio.entity.Portfolio;
import com.consoltant.consoltant.domain.user.entity.User;
import com.consoltant.consoltant.util.constant.FinanceKeyword;
import com.consoltant.consoltant.util.constant.JourneyType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@SQLDelete(sql = "UPDATE award SET is_deleted = true WHERE id = ?")
@SQLRestriction("is_deleted = false")
@Table(name = "best_roadmap")
public class BestRoadmap {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private Long salary;

    @Column(nullable = false)
    private Long startAsset;

    @Column(nullable = false)
    private FinanceKeyword financeKeyword;

    @Column(nullable = false)
    private JourneyType journeyType;

}
