package com.grupo2.desafiospring.service;

import com.grupo2.desafiospring.dto.ListProductParamsDto;
import com.grupo2.desafiospring.exception.InternalServerErrorException;
import com.grupo2.desafiospring.model.Product;
import com.grupo2.desafiospring.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> listProducts(ListProductParamsDto params) {
        try {
            return productRepository.getAllProducts(params);
        } catch (IOException e) {
            throw new InternalServerErrorException("Error trying to read products");
        }
    }

    @Override
    public List<Product> filter() {
        return null;
    }
}
