package com.grupo2.desafiospring.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductPurchase {
    private UUID productId;
    private Integer quantity;
}
