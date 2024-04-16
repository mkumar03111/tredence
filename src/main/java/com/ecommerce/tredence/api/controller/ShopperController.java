package com.ecommerce.tredence.api.controller;

import com.ecommerce.tredence.api.dto.*;
import com.ecommerce.tredence.api.exception.ShopperException;
import com.ecommerce.tredence.api.service.ProductService;
import com.ecommerce.tredence.api.service.ShopperProductService;
import com.ecommerce.tredence.api.service.ShopperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/shopper")
public class ShopperController {

    private final ShopperService shopperService;

    @Autowired
    public ShopperController( ShopperService shopperService) {
        this.shopperService = shopperService;
    }


    @PostMapping
    public ResponseEntity<ShopperResponse> addShopper(@Valid @RequestBody ShopperDTO shopperDTO) {
        return Optional.ofNullable(shopperDTO)
                .map(shopperService::addShopper)
                .orElseThrow(() -> new ShopperException("ShopperDTO is null", HttpStatus.BAD_REQUEST))
                .map(response -> ResponseEntity.ok().body(response))
                .orElseThrow(() -> new ShopperException("Failed to add shopper", HttpStatus.INTERNAL_SERVER_ERROR));
    }
}
