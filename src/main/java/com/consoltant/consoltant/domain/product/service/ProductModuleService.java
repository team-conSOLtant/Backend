package com.consoltant.consoltant.domain.product.service;

import com.consoltant.consoltant.domain.product.entity.Product;
import com.consoltant.consoltant.domain.product.repository.ProductRepository;
import com.consoltant.consoltant.util.constant.ProductType;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductModuleService {
    private final ProductRepository productRepository;

    // Product ID로 단일 조회
    public Product findById(Long id) {
        return productRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Invalid Product ID"));
    }

    // 사용자 ID로 모든 금융상품 조회
    public List<Product> findAllByUserId(Long userId) {
        return productRepository.findAllByUserId(userId);
    }

    // 사용자 여정에 해당하는 모든 금융상품 조회
    public List<Product> findProductsByUserIdAndJourneyStartDate(Long userId, LocalDate journeyStartDate) {
        return productRepository.findProductsByUserIdAndJourneyStartDate(userId, journeyStartDate);
    }

    // 금융 상품 타입에 따른 유저의 상품 목록 조회
    public List<Product> findProductsByUserIdAndProductType(Long userId, ProductType productType) {
        return productRepository.findProductsByUserIdAndProductType(userId,productType);
    }


    // Product 저장
    public Product save(Product product) {
        return productRepository.save(product);
    }

    // Product 삭제
    public void delete(Long id) {
        productRepository.deleteById(id);
    }
}
