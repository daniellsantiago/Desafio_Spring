package com.grupo2.desafiospring.service;

import com.grupo2.desafiospring.dto.ProductDTO;
import com.grupo2.desafiospring.dto.ListProductParamsDto;
import com.grupo2.desafiospring.dto.RegisterProductDto;
import com.grupo2.desafiospring.model.Product;

import java.util.List;

public interface ProductService {
    List<ProductDTO> addProduct(List<RegisterProductDto> registerProductDtos);
    List<Product> listProducts(ListProductParamsDto params);
}
