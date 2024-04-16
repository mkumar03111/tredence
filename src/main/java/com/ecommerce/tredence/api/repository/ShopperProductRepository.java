package com.ecommerce.tredence.api.repository;

import com.ecommerce.tredence.api.model.ShopperProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopperProductRepository extends JpaRepository<ShopperProduct, Long> {
}
