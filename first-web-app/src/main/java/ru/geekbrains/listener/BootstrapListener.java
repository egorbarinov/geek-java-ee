package ru.geekbrains.listener;

import ru.geekbrains.persist.*;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.math.BigDecimal;

@WebListener
public class BootstrapListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ProductRepository productRepository = new ProductRepository();
        CategoryRepository categoryRepository = new CategoryRepository();
        UserRepository userRepository = new UserRepository();

        productRepository.saveOrUpdate(new Product(null, "Product  1",
                "Description of product 1", new BigDecimal(100)));
        productRepository.saveOrUpdate(new Product(null, "Product  2",
                "Description of product 2", new BigDecimal(200)));
        productRepository.saveOrUpdate(new Product(null, "Product  3",
                "Description of product 3", new BigDecimal(200)));

        categoryRepository.saveOrUpdate(new Category(null, "Category 1"));
        categoryRepository.saveOrUpdate(new Category(null, "Category 2"));
        categoryRepository.saveOrUpdate(new Category(null, "Category 3"));
        categoryRepository.saveOrUpdate(new Category(null, "Category 4"));

        userRepository.saveOrUpdate(new User(null, "Козьма", "Прутков"));
        userRepository.saveOrUpdate(new User(null, "Max", "Frei"));
        userRepository.saveOrUpdate(new User(null, "Emil", "Azhar"));
        userRepository.saveOrUpdate(new User(null, "Boris", "Akunin"));

        sce.getServletContext().setAttribute("productRepository", productRepository);
        sce.getServletContext().setAttribute("categoryRepository", categoryRepository);
        sce.getServletContext().setAttribute("userRepository", userRepository);
    }
}
