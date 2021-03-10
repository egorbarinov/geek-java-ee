package geekbrains.ru.geekbrains.listener;


import geekbrains.ru.geekbrains.persist.Category;
import geekbrains.ru.geekbrains.persist.Product;
import geekbrains.ru.geekbrains.persist.Role;
import geekbrains.ru.geekbrains.persist.User;
import geekbrains.ru.geekbrains.repository.CategoryRepository;
import geekbrains.ru.geekbrains.repository.ProductRepository;
import geekbrains.ru.geekbrains.repository.UserRepository;

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

        categoryRepository.saveOrUpdate(new Category(null, "Напитки", "вода"));
        categoryRepository.saveOrUpdate(new Category(null, "Фрукты", "яблоки"));

        userRepository.saveOrUpdate(new User(null, "Vasya", "pass", Role.ROLE_ADMIN));
        userRepository.saveOrUpdate(new User(null, "user", "user", Role.ROLE_USER));

        sce.getServletContext().setAttribute("productRepository", productRepository);
        sce.getServletContext().setAttribute("categoryRepository", categoryRepository);
        sce.getServletContext().setAttribute("userRepository", userRepository);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
