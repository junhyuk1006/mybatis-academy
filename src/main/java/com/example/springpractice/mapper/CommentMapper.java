package com.example.springpractice.mapper;

import com.example.springpractice.dto.Comment;
import com.example.springpractice.dto.Like;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentMapper {
    int insertComment(Comment comment);
    List<Comment> getComments(int boardId);
    int deleteComment(int commentId);
    void incrementLikes(Like like);
}
