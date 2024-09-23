package com.example.springpractice.controller;

import com.example.springpractice.dto.Comment;
import com.example.springpractice.dto.Like;
import com.example.springpractice.dto.User;
import com.example.springpractice.service.CommentService;
import com.example.springpractice.util.SessionUtils;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CommentController {
    @Autowired
    CommentService service;

    @PostMapping("/board/{id}")
    public String addComment(@PathVariable("id") int boardId, HttpSession session, Comment comment){
        User user = SessionUtils.getSessionUser(session);
        if(user == null) return "redirect:/";
        comment.setBoardId(boardId);
        comment.setUserId(user.getId());
        int i = service.insertComment(comment);
        if(i==1) return "redirect:/board/"+boardId;
        else return "/";
    }

    @GetMapping("/editComment/{boardId}/{commentId}")
    public String editComment(@PathVariable("commentId") int commentId, @PathVariable("boardId") int boardId ,Model model,HttpSession session){
        User user = SessionUtils.getSessionUser(session);
        if(user == null) return "redirect:/";
        Comment comment = service.getComment(commentId);
        model.addAttribute("comment",comment);
        model.addAttribute("boardId",boardId);
        return "/board/commentEdit";
    }

    @PostMapping("/editComment/{boardId}/{commentId}")
    public String editComment(@PathVariable("boardId")int boardId,@PathVariable("commentId") int commentId, Comment comment){
        comment.setId(commentId);
        int i = service.updateComment(comment);
        if(i==1){
            return "redirect:/board/{boardId}";
        }
        else return "redirect:/editComment/{boardId}/{commentId}";
    }

    @GetMapping("/deleteComment/{boardId}/{commentId}")
    public String deleteComment(@PathVariable("boardId") int boardId, @PathVariable("commentId") int commentId,HttpSession session){
        User user = SessionUtils.getSessionUser(session);
        if(user == null) return "redirect:/";
        int i = service.deleteComment(commentId);
        if (i==1) return "redirect:/board/"+boardId;
        else return "redirect:/boardList";
    }

    /*@GetMapping("/commentComment/{boardId}/{commentId}")
    public String commentComment(@PathVariable("boardId")int boardId,@PathVariable("commentId")int commentId, HttpSession session,Model model){
        User user = SessionUtils.getSessionUser(session);
        if(user == null) return "redirect:/";
        model.addAttribute("boarId",boardId);
        model.addAttribute("commentId",commentId);
        return "/board/commentComment";
    }*/

    @PostMapping("/commentComment/{boardId}/{commentId}")
    public String commentComment(@PathVariable("boardId")int boardId,@PathVariable("commentId")int commentId,HttpSession session,Comment comment){
        User user = SessionUtils.getSessionUser(session);
        if(user == null) return "redirect:/";
        comment.setUserId(user.getId());
        comment.setBoardId(boardId);
        comment.setParentId(commentId);
        System.out.println(comment);
        int i = service.insertComment(comment);
        if(i!=1) return "redirect:/commentComment/{boardId}/{commentId}";
        return "redirect:/board/{boardId}";
    }

    @PostMapping("/like/{boardId}/{commentId}")
    public String like(@PathVariable("commentId")int commentId,@PathVariable("boardId")int boardId,HttpSession session){
        User user = SessionUtils.getSessionUser(session);
        if(user==null) return "redirect:/";
        Like like = new Like(commentId,user.getId());
        try {
            int i = service.incrementLikes(like);
        }catch (DuplicateKeyException e){
            int j = service.decrementLikes(like);
            if(j==1) return "redirect:/board/"+boardId;
            else return "user/home";
        }
        return "redirect:/board/"+boardId;
    }
}
