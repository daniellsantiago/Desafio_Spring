package com.grupo2.desafiospring.controller;

import com.grupo2.desafiospring.dto.ListProductParamsDto;
import com.grupo2.desafiospring.model.Product;
import com.grupo2.desafiospring.service.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> getProducts(ListProductParamsDto params) {
        return productService.listProducts(params);
    }
}
