package com.example.springpractice.mapper;

import com.example.springpractice.dto.Board;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {
    int insertBoard(Board board);
    List<Board> getBoardList();
}
