package ru.geekbrains.controllers;

import ru.geekbrains.dto.ProductDto;
import ru.geekbrains.services.CartService;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class CartController implements Serializable {

    @EJB
    private CartService cartService;

    public List<ProductDto> getProductsInCart() {
        return cartService.getProductsInCart();
    }

    public void addToCart(ProductDto productDto) {
        cartService.addToCart(productDto);
    }

    public void removeFromCart(ProductDto productDto) {
        cartService.removeFromCart(productDto);
    }

    public int getSize() {
        return cartService.getSize();
    }

}
