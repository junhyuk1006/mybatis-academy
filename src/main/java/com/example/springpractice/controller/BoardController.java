package com.example.springpractice.controller;

import com.example.springpractice.dto.Board;
import com.example.springpractice.dto.User;
import com.example.springpractice.service.BoardService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class BoardController {
    @Autowired
    BoardService service;

    @GetMapping("/boardList")
    public String boardList(HttpSession session, Model model) {
        List<Board> boardList = service.getBoardList();
        model.addAttribute("boardList", boardList);
        return "board/boardList";
    }

    @GetMapping("/addBoard")
    public String addBoard() {
        return "board/addBoard";
    }

    @PostMapping("/addBoard")
    public String add(Board board, HttpSession session){
        User user = (User) session.getAttribute("user");
        if(user != null){
            board.setUserId(user.getId());
            int i = service.insert(board);
            if (i==1){
                return "redirect:/boardList";
            }
            return "redirect:/addBoard";
        }
        else{
            return "redirect:/login";
        }
    }

    @GetMapping("/myBoardList")
    public String myBoard() {
        return "board/myBoardList";
    }
}
