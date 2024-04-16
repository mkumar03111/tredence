package com.ecommerce.tredence.api.model;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "shopper", uniqueConstraints = @UniqueConstraint(columnNames = "shopperId"))
public class Shopper {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "shopperId")
    @NotBlank(message = "Shopper Id cannot be blank")
    private String shopperId;

    @OneToMany(mappedBy = "shopper", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ShopperProduct> shelves = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "shopper_product",
            joinColumns = @JoinColumn(name = "shopper_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private Set<Product> products = new HashSet<>();

    public void addShopperProduct(ShopperProduct shopperProduct) {
        shelves.add(shopperProduct);
        shopperProduct.setShopper(this);
    }

    public void removeShopperProduct(ShopperProduct shopperProduct) {
        shelves.remove(shopperProduct);
        shopperProduct.setShopper(null);
    }
}