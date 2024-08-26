package com.consoltant.consoltant.domain.product.entity;

import com.consoltant.consoltant.domain.product.dto.ProductRequestDto;
import com.consoltant.consoltant.domain.user.entity.User;
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
@SQLDelete(sql = "UPDATE product SET is_deleted = true WHERE id = ?")
@SQLRestriction("is_deleted = false")
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ProductType productType; // 수시입출금, 예금, 적금, 대출

    @Column(nullable = false, length = 100)
    private String accountNo;

    @Column(nullable = false)
    private String color;

    @Column(nullable = false)
    private LocalDate startDate;

    @Column(nullable = false)
    private LocalDate endDate;

    @Column(nullable = false)
    @Builder.Default
    private Boolean isDeleted = false;

    public void setUser(User user){
        this.user = user;
    }

    public void update(ProductRequestDto productRequestDto){

    }

}
