package com.example.springpractice.controller;

import com.example.springpractice.dto.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
    @GetMapping("/")
    public String login(Model model){
        model.addAttribute("user", new User());
        return "index";
    }

    @PostMapping("/")
    public void join(User user){

    }

    @GetMapping("/home")
    public String home(){
        return "home";
    }
}
