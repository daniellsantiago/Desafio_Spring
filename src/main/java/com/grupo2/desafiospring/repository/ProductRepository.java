package com.grupo2.desafiospring.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.grupo2.desafiospring.model.Product;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Repository
public class ProductRepository {

    private final String PATH_NAME = "src/main/resources/products.json";

    private final ObjectMapper objectMapper;

    public ProductRepository(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public List<Product> getAllProducts() throws IOException {
        return Arrays.asList(objectMapper.readValue(new File(PATH_NAME), Product[].class));
    }
}
