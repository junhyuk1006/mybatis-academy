package com.example.springpractice.mapper;

import com.example.springpractice.dto.Comment;
import com.example.springpractice.dto.CommentLike;
import com.example.springpractice.dto.Like;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentMapper {
    int insertComment(Comment comment);
    List<CommentLike> getComments(int boardId);
    int deleteComment(int commentId);
    int incrementLikes(Like like);
}
