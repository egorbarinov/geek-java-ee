package ru.geekbrains.services;

import ru.geekbrains.dto.ProductDto;

import javax.ejb.Local;
import java.util.List;

@Local
public interface CartService {

    void addToCart(ProductDto product);
    void removeFromCart(ProductDto product);
    int getSize();
    List<ProductDto> getProductsInCart();
}
