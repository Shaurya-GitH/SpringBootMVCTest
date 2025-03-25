package org.example.springbootmvc.controllers;

import org.example.springbootmvc.entities.Users;
import org.example.springbootmvc.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

@Controller
@SessionAttributes("name")
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

    @ModelAttribute
    String storeName(){
        return "";
    }

    @PostMapping("/checkLogin")
    ModelAndView check(@ModelAttribute Users user, Model model){
        if(userService.checkLogin(user)){
            model.addAttribute("name",user.getName());
            ModelAndView mav=new ModelAndView("redirect:home");
            return mav;
        }
        else{
            ModelAndView mav=new ModelAndView("login");
            boolean error=true;
            mav.addObject("errorMessage",error);
            return mav;
        }
    }

    @PostMapping("/registerUser")
    String registerUser(@ModelAttribute Users user){
        if(userService.registerUser(user))return "redirect:/login";
        else return "redirect:/register";
    }

    @GetMapping("/logout")
    String logOut(SessionStatus status){
        status.setComplete();
        return "redirect:/";
    }
}
