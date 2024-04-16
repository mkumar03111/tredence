package com.ecommerce.tredence.api.repository;

import com.ecommerce.tredence.api.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByShoppersShopperIdAndCategoryAndBrand(String shopperId, String category, String brand);
    List<Product> findByShoppersShopperIdAndCategory(String shopperId, String category);
    List<Product> findByShoppersShopperIdAndBrand(String shopperId, String brand);
    List<Product> findByShoppersShopperId(String shopperId);
    Optional<Product> findByProductId(String productId);

}
