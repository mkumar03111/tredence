package com.ecommerce.tredence.api.service;

import com.ecommerce.tredence.api.dto.ProductDTO;
import com.ecommerce.tredence.api.dto.ProductResponse;
import com.ecommerce.tredence.api.dto.ShopperDTO;
import com.ecommerce.tredence.api.dto.ShopperResponse;
import com.ecommerce.tredence.api.model.Product;
import com.ecommerce.tredence.api.model.Shopper;
import com.ecommerce.tredence.api.repository.ProductRepository;
import com.ecommerce.tredence.api.repository.ShopperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.DoubleStream;

@Service
public class ShopperService {

    private final ShopperRepository shopperRepository;

    @Autowired
    public ShopperService(ShopperRepository shopperRepository) {
        this.shopperRepository = shopperRepository;
    }

    public Optional<ShopperResponse> addShopper(ShopperDTO shopperDTO) {
        return Optional.of(shopperDTO)
                .map(dto -> {
                    Shopper shopper = new Shopper();
                    shopper.setShopperId(dto.getShopperId());
                    return shopper;
                })
                .map(shopperRepository::save)
                .map(result -> new ShopperResponse(result.getId(), result.getShopperId()));
    }
}
