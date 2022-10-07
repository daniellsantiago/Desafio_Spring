package com.grupo2.desafiospring.dto;

import com.grupo2.desafiospring.validation.PrestigeValidation;
import com.grupo2.desafiospring.model.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.UUID;

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
    @Positive
    private BigDecimal price;
    @NotNull
    @Positive
    private Integer quantity;
    @NotNull
    private Boolean freeShipping;
    @NotEmpty
    @PrestigeValidation
    @Size(min = 1, max = 5)
    private String prestige;

    public Product toProduct(UUID productId) {
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
