package com.consoltant.consoltant.domain.recommend.entity;

import com.consoltant.consoltant.domain.user.entity.User;
import com.consoltant.consoltant.util.constant.JourneyType;
import com.consoltant.consoltant.util.constant.ProductType;
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
import java.time.LocalDate;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SQLDelete(sql = "UPDATE recommend SET is_deleted = true WHERE recommend_id = ?")
@SQLRestriction("is_deleted = false")
@Table(name = "recommend")
public class Recommend {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ProductType productType;

    @Column(nullable = false)
    private String accountUniqueTypeNo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private JourneyType journeyType;

    @Column(nullable = false)
    private LocalDate startDate;

    @Column(nullable = false)
    private LocalDate endDate;

    @Column(nullable = false)
    private Boolean isDeleted;

}
