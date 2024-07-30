package com.example.springpractice.controller;

import com.example.springpractice.dto.User;
import com.example.springpractice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping("/login")
    public String loginPage(Model model){
        model.addAttribute("user", new User());
        return "index";
    }

    @PostMapping("/login")
    public String login(User user, Model model){
        if(service.login(user) != null) return "home";
        else {
            model.addAttribute("message", "로그인이 실패하였습니다.");
            return "index";
        }
    }

    @GetMapping("/join")
    public String joinPage(Model model){
        model.addAttribute("user", new User());
        return "join";
    }

    @PostMapping("/join")
    public String join(User user,Model model){
        int result = service.join(user);
        if(result == 1){
            model.addAttribute("message","회원가입 완료되었습니다!");
            return "joinSuccess";
        }
        else {
            model.addAttribute("message","회원가입에 실패하였습니다");
            return "redirect:/join";
        }
    }

    @GetMapping("/home")
    public String home(){
        return "home";
    }
}
