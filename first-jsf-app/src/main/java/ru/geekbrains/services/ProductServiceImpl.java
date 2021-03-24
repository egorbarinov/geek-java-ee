package ru.geekbrains.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.geekbrains.dto.ProductDto;
import ru.geekbrains.persist.Category;
import ru.geekbrains.persist.CategoryRepository;
import ru.geekbrains.persist.Product;
import ru.geekbrains.persist.ProductRepository;
import ru.geekbrains.rest.ProductServiceRest;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.jws.WebService;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
@WebService(endpointInterface = "ru.geekbrains.services.ProductServiceWs", serviceName = "ProductService")
@Remote(ProductServiceRemote.class)
public class ProductServiceImpl implements ProductService, ProductServiceRemote,
        ProductServiceRest, ProductServiceWs {

    private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    @EJB
    private ProductRepository productRepository;

    @EJB
    private CategoryRepository categoryRepository;

    @Override
    public List<ProductDto> findAll() {
        return productRepository.findAll().stream()
                .map(this::buildProductDto).collect(Collectors.toList());
    }

    @Override
    public ProductDto findById(Long id) {
        Product product = productRepository.findById(id);
        if(product == null) return null;
        return buildProductDto(product);
    }

    @Override
    public ProductDto findByName(String name) {
        Product product = productRepository.findByName(name);
        if(product == null) return null;
        return buildProductDto(product);
    }

    @Override
    public List<ProductDto> findAllByCategory(Long categoryId) {
        return productRepository.findAllByCategory(categoryId).stream()
                .map(this::buildProductDto).collect(Collectors.toList());
    }

    @Override
    public Long countAll() {
        return productRepository.countAll();
    }

    @Override
    public void insert(ProductDto productDto) {
        if(productDto.getId() != null) {
            throw new IllegalArgumentException();
        }
        saveOrUpdate(productDto);
    }

    @Override
    public void update(ProductDto productDto) {
        if(productDto.getId() == null) {
            throw new IllegalArgumentException();
        }
        saveOrUpdate(productDto);
    }

    @TransactionAttribute
    @Override
    public void saveOrUpdate(ProductDto productDto) {
        Category category = null;
        if(productDto.getCategoryId() != null) {
            category = categoryRepository.getReference(productDto.getCategoryId());
        }
        productRepository.saveOrUpdate(new Product(productDto, category));
    }

    @TransactionAttribute
    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    public ProductDto buildProductDto(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setName(product.getName());
        productDto.setDescription(product.getDescription());
        productDto.setPrice(product.getPrice());
        Category category = product.getCategory();
        productDto.setCategoryId((category == null) ? null : category.getId());
        productDto.setCategoryName((category == null) ? null : category.getName());
        return productDto;
    }
}
