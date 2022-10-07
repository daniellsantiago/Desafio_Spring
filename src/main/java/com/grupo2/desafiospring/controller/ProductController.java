package com.grupo2.desafiospring.controller;

import com.grupo2.desafiospring.dto.ListProductParamsDto;
import com.grupo2.desafiospring.dto.ProductDTO;
import com.grupo2.desafiospring.dto.RegisterProductDto;
import com.grupo2.desafiospring.model.Product;
import com.grupo2.desafiospring.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;


@RestController
@RequestMapping("/api/v1/products")
@Validated
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public List<ProductDTO> addProduct(@RequestBody @Valid @NotEmpty List<RegisterProductDto> registerProductDtos) {
        return productService.addProduct(registerProductDtos);
    }

    @GetMapping
    public List<Product> getProducts(@Valid ListProductParamsDto params) {
        return productService.listProducts(params);
    }
}
