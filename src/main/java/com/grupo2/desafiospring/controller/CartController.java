package com.grupo2.desafiospring.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.grupo2.desafiospring.model.*;
import com.grupo2.desafiospring.service.CartService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/purchase")
public class CartController {

    private final CartService cartService;


    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Ticket setCart(@RequestBody ItemList purchaseList) throws Exception {
        return cartService.setCart(purchaseList);
    }
}
