package ru.geekbrains.controller;

import ru.geekbrains.persist.Product;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Named
@SessionScoped
public class CartController implements Serializable {

    private Map<Long, Product> productMap = new HashMap<>();

    public String addToCart(Product product) {
        return "";
    }

    public String removeFromCart(Product product) {
        return "";
    }
}
