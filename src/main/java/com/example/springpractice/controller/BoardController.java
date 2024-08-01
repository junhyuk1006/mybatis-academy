package com.example.springpractice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BoardController {
    @GetMapping("/boardList")
    public String boardList() {
        return "board/boardList";
    }

    @GetMapping("/addBoard")
    public String addBoard() {
        return "board/addBoard";
    }

    @PostMapping("/addBoard")
    public String add(){
        return "redirect:/boardList";
    }

    @GetMapping("/myBoardList")
    public String myBoard() {
        return "board/myBoardList";
    }
}
