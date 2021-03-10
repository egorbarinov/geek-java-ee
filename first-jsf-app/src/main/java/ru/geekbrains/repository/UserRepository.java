package ru.geekbrains.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.geekbrains.persist.User;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.transaction.UserTransaction;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Named
@ApplicationScoped
public class UserRepository {

    private static final Logger logger = LoggerFactory.getLogger(UserRepository.class);

    @PersistenceContext(unitName = "ds")
    private EntityManager entityManager;

    @Resource
    private UserTransaction userTransaction;

    @PostConstruct
    public void init(){
        this.saveOrUpdate(new User(null, "Vasya", "Lozhkin"));
        this.saveOrUpdate(new User(null, "Vasya", "Oblomov"));

    }

    public List<User> findAll() {
        return entityManager.createNamedQuery("findAllUsers", User.class).getResultList();
    }

    public User findById(Long id) {
        return entityManager.find(User.class, id);
    }

    public Long countAll() {
        return entityManager.createNamedQuery("countAllUsers", Long.class).getSingleResult();
    }

    @Transactional
    public void saveOrUpdate(User user) {
        if (user.getId() == null) {
            entityManager.persist(user);
        }
        entityManager.merge(user);
    }

    @Transactional
    public void deleteById(Long id) {
        entityManager.createNamedQuery("deleteUserById").setParameter("id", id).executeUpdate();
    }
}




