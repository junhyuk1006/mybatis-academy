package com.example.springpractice.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class Comment {
    private int id;
    private int userId;
    private int boardId;
    private String content;
    private Timestamp time;
    private Integer parentId;
}
