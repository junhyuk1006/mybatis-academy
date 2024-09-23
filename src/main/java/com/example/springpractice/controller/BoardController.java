package com.example.springpractice.controller;

import com.example.springpractice.dto.*;
import com.example.springpractice.service.BoardService;
import com.example.springpractice.service.CommentService;
import com.example.springpractice.util.SessionUtils;
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

    @Autowired
    CommentService commentService;

    @GetMapping("/boardList")
    public String boardList(PageRequest pageRequest, Model model) {
        PageResponse response = service.getBoardList(pageRequest);
        model.addAttribute("pageResponse", response);
        return "board/boardList";
    }

    // 추가 -- 등록버튼 눌렀을시 로그인 안되있을시 메시지 창 띄우고 로그인페이지
    @GetMapping("/addBoard")
    public String addBoard(HttpSession session) {
        User user = SessionUtils.getSessionUser(session);
        if(user == null){
            return "redirect:/login";
        }
        return "board/addBoard";
    }

    @PostMapping("/addBoard")
    public String add(Board board, HttpSession session,RedirectAttributes redirectAttributes){
        User user = SessionUtils.getSessionUser(session);
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
            return "redirect:/";
        }
    }

    @GetMapping("/myBoardList")
    public String myBoard(HttpSession session , Model model, PageRequest pageRequest) {
        User user = SessionUtils.getSessionUser(session);
        if(user == null) return "redirect:/";
        PageResponse response = service.getMyBoardList(user.getId(), pageRequest);
        model.addAttribute("pageResponse",response);
        return "board/myBoardList";
    }

    @GetMapping("/board/{id}")
    public String board(@PathVariable("id") int id , HttpSession session,Model model){
        User user = SessionUtils.getSessionUser(session);
        if(user == null) return "redirect:/";  // 세션 확인

        Board board = service.getboard(id); // board의 id 값 주고 board 하나 가져오기

        // 자신이 작성한 board일때, 수정버튼과 삭제버튼을 화면에 나타내주기 위함.
        if(board.getUserId()== user.getId()) model.addAttribute("myBoard",board.getUserId());

        // 모델에 board 넣기
        model.addAttribute("board",board);

        // board 에 달린 댓글 가져오기
        List<CommentLike> comments = commentService.getComments(id);
        // 댓글 수정 삭제를 위한 모델에 user 넣기
        model.addAttribute("user",user);
        // 모델에 댓글 넣기
        model.addAttribute("comments", comments);
        return "board/board";
    }

    @GetMapping("/editBoard/{id}")
    public String edit(@PathVariable("id") int id,HttpSession session,Model model){
        User user = SessionUtils.getSessionUser(session);
        if(user == null) return "redirect:/";
        Board board = service.getboard(id);
        model.addAttribute("board",board);
        return "board/edit";
    }

    @PostMapping("/editBoard/{id}")
    public String editBoard(@PathVariable("id") int id, Board board){
        int i = service.updateBoard(board);
        if(i==1){
            return "redirect:/board/{id}";
        }
        return "redirect:/editBoard/{id}";
    }

    @GetMapping("/deleteBoard/{id}")
    public String deleteBoard(@PathVariable("id") int id, HttpSession session, RedirectAttributes redirectAttributes){
        User user = SessionUtils.getSessionUser(session);
        if(user == null) return "redirect:/";
        int i = service.deleteBoard(id);
        if(i==1){
            redirectAttributes.addFlashAttribute("deleteMessage","삭제에 성공하였습니다!");
            return "redirect:/myBoardList";
        }else return "redirect:/myBoard/{id}";
    }
}