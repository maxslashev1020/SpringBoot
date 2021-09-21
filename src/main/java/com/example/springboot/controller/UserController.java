package com.example.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import com.example.springboot.entity.Role;
import com.example.springboot.entity.User;
import com.example.springboot.service.RoleService;
import com.example.springboot.service.UserService;

import java.util.HashSet;
import java.util.Set;

@Controller
public class UserController implements WebMvcConfigurer {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @GetMapping(value = "/user")
    public String userInfo(@AuthenticationPrincipal User user, Model model){
        model.addAttribute("user", user);
        model.addAttribute("roles", user.getRoles());
        return "userpage";
    }

    @GetMapping(value = "/admin")
    public String listUsers(Model model) {
        model.addAttribute("allUsers", userService.getAllUsers());
        return "all-user";
    }

    @GetMapping(value = "/admin/new")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("roles", roleService.getAllRoles());
        return "info";
    }

    @PostMapping(value = "/admin/add-user")
    public String addUser(@ModelAttribute User user, @RequestParam(value = "checkBoxRoles") String[] checkBoxRoles) {
        Set<Role> roleSet = new HashSet<>();
        for (String role : checkBoxRoles) {
            roleSet.add(roleService.getRoleByName(role));
        }
        user.setRoles(roleSet);
        userService.addUser(user);


        return "redirect:/admin";
    }

    //страница для редактирования юзеров
    @GetMapping(value = "/{id}/edit")
    public String editUserForm(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        model.addAttribute("roles", roleService.getAllRoles());
        return "edit";
    }

    @PostMapping(value = "/{id}")
    public String editUser(@ModelAttribute User user, @RequestParam(value = "checkBoxRoles") String[] checkBoxRoles) {
        Set<Role> roleSet = new HashSet<>();
        for (String roles : checkBoxRoles) {
            roleSet.add(roleService.getRoleByName(roles));
        }
        user.setRoles(roleSet);
        userService.updateUser(user);
        return "redirect:/admin";
    }

    @PostMapping(value = "/remove/{id}")
    public String removeUser(@PathVariable("id") long id) {
        userService.removeUserById(id);
        return "redirect:/admin";
    }

    @GetMapping(value ="/login")
    public String login(){
        return "login";
    }
}
