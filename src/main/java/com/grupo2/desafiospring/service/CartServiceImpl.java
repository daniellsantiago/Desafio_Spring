package com.grupo2.desafiospring.service;

import com.grupo2.desafiospring.model.Cart;
import com.grupo2.desafiospring.model.ProductPurchase;
import com.grupo2.desafiospring.repository.CartRepository;
import com.grupo2.desafiospring.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class CartServiceImpl implements CartService{

    private final CartRepository cartRepository;

    public CartServiceImpl(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }
    @Override
    public Cart setCart(List<ProductPurchase> purchaseList) {
        /*
         *  TODO: abrir a lista e consultar os produtos;
         *  TODO: set da quantidade da compra de acordo com o passado;
         *  TODO: gerar id random;
         *  TODO: calcular total de acordo com a quantidade;
         *  TODO: tratar as exceções;
         *  TODO: retornar os status code corretos (404, 500, 200)
         *
         */
        return null;
    }
}
