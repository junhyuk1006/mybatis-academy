package com.example.springpractice.service;

import com.example.springpractice.dto.Comment;
import com.example.springpractice.dto.CommentLike;
import com.example.springpractice.dto.Like;
import com.example.springpractice.mapper.CommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    @Autowired
    CommentMapper mapper;

    public int insertComment(Comment comment){
        return mapper.insertComment(comment);
    }

    public List<CommentLike> getComments(int boardId){
        return mapper.getComments(boardId);
    }

    public int deleteComment(int commentId){
        return mapper.deleteComment(commentId);
    }


    public int incrementLikes(Like like){
        return mapper.incrementLikes(like);
    }

    public int decrementLikes(Like like){
        return mapper.decrementLikes(like);
    }
}
