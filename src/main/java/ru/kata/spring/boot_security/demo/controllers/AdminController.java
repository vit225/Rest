package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.RoleService;
import ru.kata.spring.boot_security.demo.services.UserService;

import java.security.Principal;
import java.util.List;


@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;
    private final RoleService roleService;

    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }


    @GetMapping()
    public String findAll(Principal principal, Model model) {
        User user = userService.findByUsername(principal.getName());
        model.addAttribute("user", user);
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "admin";
    }

    @GetMapping("create")
    public String createUserForm(User user,Model model) {
        model.addAttribute("user",user);
        return "create";
    }

    @PostMapping("create")
    public String createUser(@ModelAttribute("user") User user
            , @RequestParam(value = "role") String[] roles) {
        user.setRoles(roleService.getRoles(roles));
        userService.saveUser(user);
        return "redirect:/admin";
    }

    @GetMapping("delete/{id}")
    public String deleteUserForm(@PathVariable("id") Long id) {
        userService.deleteById(id);
        return "redirect:/admin";
    }


    @GetMapping("update/{id}")
    public String updateUserForm(@PathVariable("id") Long id, Model model) {
        User user = userService.findById(id);
        model.addAttribute("user", user);
        return "update";
    }

    @PostMapping("update")
    public String updateUser(@ModelAttribute("user") User user
            , @RequestParam(value = "role") String[] roles) {
        user.setRoles(roleService.getRoles(roles));
        userService.updateUser(user);
        return "redirect:/admin";
    }

}
