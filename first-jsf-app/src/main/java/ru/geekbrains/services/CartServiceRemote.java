package ru.geekbrains.services;

import ru.geekbrains.dto.ProductDto;

import javax.ejb.Remote;
import java.util.List;

@Remote
public interface CartServiceRemote {

    int getSize();
    List<ProductDto> getProductsInCart();
}
