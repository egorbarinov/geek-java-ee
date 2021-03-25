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
public class RoleRepository implements Serializable {

    private final Logger logger = LoggerFactory.getLogger(RoleRepository.class);

    @PersistenceContext(unitName = "ds")
    protected EntityManager em;

    public Role findById(Long id) {
        return em.find(Role.class, id);
    }

    public List<Role> findAll() {
        return em.createQuery("from Role ", Role.class).getResultList();
    }

    @Transactional
    public void saveOrUpdate(Role role) {
        if (role.getId() == null) {
            em.persist(role);
        }
        em.merge(role);
    }

    @Transactional
    public void deleteById(Long id) {
        em.createNamedQuery("deleteRoleById")
                .setParameter("id", id).executeUpdate();
    }

}
