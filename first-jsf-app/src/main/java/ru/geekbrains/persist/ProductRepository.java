package ru.geekbrains.persist;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
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

    public void saveOrUpdate(Product product) {
        if (product.getId() == null) {
            entityManager.persist(product);
        }
        entityManager.merge(product);
    }

    public void deleteById(Long id) {
        entityManager.createNamedQuery("deleteProductById").setParameter("id", id).executeUpdate();
    }

    public Product findByName(String name) {
        return entityManager.createNamedQuery("findProductByName", Product.class).setParameter("name", name).getSingleResult();
    }

    public List<Product> findAllByCategory(Long categoryId) {
        return entityManager.createNamedQuery("findProductByCategory", Product.class).setParameter("category_id", categoryId).getResultList();
    }

}
