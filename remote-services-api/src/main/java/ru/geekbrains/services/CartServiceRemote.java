package ru.geekbrains.services;

import ru.geekbrains.dto.ProductDto;

import java.util.List;

public interface CartServiceRemote {

    int getSize();
    List<ProductDto> getProductsInCart();
}
