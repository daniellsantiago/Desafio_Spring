package com.grupo2.desafiospring.controller;

import com.grupo2.desafiospring.model.Cart;
import com.grupo2.desafiospring.model.ProductPurchase;
import com.grupo2.desafiospring.service.CartService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/purchase")
    @ResponseStatus(HttpStatus.CREATED)
    public Cart setCart(@RequestBody List<ProductPurchase> purchaseList) {
        return cartService.setCart(purchaseList);
    }
}
