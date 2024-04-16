package com.ecommerce.tredence.api.repository;

import com.ecommerce.tredence.api.model.Shopper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShopperRepository extends JpaRepository<Shopper, Long> {
    Optional<Shopper> findByShopperId(String shopperId);
}
