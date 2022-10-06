package com.grupo2.desafiospring.controller;

import com.grupo2.desafiospring.model.ItemList;
import com.grupo2.desafiospring.model.Ticket;
import com.grupo2.desafiospring.service.CartService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/purchase")
public class CartController {

    private final CartService cartService;


    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Ticket setCart(@RequestBody ItemList purchaseList){
        return cartService.setCart(purchaseList);
    }
}
