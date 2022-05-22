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

    public Set<Role> addRole (String [] arrRoles) {
        Set<Role> roles =  new HashSet<>();
        for (String string : arrRoles) {
            if (string.equals("ADMIN")) {
                roles.add(new Role(1L, "ADMIN"));
            }
            if (string.equals("USER")) {
                roles.add(new Role(2L, "USER"));
            }
        }
        return roles;
    }
}
