package com.grupo2.desafiospring.service;

import com.grupo2.desafiospring.dto.*;
import com.grupo2.desafiospring.exception.BusinessRuleException;
import com.grupo2.desafiospring.exception.InternalServerErrorException;
import com.grupo2.desafiospring.exception.NotFoundException;
import com.grupo2.desafiospring.model.*;
import com.grupo2.desafiospring.repository.ProductRepository;
import com.grupo2.desafiospring.repository.TicketRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class CartServiceImpl implements CartService{

    private final ProductRepository productRepository;
    private final TicketRepository ticketRepository;

    public CartServiceImpl(ProductRepository productRepository, TicketRepository ticketRepository) {
        this.productRepository = productRepository;
        this.ticketRepository = ticketRepository;
    }

    @Override
    public TicketDto setCart(ProductPurchaseListDto productPurchaseListDto) {
        List<Product> products = getProductsCart(productPurchaseListDto);

        Double total = getTotal(products);
        return generateTicket(UUID.randomUUID(), products, total);
    }

    private List<Product> getProductsCart(ProductPurchaseListDto productPurchaseListDto) {

        List<Product> productList;
        try{
             productList = productRepository.getAllProducts();
        } catch (IOException ex){
            throw new InternalServerErrorException("Erro ao ler arquivo de repositório dos produtos");
        }

        List<Product> cartProducts = new ArrayList<>();
        for (ProductPurchaseItemDto pp : productPurchaseListDto.getProductsPurchaseRequest()) {
            Product product = productList.stream()
                    .filter(p -> Objects.equals(pp.getProductId(), p.getProductId()))
                    .findFirst()
                    .orElseThrow(() -> new NotFoundException("Produto não encontrado."));

            if (pp.getQuantity() > product.getQuantity()){
                throw new BusinessRuleException("Quantidade em estoque insuficiente.");
            }

            product.setQuantity(pp.getQuantity());
            cartProducts.add(product);
        }

        return cartProducts;
    }

    private Double getTotal(List<Product> products){
        double total = 0.0;
        for (Product p : products) {
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

    private TicketDto generateTicket(UUID id, List<Product> products, Double total) {
        List<CartProductDTO> cartProductDTOS = CartProductDTO.fromCartList(products);
        TicketDto ticket = new TicketDto(new CartDto(id, cartProductDTOS, total));
        try{
            ticketRepository.addTicket(ticket);
        } catch (IOException ex){
            throw new InternalServerErrorException("Erro ao salvar o ticket no arquivo.");
        }
        return ticket;
    }
}
