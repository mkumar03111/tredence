package com.ecommerce.tredence.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShopperResponse {
    private Long id;
    private String productId;
}
