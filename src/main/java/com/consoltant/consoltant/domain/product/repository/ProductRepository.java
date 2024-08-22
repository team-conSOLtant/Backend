package com.consoltant.consoltant.domain.product.repository;

import com.consoltant.consoltant.domain.product.entity.Product;
import com.consoltant.consoltant.util.constant.ProductType;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAllByUserId(Long id);

    @Query("SELECT p FROM Product p WHERE p.user.id = :userId AND p.isDeleted = false " +
        "AND p.startDate <= :journeyStartDate " +
        "AND p.endDate >= :journeyStartDate")
    List<Product> findProductsByUserIdAndJourneyStartDate(@Param("userId") Long userId,
        @Param("journeyStartDate") LocalDate journeyStartDate);

    @Query("SELECT p FROM Product p WHERE p.user.id = :userId AND p.productType = :productType")
    List<Product> findProductsByUserIdAndProductType(
        @Param("userId") Long userId,
        @Param("productType") ProductType productType);
}
