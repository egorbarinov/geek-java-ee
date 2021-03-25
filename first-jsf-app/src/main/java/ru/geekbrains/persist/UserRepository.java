package ru.geekbrains.persist;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

@Stateless
public class UserRepository implements Serializable {

    private static final Logger logger = LoggerFactory.getLogger(UserRepository.class);

    @PersistenceContext(unitName = "ds")
    private EntityManager entityManager;

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
        entityManager.createNamedQuery("deleteUserById")
                .setParameter("id", id).executeUpdate();
    }

    public User findByName(String name) {
        return entityManager.createNamedQuery("findUserByName", User.class)
                .setParameter("name", name).getSingleResult();
    }
}
