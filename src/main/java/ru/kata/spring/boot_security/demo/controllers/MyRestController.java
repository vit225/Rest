package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.UserService;
import java.security.Principal;
import java.util.List;


@RestController
@RequestMapping("/api")
public class MyRestController {

    private final UserService userService;


    public MyRestController(UserService userService) {
        this.userService = userService;
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
        userService.updateUser(user);
        return user;
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUserForm(@PathVariable("id") Long id) {
        userService.deleteById(id);
        return "";
    }

    @PostMapping("/create")
    public User createUser(@RequestBody User user) {
        userService.saveUser(user);
        return user;
    }
}
