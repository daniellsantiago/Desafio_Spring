package com.grupo2.desafiospring.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductPurchase {
    private Long productId;
    private Integer quantity;
}
