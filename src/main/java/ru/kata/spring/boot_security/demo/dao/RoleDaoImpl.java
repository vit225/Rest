package ru.kata.spring.boot_security.demo.dao;

import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.models.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class RoleDaoImpl implements RoleDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void saveRole(Role role) {
        entityManager.persist(role);
    }

    @Override
    public Set<Role> findAllRole() {
        List<Role> list = entityManager.createQuery("SELECT role FROM Role role", Role.class).getResultList();
        return new HashSet<>(list);
    }

    @Override
    public void deleteById(long id) {
        Role role = entityManager.find(Role.class, id);
        if (role != null) {
            entityManager.remove(role);
        }
    }

    @Override
    public Role findById(long id) {
        return entityManager.find(Role.class, id);
    }

    @Override
    public void updateRole(Role role) {
        entityManager.merge(role);
    }

    @Override
    public Role getRoleByName(String name_role) {
        return entityManager.createQuery(
                "SELECT r from Role r where r.name_role=:name_role", Role.class
        ).setParameter("name_role", name_role).getSingleResult();
    }

    public Set<Role> getRoles(String[] roleNames) {
        Set<Role> roleSet = new HashSet<>();
        for (String role : roleNames) {
            roleSet.add(getRoleByName(role));
        }
        return roleSet;
    }
}
