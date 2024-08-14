package com.example.springpractice.controller;

import com.example.springpractice.dto.Comment;
import com.example.springpractice.dto.Like;
import com.example.springpractice.dto.User;
import com.example.springpractice.service.CommentService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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
        if(i==1) return "redirect:/board/"+boardId;
        else return "/";
    }

    @GetMapping("/deleteComment/{boardId}/{commentId}")
    public String deleteComment(@PathVariable("boardId") int boardId, @PathVariable("commentId") int commentId,HttpSession session){
        User user = (User) session.getAttribute("user");
        if(user == null) return "redirect:/login";
        int i = service.deleteComment(commentId);
        if (i==1) return "redirect:/board/"+boardId;
        else return "redirect:/boardList";
    }

    @PostMapping("/like/{boardId}/{commentId}")
    public String like(@PathVariable("commentId")int commentId,@PathVariable("boardId")int boardId,HttpSession session){
        User user = (User)session.getAttribute("user");
        if(user==null) return "redirect:/login";
        Like like = new Like(commentId,user.getId());
        service.incrementLikes(like);
        return "redirect:/board/"+boardId;
    }
}
