package ru.geekbrains.controllers;

import ru.geekbrains.dto.ProductDto;
import ru.geekbrains.services.ProductService;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class ProductController implements Serializable {

    @EJB
    private ProductService productService;

    private ProductDto product;

    private List<ProductDto> products;

    @Inject
    private HttpSession httpSession;

    public void preloadData(ComponentSystemEvent componentSystemEvent) {
        products = productService.findAll();
    }

    public List<ProductDto> getAllProducts() {
        return products;
    }

    public ProductDto getProduct() {
        return product;
    }

    public void setProduct(ProductDto productDto) {
        this.product = productDto;
    }

    public String createProduct() {
        this.product = new ProductDto();
        return "/product_form.xhtml?faces-redirect=true";
    }

    public String editProduct(ProductDto productDto) {
        this.product = productDto;
        return "/product_form.xhtml?faces-redirect=true";
    }

    public void deleteProduct(ProductDto productDto) {
        productService.deleteById(productDto.getId());
    }

    public String saveProduct() {
        productService.saveOrUpdate(product);
        return "/product.xhtml?faces-redirect=true";
    }

    public Object logout() {
        httpSession.invalidate();
        return "/product.xhtml?faces-redirect=true";
    }
}
