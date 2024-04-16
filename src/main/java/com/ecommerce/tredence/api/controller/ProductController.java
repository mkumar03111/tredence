package com.ecommerce.tredence.api.controller;

import com.ecommerce.tredence.api.dto.ProductDTO;
import com.ecommerce.tredence.api.dto.ProductResponse;
import com.ecommerce.tredence.api.exception.ProductException;
import com.ecommerce.tredence.api.model.Product;
import com.ecommerce.tredence.api.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<List<ProductResponse>> addProductList(@Valid @RequestBody List<ProductDTO> productDTOList) {
        List<ProductResponse> response = Optional.ofNullable(productDTOList)
                .filter(list -> !list.isEmpty())
                .flatMap(productService::addProductList)
                .orElseThrow(() -> new ProductException("Product list is empty", HttpStatus.BAD_REQUEST));
        return ResponseEntity.ok().body(response);
    }


    @GetMapping
    public ResponseEntity<List<ProductResponse>> getProductList() {
        List<ProductResponse> response = productService.getProductList()
                .orElseThrow(() -> new ProductException("Failed to retrieve product list", HttpStatus.INTERNAL_SERVER_ERROR));
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable String productId) {
        ProductResponse response = productService.getProductById(productId)
                .orElseThrow(() -> new ProductException("Failed to retrieve product by product id", HttpStatus.NOT_FOUND));
        return ResponseEntity.ok().body(response);
    }
}
