package com.example.springpractice.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class CommentLike {
    private int id;
    private int userId;
    private String content;
    private Timestamp time;
    private int likeCount;
    private Integer parentId;
}
