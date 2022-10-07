package com.grupo2.desafiospring.service;

import com.grupo2.desafiospring.dto.ProductPurchaseListDto;
import com.grupo2.desafiospring.dto.TicketDto;


public interface CartService {
    TicketDto setCart(ProductPurchaseListDto productPurchaseListDto);
}
