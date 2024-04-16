package com.ecommerce.tredence.api.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

    @NotBlank(message = "Product Id cannot be blank")
    private String productId;

    @NotBlank(message = "Category cannot be blank")
    private String category;

    @NotBlank(message = "Brand cannot be blank")
    private String brand;
}
