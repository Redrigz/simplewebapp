package com.example.SimpleWebApp.controller;

import com.example.SimpleWebApp.model.User;
import com.example.SimpleWebApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.UUID;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String home(Model model) {
        User[] listUser = userService.getUsers();
        model.addAttribute("users", listUser);
        return "home";
    }

    @GetMapping("/createUser")
    public String createUser(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "formNewUser";
    }

    @GetMapping("/updateUser/{id}")
    public String updateUser(@PathVariable("id") final UUID id, Model model) {
        User user = userService.getUser(id);
        model.addAttribute("user", user);
        return "formUpdateUser";
    }

    @GetMapping("deleteUser/{id}")
    public ModelAndView deleteUser(@PathVariable("id") final UUID id) {
        userService.deleteUser(id);
        return new ModelAndView("redirect:/");
    }

    @PostMapping("/saveUser")
    public ModelAndView saveUser(@ModelAttribute User user) {
        System.out.println("ADD USER");
        System.out.println(user);
        userService.saveUser(user);
        return new ModelAndView("redirect:/");
    }
}
