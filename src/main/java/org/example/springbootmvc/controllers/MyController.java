package org.example.springbootmvc.controllers;

import org.example.springbootmvc.entities.Users;
import org.example.springbootmvc.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MyController {
    private UserService userService;
    @Autowired
    MyController(UserService userService){
        this.userService=userService;
    }

    @GetMapping("/login")
    String goLogin(){
        return "login";
    }

    @GetMapping("/register")
    String goRegister(){
        return "register";
    }

    @GetMapping("/home")
    String goHome(){
        return "home";
    }

    @PostMapping("/checkLogin")
    String check(@ModelAttribute Users user){
        if(userService.checkLogin(user)){
            return "redirect:/home";
        }
        else{
            return "redirect:/login";
        }
    }

    @PostMapping("/registerUser")
    String registerUser(@ModelAttribute Users user){
        if(userService.registerUser(user))return "redirect:/login";
        else return "redirect:/register";
    }
}
