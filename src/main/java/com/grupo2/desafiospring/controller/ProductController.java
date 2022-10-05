package com.grupo2.desafiospring.controller;

import com.grupo2.desafiospring.dto.ProductDTO;
import com.grupo2.desafiospring.model.Product;
import com.grupo2.desafiospring.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/insert-articles-request")
    public ResponseEntity<List<ProductDTO>> setProduct(@RequestBody List<Product> product) throws IOException {
        return new ResponseEntity<>(productService.addProduct(product), HttpStatus.CREATED);
    }

}
