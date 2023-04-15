package com.fruitella.inventory.controller;

import com.fruitella.inventory.entity.Users;
import com.fruitella.inventory.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        super();
        this.userService = userService;
    }

    @GetMapping("/users")
    public String listOfUsers(Model model) {
        model.addAttribute("users", userService.getAll());
        return "users";
    }

    @GetMapping("/users/new")
    public String createNewUserForm(Model model) {
        Users user = new Users();
        model.addAttribute("user", user);
        return "create_user";
    }

    @PostMapping("/users")
    public String saveUser(@ModelAttribute("user") Users user) {
        userService.saveUser(user);
        return "redirect:/users";
    }
}
