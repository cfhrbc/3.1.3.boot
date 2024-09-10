package ru.kata.spring.boot_security.demo.repository;

import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.Role;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class RoleDao {
    @PersistenceContext
    private EntityManager entityManager;

    public Role findByName(String name) {
        try {
            return entityManager.createQuery("SELECT r FROM Role r WHERE r.name = :name", Role.class)
                    .setParameter("name", name)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }


    public void save(Role role) {
        if (role.getId() == null) {
            entityManager.persist(role);
        } else {
            entityManager.merge(role);
        }
    }

    public Role findRoleById(Integer id) {
        return entityManager.find(Role.class, id);
    }


    public List<Role> findAll() {
        return entityManager.createQuery("SELECT r FROM Role r", Role.class).getResultList();
    }
}