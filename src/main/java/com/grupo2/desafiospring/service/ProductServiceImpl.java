package com.grupo2.desafiospring.service;

import com.grupo2.desafiospring.dto.ListProductParamsDto;
import com.grupo2.desafiospring.dto.ProductDTO;
import com.grupo2.desafiospring.dto.RegisterProductDto;
import com.grupo2.desafiospring.exception.BusinessRuleException;
import com.grupo2.desafiospring.exception.InternalServerErrorException;
import com.grupo2.desafiospring.model.Product;
import com.grupo2.desafiospring.repository.ProductRepository;
import com.grupo2.desafiospring.utils.IdGenerator;
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
    public List<ProductDTO> addProduct(List<RegisterProductDto> registerProductDtos) {
        List<Product> productsToBeAdded = registerProductDtos.stream()
                .map(dto -> dto.toProduct(IdGenerator.generateIdByClass(Product.class)))
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
