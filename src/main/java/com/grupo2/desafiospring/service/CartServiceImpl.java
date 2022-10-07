package com.grupo2.desafiospring.service;

import com.grupo2.desafiospring.dto.CartProductDTO;
import com.grupo2.desafiospring.exception.BusinessRuleException;
import com.grupo2.desafiospring.exception.InternalServerErrorException;
import com.grupo2.desafiospring.exception.NotFoundException;
import com.grupo2.desafiospring.model.*;
import com.grupo2.desafiospring.repository.ProductRepository;
import com.grupo2.desafiospring.repository.TicketRepository;
import com.grupo2.desafiospring.utils.IdGenerator;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class CartServiceImpl implements CartService{

    private final ProductRepository productRepository;
    private final TicketRepository ticketRepository;

    public CartServiceImpl(ProductRepository productRepository, TicketRepository ticketRepository) {
        this.productRepository = productRepository;
        this.ticketRepository = ticketRepository;
    }


    @Override
    public Ticket setCart(ItemList itemList) {

        List<CartProductDTO> products = getProductsCart(itemList);
        Long id = IdGenerator.generateIdByClass(Cart.class);
        Double total = getTotal(products);
        return generateTicket(id, products, total);

    }
    private List<CartProductDTO> getProductsCart(ItemList itemList) {

        List<Product> productList;
        try{
             productList = productRepository.getAllProducts();
        } catch (IOException ex){
            throw new InternalServerErrorException("Erro ao ler arquivo de repositório dos produtos");
        }

        List<Product> productsCart = new ArrayList<>();
        for (ProductPurchase pp : itemList.getProductsPurchaseRequest()) {
            Product product = productList.stream()
                    .filter(p -> Objects.equals(pp.getProductId(), p.getProductId()))
                    .findFirst()
                    .orElseThrow(() -> new NotFoundException("Produto não encontrado."));

            if (pp.getQuantity() > product.getQuantity()){
                throw new BusinessRuleException("Quantidade em estoque insuficiente.");
            }

            product.setQuantity(pp.getQuantity());
            productsCart.add(product);
        }

        return CartProductDTO.fromCartList(productsCart);
    }

    private Double getTotal(List<CartProductDTO> products){
        Double total = 0.0;
        for (CartProductDTO p : products) {
            total += calcTotal(p.getQuantity(), p.getPrice());
            try{
                productRepository.patchProduct(p.getProductId(), p.getQuantity());
            } catch(IOException ex){
                throw new InternalServerErrorException("Erro ao salvar o ticket no arquivo.");
            }
        }
        return total;
    }

    private Double calcTotal(Integer quantity, BigDecimal price){
        return price.multiply(BigDecimal.valueOf(quantity)).doubleValue();
    }

    private Ticket generateTicket(Long id, List<CartProductDTO> products, Double total) {
        Ticket ticket = new Ticket(new Cart(id, products, total));
        try{
            ticketRepository.addTicket(ticket);
        } catch (IOException ex){
            throw new InternalServerErrorException("Erro ao salvar o ticket no arquivo.");
        }
        return ticket;
    }
}
