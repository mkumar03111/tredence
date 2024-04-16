package com.ecommerce.tredence.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse {
    private Long id;
    private String productId;
    private String category;
    private String brand;

    public ProductResponse(Long id, String message) {
        this.id = id;
        this.productId = message;
        this.category = null;
        this.brand = null;
    }
}
