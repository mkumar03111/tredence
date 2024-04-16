package com.ecommerce.tredence.api.model;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import javax.validation.constraints.NotBlank;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "productId")
    @NotBlank(message = "Product Id cannot be blank")
    private String productId;

    @Column(name = "category")
    @NotBlank(message = "Category cannot be blank")
    private String category;

    @Column(name = "brand")
    @NotBlank(message = "Brand cannot be blank")
    private String brand;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ShopperProduct> shopperProductLists = new HashSet<>();

    @ManyToMany(mappedBy = "products")
    private Set<Shopper> shoppers = new HashSet<>();

    public void addShopperProduct(ShopperProduct shopperProduct) {
        shopperProductLists.add(shopperProduct);
        shopperProduct.setProduct(this);
    }

    public void removeShopperProduct(ShopperProduct shopperProduct) {
        shopperProductLists.remove(shopperProduct);
        shopperProduct.setProduct(null);
    }
}