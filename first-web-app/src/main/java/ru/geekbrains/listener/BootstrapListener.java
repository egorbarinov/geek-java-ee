package ru.geekbrains.listener;

import ru.geekbrains.model.Category;
import ru.geekbrains.model.Product;
import ru.geekbrains.model.Role;
import ru.geekbrains.model.User;
import ru.geekbrains.persist.CategoryRepository;
import ru.geekbrains.persist.ProductRepository;
import ru.geekbrains.persist.UserRepository;

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

        categoryRepository.saveOrUpdate(new Category(null, "Овощи", "отечественные"));
        categoryRepository.saveOrUpdate(new Category(null, "Фрукты", "иноземные"));

        userRepository.saveOrUpdate(new User(null, "Egor", "password", Role.ROLE_ADMIN));
        userRepository.saveOrUpdate(new User(null, "user", "user", Role.ROLE_USER));

        sce.getServletContext().setAttribute("productRepository", productRepository);
        sce.getServletContext().setAttribute("categoryRepository", categoryRepository);
        sce.getServletContext().setAttribute("userRepository", userRepository);
    }

}
