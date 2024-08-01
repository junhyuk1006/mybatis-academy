package com.example.springpractice.controller;

import com.example.springpractice.dto.User;
import com.example.springpractice.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public String login(User user, HttpServletRequest request, Model model){
        User loginUser = service.select(user);
        if(loginUser != null) {
            HttpSession session = request.getSession();
            session.setAttribute("user", loginUser);
            return "redirect:/home";
        }
        else {
            model.addAttribute("message", "아이디 또는 비밀번호가 일치하지 않습니다.");
            return "index";
        }
    }

    @GetMapping("/join")
    public String joinPage(Model model){
        model.addAttribute("user", new User());
        return "join";
    }

    @PostMapping("/join")
    public String join(User user, Model model, RedirectAttributes redirectAttributes){
        User existUser = service.select(user);
        int result;
        if(existUser == null){
             result = service.insert(user);
            if(result == 1){
                redirectAttributes.addFlashAttribute("successMessage","회원가입 완료되었습니다!");
                return "redirect:/login";
            }
            else {
                model.addAttribute("message","회원가입에 실패하였습니다");
                return "join";
            }
        }
        else{
            model.addAttribute("message","username이 중복되었습니다");
            return "join";
        }
    }

    @GetMapping("/home")
    public String home(HttpSession session){
        User user = (User)session.getAttribute("user");
        if(user == null){
            return "redirect:/login";
        }
        return "home";
    }

    @GetMapping("/mypage")
    public String mypage(HttpSession session , Model model){
        User user = (User)session.getAttribute("user");
        model.addAttribute("user", user);
        if(user == null){
            return "redirect:/login";
        }
        return "mypage";
    }

    @GetMapping("/edit")
    public String editPage(HttpSession session, Model model){
        User user = (User)session.getAttribute("user");
        model.addAttribute("user", user);
        if(user == null){
            return "redirect:/login";
        }
        return "edit";
    }

    @PostMapping("/edit")
    public String edit(User user,HttpSession session,RedirectAttributes redirectAttributes){
        User idUser = (User) session.getAttribute("user");
        user.setId(idUser.getId());
        int i = service.update(user);
        if(i == 1){
            session.setAttribute("user", user); // 세션에 사용자 정보 갱신
            redirectAttributes.addFlashAttribute("message","수정이 완료되었습니다!");
            return "redirect:/mypage";
        }
        return "edit";
    }

    // 로그아웃
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // 세션 무효화
        return "redirect:/login"; // 로그인 페이지로 리다이렉트
    }

    @GetMapping("/delete")
    public String delete(HttpSession session, Model model){
        User user = (User) session.getAttribute("user");
        int i = service.delete(user);
        if(i == 1){
            session.invalidate();
            model.addAttribute("message","회원삭제에 성공하였습니다.");
            return "delete";
        }
        return "redirect:/mypage";
    }
}
