package geekbrains.ru.geekbrains.sevice;


import geekbrains.ru.geekbrains.dto.ProductDto;

import javax.ejb.Stateful;
import java.util.HashMap;
import java.util.Map;

@Stateful
public class CartServiceImpl implements CartService {

    private final Map<Long, ProductDto> productMap = new HashMap<>();

    @Override
    public void addToCart(ProductDto product) {

    }
}
