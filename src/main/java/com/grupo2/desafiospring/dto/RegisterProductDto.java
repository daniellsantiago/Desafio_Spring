package com.grupo2.desafiospring.dto;

import com.grupo2.desafiospring.annotation.PrestigeValidation;
import com.grupo2.desafiospring.model.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
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
    @Max(5)
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
