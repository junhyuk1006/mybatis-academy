package com.example.springpractice.mapper;

import com.example.springpractice.dto.Board;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Mapper
public interface BoardMapper {
    int insertBoard(Board board);
    List<Board> getBoardList(int size,int offset);
    int countBoardList();
    int myCountBoardList(int userId);
    List<Board> getMyBoardList(@Param("userId") int userId, @Param("size") int size, @Param("offset") int offset);
    Board getBoard(int id);
    int updateBoard(Board board);
    int deleteBoard(int id);
}
