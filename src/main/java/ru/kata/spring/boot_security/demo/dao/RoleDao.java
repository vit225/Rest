package ru.kata.spring.boot_security.demo.dao;

import ru.kata.spring.boot_security.demo.models.Role;
import java.util.Set;

public interface RoleDao {

    void saveRole(Role role);


    Set<Role> findAllRole();


    void deleteById(long id);


    Role findById(long id);


    void updateRole(Role role);


    Role getRoleByName(String role);

    Set<Role> getRoles(String[] roleNames);
}
