package com.grupo2.desafiospring.dto;

import com.grupo2.desafiospring.model.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterProductDto {
    private String name;
    private String category;
    private String brand;
    private BigDecimal price;
    private Integer quantity;
    private Boolean freeShipping;
    private String prestige;

    public Product toProduct(Long productId) {
        return new Product(
                productId,
                this.name,
                this.category,
                this.brand,
                this.price,
                this.quantity,
                this.freeShipping,
                this.prestige
        );
    }
}
