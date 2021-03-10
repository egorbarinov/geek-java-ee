package geekbrains.ru.geekbrains.controller;

import geekbrains.ru.geekbrains.dto.ProductDto;
import geekbrains.ru.geekbrains.sevice.CartService;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Named
@SessionScoped
public class CartController implements Serializable {

    @EJB
    private CartService cartService;

    // TODO
    private final Map<Long, ProductDto> productMap = new HashMap<>();

    public void addToCart(ProductDto product) {
        productMap.put(product.getId(), product);
    }

    public void removeFromCart(ProductDto product) {
        productMap.remove(product.getId());
    }

    public List<ProductDto> getAllProducts() {
        return new ArrayList<>(productMap.values());
    }
}
