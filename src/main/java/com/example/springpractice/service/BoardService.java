package com.example.springpractice.service;

import com.example.springpractice.dto.Board;
import com.example.springpractice.dto.PageRequest;
import com.example.springpractice.dto.PageResponse;
import com.example.springpractice.mapper.BoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {

    @Autowired
    BoardMapper boardMapper;

    public int insert(Board board){
        return boardMapper.insertBoard(board);
    }

    public PageResponse getBoardList(PageRequest request){
        int total = boardMapper.countBoardList(); // 총 게시물 수
        int totalPages = (int)Math.ceil((double)total / request.getSize());
        List<Board> boardList = boardMapper.getBoardList(request.getSize(),request.getOffset());
        int startPage = (request.getPage()-1)/5*5+1;
        int endPage = Math.min(startPage+4,totalPages);
        return new PageResponse(boardList,totalPages, request.getPage(),startPage,endPage);
    }

    public PageResponse getMyBoardList(int userId,PageRequest request){
        int total = boardMapper.myCountBoardList(userId); // 총 게시물 수
        int totalPages = (int)Math.ceil((double)total / request.getSize());
        List<Board> myBoardList = boardMapper.getMyBoardList(userId,request.getSize(),request.getOffset());
        int startPage = (request.getPage()-1)/5*5+1;
        int endPage = Math.min(startPage+4,totalPages);
        return new PageResponse(myBoardList,totalPages, request.getPage(),startPage,endPage);
    }

    public Board getboard(int id) {
        return boardMapper.getBoard(id);
    }

    public int updateBoard(Board board){
        return boardMapper.updateBoard(board);
    }

    public int deleteBoard(int id){
        return boardMapper.deleteBoard(id);
    }
}
