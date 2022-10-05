package com.grupo2.desafiospring.dto;

import com.grupo2.desafiospring.model.Product;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class ProductDTO implements Serializable {
    private Long productId;
    private String name;
    private Integer quantity;

    public ProductDTO(Product articles) {
        this.productId = articles.getProductId();
        this.name = articles.getName();
        this.quantity = articles.getQuantity();
    }

    public static List<ProductDTO> convertDto(List<Product> articles) {
        return articles.stream().map(ProductDTO::new).collect(Collectors.toList());
    }
}
