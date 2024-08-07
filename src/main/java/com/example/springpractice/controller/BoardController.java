package com.example.springpractice.controller;

import com.example.springpractice.dto.Board;
import com.example.springpractice.dto.PageRequest;
import com.example.springpractice.dto.PageResponse;
import com.example.springpractice.dto.User;
import com.example.springpractice.service.BoardService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class BoardController {
    @Autowired
    BoardService service;

    @GetMapping("/boardList")
    public String boardList(PageRequest pageRequest, HttpSession session, Model model) {
        User user = (User)session.getAttribute("user");
        if(user == null){
            return "redirect:/login";
        }
        //List<Board> boardList = service.getBoardList();
        //model.addAttribute("boardList", boardList);
        PageResponse response = service.getBoardList(pageRequest);
        model.addAttribute("pageResponse", response);
        return "board/boardList";
    }

    @GetMapping("/addBoard")
    public String addBoard(HttpSession session) {
        User user = (User)session.getAttribute("user");
        if(user == null){
            return "redirect:/login";
        }
        return "board/addBoard";
    }

    @PostMapping("/addBoard")
    public String add(Board board, HttpSession session,RedirectAttributes redirectAttributes){
        User user = (User) session.getAttribute("user");
        if(user != null){
            board.setUserId(user.getId());
            int i = service.insert(board);
            if (i==1){
                redirectAttributes.addFlashAttribute("successMessage","게시물을 등록하였습니다.");
                return "redirect:/boardList";
            }
            return "redirect:/addBoard";
        }
        else{
            return "redirect:/login";
        }
    }

    @GetMapping("/myBoardList")
    public String myBoard(HttpSession session , Model model, PageRequest pageRequest) {
        User user = (User)session.getAttribute("user");
        if(user == null) return "redirect:/login";
        PageResponse response = service.getMyBoardList(user.getId(), pageRequest);
        model.addAttribute("pageResponse",response);
        return "board/myBoardList";
    }

    @GetMapping("/board/{id}")
    public String board(@PathVariable("id") int id , HttpSession session,Model model){
        User user = (User)session.getAttribute("user");
        if(user == null) return "redirect:/login";
        Board board = service.getboard(id);
        if(board.getUserId()== user.getId()) model.addAttribute("myBoard",board.getUserId());
        model.addAttribute("board",board);
        return "board/board";
    }

    @GetMapping("/myBoard/{id}")
    public String myBoard(@PathVariable("id") int id, HttpSession session, Model model){
        User user = (User)session.getAttribute("user");
        if(user == null) return "redirect:/login";
        Board board = service.getboard(id);
        model.addAttribute("board",board);
        return "board/myBoard";
    }

    @GetMapping("/editBoard/{id}")
    public String edit(@PathVariable("id") int id,HttpSession session,Model model){
        User user = (User)session.getAttribute("user");
        if(user == null) return "redirect:/login";
        Board board = service.getboard(id);
        model.addAttribute("board",board);
        return "board/edit";
    }

    @PostMapping("/editBoard/{id}")
    public String editBoard(@PathVariable("id") int id, Board board){
        int i = service.updateBoard(board);
        if(i==1){
            return "redirect:/myBoard/{id}";
        }
        return "redirect:/editBoard/{id}";
    }

    @GetMapping("/deleteBoard/{id}")
    public String deleteBoard(@PathVariable("id") int id, HttpSession session, RedirectAttributes redirectAttributes){
        User user = (User)session.getAttribute("user");
        if(user == null) return "redirect:/login";
        int i = service.deleteBoard(id);
        if(i==1){
            redirectAttributes.addFlashAttribute("deleteMessage","삭제에 성공하였습니다!");
            return "redirect:/myBoardList";
        }else return "redirect:/myBoard/{id}";
    }
}