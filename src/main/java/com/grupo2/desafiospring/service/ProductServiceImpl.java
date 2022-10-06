package com.grupo2.desafiospring.service;

import com.grupo2.desafiospring.dto.ListProductParamsDto;
import com.grupo2.desafiospring.exception.InternalServerErrorException;
import com.grupo2.desafiospring.model.Product;
import com.grupo2.desafiospring.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> listProducts(ListProductParamsDto params) {
        try {
            List<Product> products = productRepository.getAllProducts();
            if (!params.hasAnyFilterParam() || !params.hasAnySortParam()) {
                return products;
            }
            Stream<Product> filteredProducts = filterProducts(products.stream(), params);
            return sortProducts(params.getOrder(), filteredProducts)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new InternalServerErrorException("Error trying to read products");
        }
    }

    private Stream<Product> filterProducts(Stream<Product> products, ListProductParamsDto params) {
        if (!params.hasAnyFilterParam()) {
            return products;
        }

        return products.filter(product -> {
                    if (params.getFreeShipping() != null && params.getPrestige() != null) {
                        return product.getFreeShipping() == params.getFreeShipping()
                                && params.getPrestige().equals(product.getPrestige());
                    }
                    if (params.getCategory() != null && params.getFreeShipping() != null) {
                        return params.getCategory().equalsIgnoreCase(product.getCategory())
                                && product.getFreeShipping() == params.getFreeShipping();
                    }
                    if (params.getCategory() != null) {
                        return params.getCategory().equalsIgnoreCase(product.getCategory());
                    }
                    return true;
                });
    }

    private Stream<Product> sortProducts(Integer paramOrder, Stream<Product> products) {
        if(paramOrder == 0) return sortProductsByCategoryAsc(products);
        if(paramOrder == 1) return sortProductsByCategoryDesc(products);
        if(paramOrder == 2) return sortProductsByHighestPrice(products);
        if(paramOrder == 3) return sortProductsByLowestPrice(products);

        return products;
    }

    private Stream<Product> sortProductsByCategoryAsc(Stream<Product> products) {
        return products.sorted(Comparator.comparing(Product::getName));
    }

    private Stream<Product> sortProductsByCategoryDesc(Stream<Product> products) {
        return products.sorted(Comparator.comparing(Product::getName).reversed());
    }

    private Stream<Product> sortProductsByHighestPrice(Stream<Product> products) {
        return products.sorted(Comparator.comparing(Product::getPrice).reversed());
    }

    private Stream<Product> sortProductsByLowestPrice(Stream<Product> products) {
        return products.sorted(Comparator.comparing(Product::getPrice));
    }
}
