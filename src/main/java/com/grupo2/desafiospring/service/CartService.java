package com.grupo2.desafiospring.service;

import com.grupo2.desafiospring.model.ItemList;
import com.grupo2.desafiospring.model.Ticket;


public interface CartService {
    Ticket setCart(ItemList itemList);
}
