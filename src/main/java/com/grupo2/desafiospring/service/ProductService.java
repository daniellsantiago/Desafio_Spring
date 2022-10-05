package com.grupo2.desafiospring.service;

import com.grupo2.desafiospring.dto.ProductDTO;
import com.grupo2.desafiospring.model.Product;

import java.io.IOException;
import java.util.List;

public interface ProductService {
    List<Product> listProducts();

    List<ProductDTO> addProduct(List<Product> productList) throws IOException;
}
