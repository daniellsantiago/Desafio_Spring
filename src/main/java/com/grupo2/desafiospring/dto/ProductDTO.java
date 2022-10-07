package com.grupo2.desafiospring.dto;

import com.grupo2.desafiospring.model.Product;
import lombok.Data;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
public class ProductDTO {
    private UUID productId;
    private String name;
    private Integer quantity;

    public ProductDTO(Product product) {
        this.productId = product.getProductId();
        this.name = product.getName();
        this.quantity = product.getQuantity();
    }

    public static List<ProductDTO> fromProductList(List<Product> products) {
        return products.stream().map(ProductDTO::new).collect(Collectors.toList());
    }
}
