package com.ecommerce.tredence.api.service;

import com.ecommerce.tredence.api.dto.ShopperProductDTO;
import com.ecommerce.tredence.api.model.Product;
import com.ecommerce.tredence.api.model.Shopper;
import com.ecommerce.tredence.api.model.ShopperProduct;
import com.ecommerce.tredence.api.repository.ProductRepository;
import com.ecommerce.tredence.api.repository.ShopperProductRepository;
import com.ecommerce.tredence.api.repository.ShopperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShopperProductService {

    private final ShopperRepository shopperRepository;
    private final ProductRepository productRepository;
    private final ShopperProductRepository shopperProductRepository;

    @Autowired
    public ShopperProductService(ShopperRepository shopperRepository, ProductRepository productRepository, ShopperProductRepository shopperProductRepository) {
        this.shopperRepository = shopperRepository;
        this.productRepository = productRepository;
        this.shopperProductRepository = shopperProductRepository;
    }

    public void addShopperProductList(String shopperId, ShopperProductDTO shopperProductDTO) {
        Shopper shopper = shopperRepository.findByShopperId(shopperId)
                .orElseThrow(() -> new IllegalArgumentException("Shopper not found"));

        if (!shopperProductDTO.getShopperId().equals(shopperId)) {
            throw new IllegalArgumentException("Shopper ID in payload does not match the requested shopper ID.");
        }

        shopperProductDTO.getShelf().forEach(shelf -> {
            Product product = productRepository.findByProductId(shelf.getProductId())
                    .orElseThrow(() -> new IllegalArgumentException("Product not found"));

            ShopperProduct shopperProduct = new ShopperProduct();
            shopperProduct.setShopper(shopper);
            shopperProduct.setProduct(product);
            shopperProduct.setRelevancyScore(shelf.getRelevancyScore());
            shopperProductRepository.save(shopperProduct);
        });
    }
}
