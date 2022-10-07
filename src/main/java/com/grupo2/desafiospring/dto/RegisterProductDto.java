package com.grupo2.desafiospring.dto;

import com.grupo2.desafiospring.model.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterProductDto {
    @NotEmpty
    private String name;
    @NotEmpty
    private String category;
    @NotEmpty
    private String brand;
    @NotNull
    private BigDecimal price;
    @NotNull
    private Integer quantity;
    @NotNull
    private Boolean freeShipping;
    @NotEmpty
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
