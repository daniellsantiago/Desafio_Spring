package com.grupo2.desafiospring.service;

import com.grupo2.desafiospring.dto.ListProductParamsDto;
import com.grupo2.desafiospring.model.Product;

import java.util.List;

public interface ProductService {
    List<Product> listProducts(ListProductParamsDto params);
}
