package com.ecommerce.tredence.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShopperProductDTO {

    @NotBlank(message = "Shopper Id cannot be blank")
    private String shopperId;

    @NotEmpty(message = "shelf cannot be empty")
    @NotNull(message = "shelf cannot be empty")
    private List<ShopperShelf> shelf;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ShopperShelf {
        private String productId;
        private Double relevancyScore;
    }

}
