package com.grupo2.desafiospring.service;

import com.grupo2.desafiospring.model.*;

import java.io.IOException;
import java.util.List;


public interface CartService {
    Ticket setCart(ItemList itemList) throws Exception;
}
