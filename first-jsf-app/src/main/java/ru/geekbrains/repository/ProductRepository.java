package ru.geekbrains.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.geekbrains.persist.Product;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Named
@ApplicationScoped
public class ProductRepository {

    private static final Logger logger = LoggerFactory.getLogger(ProductRepository.class);

    @PersistenceContext(unitName = "ds")
    private EntityManager entityManager;

    public List<Product> findAll() {
        return entityManager.createNamedQuery("findAllProducts", Product.class).getResultList();
    }

    public Product findById(Long id) {
        return entityManager.find(Product.class, id);
    }

    public Long countAll() {
        return entityManager.createNamedQuery("countAllProducts", Long.class).getSingleResult();
    }

    @Transactional
    public void saveOrUpdate(Product product) {
        if (product.getId() == null) {
            entityManager.persist(product);
        }
        entityManager.merge(product);
    }

    @Transactional
    public void deleteById(Long id) {
        entityManager.createNamedQuery("deleteProductById").setParameter("id", id).executeUpdate();
    }
}

