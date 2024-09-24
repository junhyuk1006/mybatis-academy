package com.example.springpractice.mapper;

import com.example.springpractice.dto.Board;
import com.example.springpractice.dto.BoardLike;
import com.example.springpractice.dto.BoardList;
import com.example.springpractice.dto.PageRequest;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Mapper
public interface BoardMapper {
    int insertBoard(Board board);
    List<BoardList> getBoardList(PageRequest request);
    int countBoardList();
    int myCountBoardList(int userId);
    List<BoardList> getMyBoardList(@Param("userId") int userId, @Param("size") int size, @Param("offset") int offset);
    Board getBoard(int id);
    int updateRead(Board board);
    int updateBoard(Board board);
    int deleteBoard(int id);

    int incrementLike(BoardLike boardLike);

    int decrementLike(BoardLike boardLike);

    int getCountLike(int boardId);
}
