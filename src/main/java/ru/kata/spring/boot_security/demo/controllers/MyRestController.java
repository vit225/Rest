package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.exception.UserException;
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
    public ResponseEntity<List<User>> findAll() {
        return new ResponseEntity<> (userService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/user")
    public ResponseEntity<User> findByUsername(Principal principal) {
        return new ResponseEntity<> (userService.findByUsername(principal.getName()),HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<? extends Object> updateUser(@RequestBody User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(new UserException("Не правильный запрос"), HttpStatus.BAD_REQUEST);
        }
        userService.updateUser(user);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<UserException> deleteUserForm(@PathVariable("id") Long id) {
        userService.deleteById(id);
        return new ResponseEntity<>(new UserException("Пользователь удален"), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<? extends Object> createUser(@RequestBody User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(new UserException("Не правильный запрос"), HttpStatus.BAD_REQUEST);
        }
        userService.saveUser(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
