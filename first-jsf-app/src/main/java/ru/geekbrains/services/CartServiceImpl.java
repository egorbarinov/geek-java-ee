package ru.geekbrains.services;


import ru.geekbrains.dto.ProductDto;

import javax.ejb.Stateful;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Stateful
public class CartServiceImpl implements CartService, CartServiceRemote {

    private final Map<Long, ProductDto> cart = new HashMap<>();

    @Override
    public void addToCart(ProductDto product) {
        cart.put(product.getId(), product);
    }

    @Override
    public void removeFromCart(ProductDto product) {
        cart.remove(product.getId());
    }

    @Override
    public int getSize() {
        return cart.size();
    }

    @Override
    public List<ProductDto> getProductsInCart() {
        return new ArrayList<>(cart.values());
    }
}
