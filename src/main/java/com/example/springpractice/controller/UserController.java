package com.example.springpractice.controller;

import com.example.springpractice.dto.User;
import com.example.springpractice.service.UserService;
import com.example.springpractice.util.SessionUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Controller
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping("/")
    public String home(HttpSession session,Model model){
        User user = SessionUtils.getSessionUser(session);
        if(user != null){
            model.addAttribute("user",user);
            System.out.println("Avatar URL: " + user.getAvatar_url());
        }
        return "user/index";
    }

    @GetMapping("/login")
    public String loginPage(){
        return "user/login";
    }

    @PostMapping("/login")
    public String login(User user, HttpServletRequest request, Model model){
        User loginUser = service.select(user);
        if(loginUser != null) {
            HttpSession session = request.getSession();
            session.setAttribute("user", loginUser);
            return "redirect:/";
        }
        else {
            model.addAttribute("message", "이메일 또는 비밀번호가 일치하지 않습니다.");
            return "user/login";
        }
    }

    @GetMapping("/join")
    public String joinPage(){
        return "user/join";
    }

    @PostMapping("/join")
    public String join(User user, Model model, RedirectAttributes redirectAttributes){
        User existUser = service.select(user);
        int result;
        if(existUser == null){
             result = service.insert(user);
            if(result == 1){
                redirectAttributes.addFlashAttribute("message","회원가입 완료되었습니다!");
                return "redirect:/login";
            }
            else {
                model.addAttribute("message","회원가입에 실패하였습니다");
                return "user/join";
            }
        }
        else if(existUser.getEmail().equals(user.getEmail())){
            model.addAttribute("message","email이 중복되었습니다");
            return "user/join";
        }else return "redirect:/";
    }

    @GetMapping("/mypage")
    public String mypage(HttpSession session , Model model){
        User user = SessionUtils.getSessionUser(session);
        model.addAttribute("user", user);
        if(user == null){
            return "redirect:/";
        }
        return "user/mypage";
    }

    @GetMapping("/edit")
    public String editPage(HttpSession session, Model model){
        User user = SessionUtils.getSessionUser(session);
        model.addAttribute("user", user);
        if(user == null){
            return "redirect:/";
        }
        return "user/edit";
    }

    @PostMapping("/edit")
    public String edit(User user,HttpSession session,RedirectAttributes redirectAttributes){
        User idUser = SessionUtils.getSessionUser(session);
        user.setId(idUser.getId());
        int i = service.update(user);
        if(i == 1){
            session.setAttribute("user", user); // 세션에 사용자 정보 갱신
            redirectAttributes.addFlashAttribute("message","수정이 완료되었습니다!");
            return "redirect:/mypage";
        }
        return "user/edit";
    }

    // 로그아웃
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // 세션 무효화
        return "redirect:/"; // 로그인 페이지로 리다이렉트
    }

    @GetMapping("/delete")
    public String delete(HttpSession session, Model model){
        User user = SessionUtils.getSessionUser(session);
        int i = service.delete(user);
        if(i == 1){
            session.invalidate();
            model.addAttribute("message","회원삭제에 성공하였습니다.");
            return "redirect:/";
        }
        return "redirect:/mypage";
    }

    @PostMapping("/upload-avatar")
    public String upload(@RequestParam("avatar") MultipartFile file, HttpSession session){
        if(!file.isEmpty()){
            try{
                User user = (User)session.getAttribute("user");
                // 기존 프로필 사진 삭제
                if (user.getAvatar_url() != null && !user.getAvatar_url().equals("/images/default.jpg")) {
                    // 기본 이미지가 아니라면 삭제 시도
                    Path existingFilePath = Paths.get(System.getProperty("user.dir") + "/src/main/resources/static" + user.getAvatar_url());
                    Files.deleteIfExists(existingFilePath);  // 기존 파일 삭제
                }

                String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename().replaceAll(" ", "_");
                Path path = Paths.get(System.getProperty("user.dir") + "/src/main/resources/static/images/" + fileName);
                Files.write(path,file.getBytes());

                user.setAvatar_url("/images/" + fileName);
                int i = service.updateUserAvatar(user.getId(),user.getAvatar_url());
            }catch(IOException e){
                e.printStackTrace();
            }
        }
        return "redirect:/";
    }
    // 로그 , 트랜잭션 , put
}
