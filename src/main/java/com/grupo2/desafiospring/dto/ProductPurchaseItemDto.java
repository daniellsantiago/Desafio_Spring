package com.grupo2.desafiospring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductPurchaseItemDto {
    @NotNull
    private UUID productId;

    @Positive
    @NotNull
    private Integer quantity;
}
