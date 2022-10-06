package com.grupo2.desafiospring.model;

import com.grupo2.desafiospring.dto.CartProductDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cart {
    private UUID id;
    private List<CartProductDTO> products;
    private Double total;
}
