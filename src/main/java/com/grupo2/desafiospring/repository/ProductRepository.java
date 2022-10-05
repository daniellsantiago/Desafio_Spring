package com.grupo2.desafiospring.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.grupo2.desafiospring.dto.ListProductParamsDto;
import com.grupo2.desafiospring.model.Product;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ProductRepository {

    private final String PATH_NAME = "src/main/resources/products.json";

    private final ObjectMapper objectMapper;

    public ProductRepository(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public List<Product> getAllProducts(ListProductParamsDto params) throws IOException {
        List<Product> products = Arrays.asList(objectMapper.readValue(new File(PATH_NAME), Product[].class));
        return products.stream()
                .filter(product -> {
                    if (params.getFreeShipping() != null && params.getPrestige() != null) {
                        return product.getFreeShipping() == params.getFreeShipping() && params.getPrestige().equals(product.getPrestige());
                    }
                    if (params.getCategory() != null && params.getFreeShipping() != null) {
                        return params.getCategory().equals(product.getCategory()) && product.getFreeShipping() == params.getFreeShipping();
                    }
                    if (params.getCategory() != null) {
                        return params.getCategory().equals(product.getCategory());
                    }
                    return true;
                })
                .collect(Collectors.toList());
    }
}
