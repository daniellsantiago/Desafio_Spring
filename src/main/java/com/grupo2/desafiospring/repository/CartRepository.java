package com.grupo2.desafiospring.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;

@Repository
public class CartRepository {
    private final String PATH_NAME = "src/main/resources/carts.json";

    private final ObjectMapper objectMapper;

    public CartRepository(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }
}
