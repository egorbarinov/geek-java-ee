package ru.geekbrains;

import ru.geekbrains.services.ProductService;
import ru.geekbrains.services.ProductServiceWs;

import java.net.MalformedURLException;
import java.net.URL;

public class Main {
    public static void main(String[] args) throws MalformedURLException {
        URL url = new URL("http://localhost:8080/first-jsf-app/ProductService/ProductServiceImpl?wsdl");
        ProductService productService = new ProductService(url);

        ProductServiceWs port = productService.getProductServiceImplPort();

        port.findAll().forEach(product -> System.out.println(product.getName()));
    }
}
