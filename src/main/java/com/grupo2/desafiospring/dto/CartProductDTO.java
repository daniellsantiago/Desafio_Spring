package com.grupo2.desafiospring.dto;

import com.grupo2.desafiospring.model.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartProductDTO {
    private Long productId;
    private String name;
    private String category;
    private String brand;
    private BigDecimal price;
    private Integer quantity;
    private Boolean freeShipping;
    private String prestige;

    public CartProductDTO(Product product) {
        this.productId = product.getProductId();
        this.name = product.getName();
        this.category = product.getCategory();
        this.brand = product.getBrand();
        this.price = product.getPrice();
        this.quantity = product.getQuantity();
        this.freeShipping = product.getFreeShipping();
        this.prestige = product.getPrestige();
    }

    public static List<CartProductDTO> fromCartList(List<Product> articles) {
        return articles.stream().map(CartProductDTO::new).collect(Collectors.toList());
    }
}
