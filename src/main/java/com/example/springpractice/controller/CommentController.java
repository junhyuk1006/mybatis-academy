package com.example.springpractice.controller;

import com.example.springpractice.dto.Comment;
import com.example.springpractice.dto.User;
import com.example.springpractice.service.CommentService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CommentController {
    @Autowired
    CommentService service;

    @PostMapping("/board/{id}")
    public String addComment(@PathVariable("id") int boardId, HttpSession session, Comment comment){
        User user = (User) session.getAttribute("user");
        if(user == null) return "redirect:/login";
        comment.setBoardId(boardId);
        comment.setUserId(user.getId());
        int i = service.insertComment(comment);
        if(i==1) return "redirect:/board/{id}";
        else return "/";
    }
}
