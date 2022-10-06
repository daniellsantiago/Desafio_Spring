package com.grupo2.desafiospring.service;

import com.grupo2.desafiospring.dto.CartProductDTO;
import com.grupo2.desafiospring.exception.BusinessRuleException;
import com.grupo2.desafiospring.exception.InternalServerErrorException;
import com.grupo2.desafiospring.exception.NotFoundException;
import com.grupo2.desafiospring.model.*;
import com.grupo2.desafiospring.repository.CartRepository;
import com.grupo2.desafiospring.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartService{

    private final ProductRepository productRepository;
    private final CartRepository cartRepository;

    public CartServiceImpl(ProductRepository productRepository, CartRepository cartRepository) {
        this.productRepository = productRepository;
        this.cartRepository = cartRepository;
    }


    @Override
    public Ticket setCart(ItemList itemList) throws Exception {
        /*
         *  TODO: abrir a lista e consultar os produtos; #Feito
         *  TODO: set da quantidade da compra de acordo com o passado; #Feito
         *  TODO: gerar id random; #Feito
         *  TODO: calcular total de acordo com a quantidade; #Feito
         *  TODO: tratar as exceções; #Feito
         *  TODO: retornar os status code corretos (404, 500, 200); #Feito
         *  TODO: add no repositorio cart;
         *  TODO: controle de estoque;
         */
        List<CartProductDTO> products = getProductsCart(itemList);
        int idRandom = getRandomNumberInRange(10000, 99999);
        Double total = 0.0;
        for (CartProductDTO p :
                products) {
            total += calcTotal(p.getQuantity(), p.getPrice());
        }

        return new Ticket(new Cart(idRandom, products, total));
    }

    private List<CartProductDTO> getProductsCart(ItemList itemList) {
        List<Product> productList;

        try{
             productList = productRepository.getAllProducts(null);
        } catch (IOException ex){
            throw new InternalServerErrorException("Erro ao ler arquivo de repositório dos produtos");
        }

        List<Product> productsCart = new ArrayList<>();

        for (ProductPurchase pp: itemList.getArticlesPurchaseRequest()) {
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

        return CartProductDTO.convertDto(productsCart);
    }

    private Double calcTotal(Integer quantity, BigDecimal price){
        return price.multiply(BigDecimal.valueOf(quantity)).doubleValue();
    }

    private int getRandomNumberInRange(int min, int max) {

        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }
}
