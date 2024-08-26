package com.consoltant.consoltant.domain.journey.entity;

import com.consoltant.consoltant.domain.journey.dto.JourneyRequestDto;
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
@SQLDelete(sql = "UPDATE journey SET is_deleted = true WHERE id = ?")
@SQLRestriction("is_deleted = false")
@Table(name = "journey")
public class Journey {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private JourneyType journeyType;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ProductType productType;

    private LocalDate startDate;

    private LocalDate endDate;

    @Column(nullable = false)
    private Long balance;

    @Column(nullable = false)
    private int age;

    @Column(nullable = false)
    @Builder.Default
    private Boolean isDeleted = false;

    public void setUser(User user){
        this.user = user;
    }

    public void update(JourneyRequestDto journeyRequestDto){
        this.journeyType = journeyRequestDto.getJourneyType();
        this.balance = journeyRequestDto.getBalance();
        this.startDate = journeyRequestDto.getStartDate();
        this.endDate = journeyRequestDto.getEndDate();
    }

}
