package com.ecommerce.tredence.api.controller;

import com.ecommerce.tredence.api.dto.ProductDTO;
import com.ecommerce.tredence.api.dto.ShopperProductDTO;
import com.ecommerce.tredence.api.service.ProductService;
import com.ecommerce.tredence.api.service.ShopperProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/{shopperId}/product")
public class ShopperProductController {

    private final ShopperProductService shopperProductService;
    private final ProductService productService;

    @Autowired
    public ShopperProductController(ShopperProductService shopperProductService, ProductService productService) {
        this.shopperProductService = shopperProductService;
        this.productService = productService;
    }
    @PostMapping
    public ResponseEntity<Void> addShopperProductList(@PathVariable String shopperId,
                                                      @Valid @RequestBody ShopperProductDTO shopperProductDTO) {
        shopperProductService.addShopperProductList(shopperId, shopperProductDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @GetMapping
    public ResponseEntity<List<ProductDTO>> getProductsByShopper(@PathVariable String shopperId,
                                                                 @RequestParam(required = false) String category,
                                                                 @RequestParam(required = false) String brand,
                                                                 @RequestParam(defaultValue = "10") Integer limit) {
        List<ProductDTO> products = productService.getProductsByShopper(shopperId, category, brand, limit);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

}
