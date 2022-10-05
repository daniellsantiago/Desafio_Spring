package com.grupo2.desafiospring.service;

import com.grupo2.desafiospring.dto.ProductDTO;
import com.grupo2.desafiospring.exception.InternalServerErrorException;
import com.grupo2.desafiospring.exception.NotFoundException;
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
    public List<Product> listProducts() {
        try {
            return productRepository.getAllProducts();
        } catch (IOException e) {
            throw new InternalServerErrorException("Error trying to read products");
        }
    }

    @Override
    public List<ProductDTO> addProduct(List<Product> product) {
        try{
            return ProductDTO.convertDto(productRepository.addProductRepository(product));
        } catch (IOException ex){
            throw new InternalServerErrorException("Error trying to write products");
        }

    }
}
