package com.grupo2.desafiospring.controller;

import com.grupo2.desafiospring.dto.ProductPurchaseListDto;
import com.grupo2.desafiospring.dto.TicketDto;
import com.grupo2.desafiospring.service.CartService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/purchase")
public class CartController {
    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TicketDto setCart(@RequestBody @Valid ProductPurchaseListDto purchaseList){
        return cartService.setCart(purchaseList);
    }
}
