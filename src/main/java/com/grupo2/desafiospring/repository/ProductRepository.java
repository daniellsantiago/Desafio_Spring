package com.grupo2.desafiospring.repository;

import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.grupo2.desafiospring.model.Product;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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

    public List<Product> addProducts(List<Product> products) throws IOException {
        ObjectWriter writer = objectMapper.writer(new DefaultPrettyPrinter());
        List<Product> productList = new ArrayList<>(getAllProducts());

        productList.addAll(products);
        writer.writeValue(new File(PATH_NAME), productList);

        return products;
    }
}
