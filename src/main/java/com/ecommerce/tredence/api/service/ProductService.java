package com.ecommerce.tredence.api.service;

import com.ecommerce.tredence.api.dto.ProductDTO;
import com.ecommerce.tredence.api.dto.ProductResponse;
import com.ecommerce.tredence.api.model.Product;
import com.ecommerce.tredence.api.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.StreamSupport;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Optional<List<ProductResponse>> addProductList(List<ProductDTO> productDTOList) {
        List<Product> products = productDTOList.stream()
                .map(this::mapProductDTOToProduct)
                .collect(Collectors.toList());

        Iterable<Product> savedProducts = productRepository.saveAll(products);

        return Optional.of(savedProducts).map(saved -> StreamSupport.stream(saved.spliterator(), false)
                .map(product -> new ProductResponse(product.getId(), product.getProductId(), product.getCategory(), product.getBrand()))
                .collect(Collectors.toList()));
    }

    public Optional<ProductResponse> addProduct(ProductDTO productDTO) {
        return Optional.of(mapProductDTOToProduct(productDTO))
                .map(productRepository::save)
                .map(product -> new ProductResponse(product.getId(), product.getProductId(), product.getCategory(), product.getBrand()));
    }

    private Product mapProductDTOToProduct(ProductDTO productDTO) {
        Product product = new Product();
        product.setProductId(productDTO.getProductId());
        product.setCategory(productDTO.getCategory());
        product.setBrand(productDTO.getBrand());
        return product;
    }

    public List<ProductDTO> getProductsByShopper(String shopperId, String category, String brand, Integer limit) {
        List<Product> products;

        if (category != null && brand != null) {
            products = productRepository.findByShoppersShopperIdAndCategoryAndBrand(shopperId, category, brand);
        } else if (category != null) {
            products = productRepository.findByShoppersShopperIdAndCategory(shopperId, category);
        } else if (brand != null) {
            products = productRepository.findByShoppersShopperIdAndBrand(shopperId, brand);
        } else {
            products = productRepository.findByShoppersShopperId(shopperId);
        }

        if (limit != null && limit > 0 && products.size() > limit) {
            products = products.subList(0, limit);
        }

        return products.stream()
                .map(this::mapToProductDTO)
                .collect(Collectors.toList());
    }

    private ProductDTO mapToProductDTO(Product product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setProductId(product.getProductId());
        productDTO.setCategory(product.getCategory());
        productDTO.setBrand(product.getBrand());
        return productDTO;
    }

    public Optional<List<ProductResponse>> getProductList() {
        return Optional.of(productRepository.findAll())
                .map(productList -> productList.stream()
                        .map(product -> new ProductResponse(product.getId(), product.getProductId(), product.getCategory(), product.getBrand()))
                        .collect(Collectors.toList()));
    }

    public Optional<ProductResponse> getProductById(String productId) {
        return productRepository.findByProductId(productId)
                .map(product -> new ProductResponse(product.getId(), product.getProductId(), product.getCategory(), product.getBrand()));
    }


}
