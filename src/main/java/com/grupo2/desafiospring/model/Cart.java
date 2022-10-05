package com.grupo2.desafiospring.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cart {
    private Long id;
    private List<Product> products;
    private Double total;

    public Cart(Long id) {
        this.id = id;
    }
}
