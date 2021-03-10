package ru.geekbrains.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.geekbrains.persist.Category;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Named
@ApplicationScoped
public class CategoryRepository {

    private static final Logger logger = LoggerFactory.getLogger(CategoryRepository.class);

    @PersistenceContext(unitName = "ds")
    private EntityManager entityManager;

    public List<Category> findAll() {
        return entityManager.createNamedQuery("findAllCategories", Category.class).getResultList();
    }

    public Category findById(Long id) {
        return entityManager.find(Category.class, id);
    }

    public Long countAll() {
        return entityManager.createNamedQuery("countAllCategories", Long.class).getSingleResult();
    }

    @Transactional
    public void saveOrUpdate(Category category) {
        if (category.getId() == null) {
            entityManager.persist(category);
        }
        entityManager.merge(category);
    }

    @Transactional
    public void deleteById(Long id) {
        entityManager.createNamedQuery("deleteCategoryById").setParameter("id", id).executeUpdate();
    }
}
