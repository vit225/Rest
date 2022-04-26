package ru.kata.spring.boot_security.demo.services;

import ru.kata.spring.boot_security.demo.models.User;

import java.util.List;


public interface UserService {

    void saveUser(User user);


    List<User> findAll();


    void deleteById(long id);


    User findById(long id);


    void updateUser(User user);

    User findByUsername(String username);
}
