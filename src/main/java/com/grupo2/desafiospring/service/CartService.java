package com.grupo2.desafiospring.service;

import com.grupo2.desafiospring.model.Cart;
import com.grupo2.desafiospring.model.ProductPurchase;

import java.util.List;


public interface CartService {
    Cart setCart(List<ProductPurchase> purchaseList);
}
