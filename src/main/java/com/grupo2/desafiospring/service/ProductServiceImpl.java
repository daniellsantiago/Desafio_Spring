package com.grupo2.desafiospring.service;

import com.grupo2.desafiospring.dto.ListProductParamsDto;
import com.grupo2.desafiospring.dto.ProductDTO;
import com.grupo2.desafiospring.dto.RegisterProductDto;
import com.grupo2.desafiospring.exception.BusinessRuleException;
import com.grupo2.desafiospring.exception.InternalServerErrorException;
import com.grupo2.desafiospring.model.Product;
import com.grupo2.desafiospring.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<ProductDTO> addProduct(List<RegisterProductDto> registerProductDtos) {
        List<Product> productsToBeAdded = registerProductDtos.stream()
                .map(dto -> dto.toProduct(UUID.randomUUID()))
                .collect(Collectors.toList());
        try{
            return ProductDTO.fromProductList(productRepository.addProducts(productsToBeAdded));
        } catch (IOException ex){
            throw new InternalServerErrorException("Error trying to write products");
        }
    }

    @Override
    public List<Product> listProducts(ListProductParamsDto params) {
        try {
            List<Product> products = productRepository.getAllProducts();
            if (!params.hasAnyFilterParam() && !params.hasAnySortParam()) {
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
        var filteredByCategory = filterProductsByCategory(products, params);
        var filteredByFreeShipping = filterProductsByFreeShipping(filteredByCategory, params);
        return filterProductsByPrestige(filteredByFreeShipping, params);
    }

    private Stream<Product> filterProductsByFreeShipping(Stream<Product> products, ListProductParamsDto params) {
        if (params == null || params.getFreeShipping() == null) {
            return products;
        }

        return products.filter(product -> product.getFreeShipping() == params.getFreeShipping());
    }

    private Stream<Product> filterProductsByCategory(Stream<Product> products, ListProductParamsDto params) {
        if (params == null || params.getCategory() == null) {
            return products;
        }

        return products.filter(product -> product.getCategory().equals(params.getCategory()));
    }

    private Stream<Product> filterProductsByPrestige(Stream<Product> products, ListProductParamsDto params) {
        if (params == null || params.getPrestige() == null) {
            return products;
        }

        return products.filter(product -> product.getPrestige().equals(params.getPrestige()));
    }

    private Stream<Product> sortProducts(Integer paramOrder, Stream<Product> products) {
        if (paramOrder == null) return products;

        if(paramOrder == 0) return sortProductsByNameAsc(products);
        if(paramOrder == 1) return sortProductsByNameDesc(products);
        if(paramOrder == 2) return sortProductsByHighestPrice(products);
        if(paramOrder == 3) return sortProductsByLowestPrice(products);

        throw new BusinessRuleException("Invalid param order provided");
    }

    private Stream<Product> sortProductsByNameAsc(Stream<Product> products) {
        return products.sorted(Comparator.comparing(Product::getName));
    }

    private Stream<Product> sortProductsByNameDesc(Stream<Product> products) {
        return products.sorted(Comparator.comparing(Product::getName).reversed());
    }

    private Stream<Product> sortProductsByHighestPrice(Stream<Product> products) {
        return products.sorted(Comparator.comparing(Product::getPrice).reversed());
    }

    private Stream<Product> sortProductsByLowestPrice(Stream<Product> products) {
        return products.sorted(Comparator.comparing(Product::getPrice));
    }
}
