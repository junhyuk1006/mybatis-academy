package com.example.springpractice.service;

import com.example.springpractice.dto.Board;
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

    public List<Board> getBoardList(){
        return boardMapper.getBoardList();
    }
}
