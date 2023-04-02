package com.example.mvccrud.controller;

import com.example.mvccrud.entity.User;
import com.example.mvccrud.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SecurityController {

    @Autowired
    private SecurityService service;

    @GetMapping("/sign-up")
    public String signup(Model model){
        model.addAttribute("user", new User());
        return "signup";
    }
    @PostMapping("/register")
    public String register(User user, BindingResult result){
        if (result.hasErrors()){
            return "signup";
        }
        service.signUp(user);
        return "redirect:/login";
    }
    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/login-error")
    public String loginError(Model model){
        model.addAttribute("loginError", true);
        return "login";
    }
}
