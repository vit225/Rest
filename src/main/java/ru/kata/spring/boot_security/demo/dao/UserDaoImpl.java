package ru.kata.spring.boot_security.demo.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext()
    private EntityManager entityManager;


    @Transactional
    @Override
    public void saveUser(User user) {
        entityManager.persist(user);
        entityManager.close();

    }


    @Transactional
    @Override
    public List<User> findAll() {
        return entityManager.createQuery("SELECT user FROM User user", User.class).getResultList();
    }


    @Transactional
    @Override
    public void deleteById(long id) {
        User user = entityManager.find(User.class, id);
        if (user != null) {
            entityManager.remove(user);
        }
    }


    @Transactional
    @Override
    public User findById(long id) {
        return entityManager.find(User.class, id);
    }


    @Transactional
    @Override
    public void updateUser(User user) {
        entityManager.merge(user);
    }

    @Override
    public User findByUsername(String name) {
        return entityManager.createQuery(
                        "SELECT user FROM User user WHERE user.name =:name", User.class)
                .setParameter("name", name)
                .getSingleResult();
    }

}
