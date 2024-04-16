package com.ecommerce.tredence.api.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShopperDTO {
    @NotBlank(message = "Shopper Id cannot be blank")
    private String shopperId;
}
