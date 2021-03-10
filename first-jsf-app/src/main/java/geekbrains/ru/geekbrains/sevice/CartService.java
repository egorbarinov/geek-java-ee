package geekbrains.ru.geekbrains.sevice;

import geekbrains.ru.geekbrains.dto.ProductDto;

import javax.ejb.Local;

@Local
public interface CartService {

    // TODO
    void addToCart(ProductDto product);
}
