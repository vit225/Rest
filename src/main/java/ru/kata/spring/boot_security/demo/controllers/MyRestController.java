package ru.kata.spring.boot_security.demo.controllers;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.RoleService;
import ru.kata.spring.boot_security.demo.services.UserService;

import java.security.Principal;
import java.util.List;


@RestController
@RequestMapping("/api")
public class MyRestController {

    private final UserService userService;
    private final RoleService roleService;

    public MyRestController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/users")
    public List<User> findAll() {
        return userService.findAll();
    }

    @GetMapping("/user")
    public User findByUsername(Principal principal) {
        return userService.findByUsername(principal.getName());
    }

    @PutMapping("/update")
    public User updateUser(@RequestBody User user) {
        User userOld = userService.findById(user.getId());
        userOld.setName(user.getName());
        userOld.setLastName(user.getLastName());
        userOld.setAge(user.getAge());
        userOld.setEmail(user.getEmail());
        userOld.setPassword(user.getPassword());
        userService.updateUser(userOld);
        return user;
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUserForm(@PathVariable("id") Long id) {
        userService.deleteById(id);
        return "Пользователь с id " + id + ",был удален";
    }

    @PostMapping("/create")
    public User createUser(@RequestBody User user) {
        userService.saveUser(user);
        return user;
    }
}
