package com.grupo2.desafiospring.service;

import com.grupo2.desafiospring.dto.ProductDTO;
import com.grupo2.desafiospring.dto.ListProductParamsDto;
import com.grupo2.desafiospring.exception.InternalServerErrorException;
import com.grupo2.desafiospring.exception.NotFoundException;
import com.grupo2.desafiospring.model.Product;
import com.grupo2.desafiospring.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    @Override
    public List<Product> listProducts(ListProductParamsDto params) {
        try {
            List<Product> products = productRepository.getAllProducts(params);
            if (params.getOrder() != null) {
                return listProductsOrder(params.getOrder(), products);
            }
            return products;
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

    private List<Product> listProductsOrder(int paramOrder, List<Product> products) {
        if(paramOrder == 0) return listProductsAsc(products);
        if(paramOrder == 1) return listProductsDesc(products);
        if(paramOrder == 2) return listProductsHigherPrice(products);
        if(paramOrder == 3) return listProductsSmallerPrice(products);

        throw new RuntimeException("Escolha um número de 0 a 3");
    }
    private List<Product> listProductsAsc(List<Product> products) {
        return products.stream()
                .sorted(Comparator.comparing(Product::getName))
                .collect(Collectors.toList());
    }
    private List<Product> listProductsDesc(List<Product> products) {
        return products.stream()
                .sorted(Comparator.comparing(Product::getName).reversed())
                .collect(Collectors.toList());
    }
    private List<Product> listProductsHigherPrice(List<Product> products) {
        return products.stream()
                .sorted(Comparator.comparing(Product::getPrice).reversed())
                .collect(Collectors.toList());
    }
    private List<Product> listProductsSmallerPrice(List<Product> products) {
        return products.stream()
                .sorted(Comparator.comparing(Product::getPrice))
                .collect(Collectors.toList());
    }

}
